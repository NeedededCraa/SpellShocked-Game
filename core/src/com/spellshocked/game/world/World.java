package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.item.inventory.Hotbar;
import com.spellshocked.game.util.CameraHelper;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class World implements Screen {
    public static final Tile GRASS = new Tile(-1, -1, -1, "./jsons/tileDemo.json");
    public static final Obstacle ROCK = new Obstacle("./jsons/Obstacle.json");

    private SpriteBatch b;
    private OrthographicCamera c;
    private PlayerEntity p;
    private SheepEntity s;

    public static final int RD = 24; //increase the rendering distance solved most issues
    private Tile[][] tiles;
    private Entity[] entities;
    private int entityIndex = 0;
    protected int xValue, yValue;
    private Perlin noise = new Perlin();
    protected Hotbar hotbar;
    public World(int x, int y){
        tiles = new Tile[x+1][y+1];
        entities = new Entity[100];
        xValue = x;
        yValue = y;

        float[][] seed =  Perlin.GenerateWhiteNoise(x+1, y+1);
        float[][] seedE = Perlin.GenerateSmoothNoise( seed, 4);
        float[][] perlinNoise = Perlin.GeneratePerlinNoise(seedE, 6);

        for(int i = 0; i <= x; i++){
            for(int j = 0; j <= y; j++){
                tiles[i][j] = new Tile(i, j, (int) (perlinNoise[i][j]*10), GRASS);
                if (Math.random()*200 < 1) tiles[i][j].setObstacle(ROCK);
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].setNeighbors(tiles[Math.max(0,i-1)][j], tiles[Math.min(x,i+1)][j],
                        tiles[i][Math.min(y,j+1)], tiles[i][Math.max(0,j-1)]);
            }
        }
        b = new SpriteBatch();
        c = new OrthographicCamera(400, 240);
        c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);
        p = new PlayerEntity();
        s = new SheepEntity();
        p.followWithCamera(c);
        p.setOrthographicCamera(c);
        p.setSize(0.2f, 0.4f);
        s.setSize(0.3f, 0.2f);
        p.setPosition(200, 120);
        s.setPosition(250, 120);
        addEntity(s);
        addEntity(p);

        /* for more convenience hand position */
        FunctionalInput.fromKeyPress(Input.Keys.Q).onTrue(()->CameraHelper.zoomOut(c));
        FunctionalInput.fromKeyPress(Input.Keys.E).onTrue(()->CameraHelper.zoomIn(c));
        FunctionalInput.fromKeyPress(Input.Keys.W).onTrue(p::moveUp);
        FunctionalInput.fromKeyPress(Input.Keys.S).onTrue(p::moveDown);
        FunctionalInput.fromKeyPress(Input.Keys.A).onTrue(p::moveLeft);
        FunctionalInput.fromKeyPress(Input.Keys.D).onTrue(p::moveRight);
        FunctionalInput.fromKeyPress(Input.Keys.UP).onTrue(s::moveUp);
        FunctionalInput.fromKeyPress(Input.Keys.DOWN).onTrue(s::moveDown);
        FunctionalInput.fromKeyPress(Input.Keys.LEFT).onTrue(s::moveLeft);
        FunctionalInput.fromKeyPress(Input.Keys.RIGHT).onTrue(s::moveRight);

        hotbar = new Hotbar(9);
        hotbar.setActiveSlot(1);
    }
    public void addEntity(Entity e){
        entities[entityIndex++] = e;
    }

    @Override
    public void show() {

    }
    float pastCamX, pastCamY;
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        InputScheduler.getInstance().run();
        c.update();
        b.setProjectionMatrix(c.combined);
        b.begin();


        int x = (int) c.position.x/16 + xValue/2;
        int y = (int) c.position.y/12 + yValue/2; //changed to 16 then fixed the issue of player standing on void when y=0 but caused same issue when y=64
        for(int i = clamp(x-RD-xValue/2, 0, xValue); i <= clamp(x+RD-xValue/2, 0, xValue); i++){
            for(int j = clamp(y+RD-yValue/2, 0, yValue); j >= clamp(y-RD-yValue/2, 0, yValue); j--){
                tiles[i][j].draw(b);
            }
        }
        pastCamX = c.position.x;
        pastCamY = c.position.y;
        for(Entity e : entities){
            if(e == null) break;
            Tile t = tiles[(int) (e.getX()+8)/16][clamp((int) ((e.getY()+2)/12-e.getTerrainHeight()), 0, yValue)];
            e.setTile(t);
            e.periodic();
            e.draw(b);
            t.drawBlockingFront(b);
            if(e instanceof PlayerEntity){
                System.out.println("X: "+t.xValue+" Y: "+t.yValue+" Z: "+t.zValue);
//                System.out.println(" "+(int) (e.getX()+8)/16+" "+clamp((int) ((e.getY()+2)/12-e.getTerrainHeight()), 0, yValue));
//                System.out.println(clamp(x-RD-xValue/2, 0, xValue)+" " +clamp(x+RD-xValue/2, 0, xValue));
//                System.out.println(clamp(y+RD-yValue/2, 0, yValue)+" " +clamp((y-RD-yValue/2), 0, yValue));
            }
        }
        hotbar.draw(b, pastCamX-144, pastCamY-c.zoom*120);
        

        b.end();
    }

    @Override
    public void resize(int width, int height) {

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
