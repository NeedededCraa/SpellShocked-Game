package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.util.CameraHelper;

import static com.badlogic.gdx.Input.*;
import static com.badlogic.gdx.math.MathUtils.clamp;

public class World implements Screen {
    /**
     * pre-create all types of tile so no need to read JSON file every time
     */
    public static final Tile GRASS = new Tile(-1, -1, -1, "./json/Tile/grass.json");
    public static final Tile SAND = new Tile(-1, -1, -1, "./json/Tile/sand.json");
    public static final Tile LAVA = new Tile(-1, -1, -1, "./json/Tile/lava.json");
    public static final Obstacle ROCK = new Obstacle("./json/Obstacle/rock.json");

    /**
     * variables that share with child class
     */
    protected SpriteBatch b;
    protected OrthographicCamera c;
    public CameraHelper cameraHelper; //for zooming
    public Spellshocked g;
    protected Tile[][] tiles;
    protected Entity[] entities;
    public static int renderDistance; //increase the rendering distance solved most issues

    private Label timeLabel;
    private float timeCount;
     //increase the rendering distance solved most issues

    private int entityIndex = 0;
    protected int xValue, yValue;
    static final int x = 64;
    static final int y = 64;

    public float VOLUME = 0.75f;
    public int frame_since_start;

    public World(Spellshocked g){
        this.g = g;
        this.tiles = new Tile[x+1][y+1];
        this.entities = new Entity[100];
        this.xValue = x;
        this.yValue = y;

        this.b = new SpriteBatch();
        this.c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);

        /* for more convenience hand position */
        FunctionalInput.fromKeyJustPress(Keys.Q).onTrue(cameraHelper::zoomOut);
        FunctionalInput.fromKeyJustPress(Keys.E).onTrue(cameraHelper::zoomIn);
        FunctionalInput.fromKeyJustPress(Keys.ESCAPE).onTrue(()-> g.setScreen(g.pause));
        FunctionalInput.fromKeyJustPress(Keys.K).onTrue(()-> g.setScreen(g.dieGUI));
    }

    public World(Spellshocked g, int Entity_count_limit, int Tiles_X_limit, int Tiles_Y_limit){
        this.g = g;
        this.tiles = new Tile[Tiles_X_limit][Tiles_Y_limit];
        this.entities = new Entity[Entity_count_limit];
        this.xValue = x;
        this.yValue = y;

        this.c = new OrthographicCamera(400, 240);
        this.cameraHelper = new CameraHelper(c);

        this.b = new SpriteBatch();
        this.c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);

        /* for more convenience hand position */
        FunctionalInput.fromKeyJustPress(Keys.Q).onTrue(cameraHelper::zoomOut);
        FunctionalInput.fromKeyJustPress(Keys.E).onTrue(cameraHelper::zoomIn);
        FunctionalInput.fromKeyJustPress(Keys.ESCAPE).onTrue(()-> g.setScreen(g.pause));
        FunctionalInput.fromKeyJustPress(Keys.K).onTrue(()-> g.setScreen(g.dieGUI));
    }

    public void addEntity(Entity e){
        e.VOLUME = this.VOLUME; //pass the master volume into entity
        entities[entityIndex++] = e;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        InputScheduler.getInstance().run();
        c.update();
        b.setProjectionMatrix(c.combined);
        b.begin();

        renderDistance = cameraHelper.get_render_distance();
        int x = (int) c.position.x/16 + xValue/2;
        int y = (int) c.position.y/12 + yValue/2; //changed to 16 then fixed the issue of player standing on void when y=0 but caused same issue when y=64
        for(int i = clamp(x-renderDistance-xValue/2, 0, xValue); i <= clamp(x+renderDistance-xValue/2, 0, xValue); i++){
            for(int j = clamp(y+renderDistance-yValue/2, 0, yValue); j >= clamp(y-renderDistance-yValue/2, 0, yValue); j--){
                tiles[i][j].draw(b);
            }
        }

        for(Entity e : entities){
            if(e == null) break;
            Tile t = tiles[(int) (e.getX()+8)/16][clamp((int) ((e.getY()+2)/12-e.getTerrainHeight()), 0, yValue)];
            e.setTile(t);
            e.draw(b);
            e.periodic();
            t.drawBlockingFront(b);
            if(e instanceof PlayerEntity){
//                System.out.println("X: "+t.xValue+" Y: "+t.yValue+" Z: "+t.zValue);
//                System.out.println(" "+(int) (e.getX()+8)/16+" "+clamp((int) ((e.getY()+2)/12-e.getTerrainHeight()), 0, yValue));
//                System.out.println(clamp(x-renderDistance-xValue/2, 0, xValue)+" " +clamp(x+renderDistance-xValue/2, 0, xValue));
//                System.out.println(clamp(y+renderDistance-yValue/2, 0, yValue)+" " +clamp((y-renderDistance-yValue/2), 0, yValue));
//                System.out.println(c.zoom);
//                System.out.println(cameraHelper.get_zoom_level());
//                System.out.println("camX: "+(pastCamX-144)+" camY: "+(pastCamY-c.zoom*120));
//                System.out.println(Gdx.graphics.getWidth() +" "+ Gdx.graphics.getHeight());
            }
        }

        b.end();

//        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
        frame_since_start++;
    }

    @Override
    public void resize(int width, int height) {

    }
    public void update(float dt){
        timeCount+= dt;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
