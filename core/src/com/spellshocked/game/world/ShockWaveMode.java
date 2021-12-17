package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import com.spellshocked.game.gui.BlockInventoryGUI;
import com.spellshocked.game.gui.ClickGUI;
import com.spellshocked.game.input.ConditionalRunnable;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.input.action.AttackAction;
import com.spellshocked.game.input.action.ConsumeAction;
import com.spellshocked.game.input.action.PlaceAction;
import com.spellshocked.game.world.obstacle.Chest;
import com.spellshocked.game.world.obstacle.ObstacleContainer;
import com.spellshocked.game.world.obstacle.ObstacleEntity;
import com.spellshocked.game.world.obstacle.Pumpkin;

import static com.spellshocked.game.world.Perlin.GenerateWhiteNoise;
import static com.spellshocked.game.world.Perlin.GenerateSmoothNoise;
import static com.spellshocked.game.world.Perlin.GeneratePerlinNoise;

import java.util.ArrayList;
import java.util.Random;

public class ShockWaveMode extends World{
    final static long mapSeed = 10000000;
    Random randomSeed;

    private PlayerEntity p;
    private SheepEntity s;

    private ClickGUI previousChestGUI;

    float[][] perlinNoise;

    float health = 1;//0 = dead, 1 = full health
    Texture healthbarTexture;
    long worldTimer;
    long startTime;
    TextButton countUpLabel;
    protected Stage stage;
    ProgressBar test;
    Skin skin = new Skin(Gdx.files.internal("./pixthulhu/skin/pixthulhu-ui.json"));
    public Texture healthBarBorder = new Texture("image/World/healthBars/healthBarBorder.png");


    public ShockWaveMode() {
        super( 100, 64, 64, 400, 240);
        this.randomSeed = new Random(this.mapSeed);
        this.perlinNoise = GeneratePerlinNoise(GenerateSmoothNoise(GenerateWhiteNoise(this.randomSeed ,super.xValue+1, super.yValue+1), 4), 6);

        this.p = new PlayerEntity(2);
        this.s = new SheepEntity();
        this.p.followWithCamera(super.orthographicCamera);
        this.p.setOrthographicCamera(super.orthographicCamera); //to get current zoom
        super.addEntity(this.s);
        super.addEntity(this.p);

        stage = new Stage(this.viewport, this.spriteBatch);
        startTime = System.currentTimeMillis();
        countUpLabel = new TextButton(String.format("%03d", worldTimer), new Skin(Gdx.files.internal("./pixthulhu/skin/pixthulhu-ui.json")));
        countUpLabel.setPosition(orthographicCamera.position.x+700,
                orthographicCamera.position.y-orthographicCamera.zoom*-700);//Gdx.graphics.getWidth()/2f)-100, (Gdx.graphics.getHeight()/30f)+orthographicCamera.zoom*700);
        countUpLabel.getLabel().setFontScale(0.5f, 0.5f);
        countUpLabel.setSize(50,50);
        stage.addActor(countUpLabel);
        activeStages.put(stage, true);

        test = new ProgressBar(100, 1000, 10, false, skin);
        stage.addActor(test);

        create_Tile_with_Perlin(this.perlinNoise);
        healthbarTexture = new Texture("image/World/healthBars/healthBarGreen.png");

        FunctionalInput.fromButtonJustPress(Input.Buttons.LEFT).onTrue(new ConditionalRunnable(new AttackAction(p), ()-> !InputScheduler.getInstance().buttonPressedThisLoop.getOrDefault(Input.Buttons.LEFT, false)));
        FunctionalInput.fromButtonJustPress(Input.Buttons.LEFT).onTrue(new ConditionalRunnable(new ConsumeAction(p), ()-> !InputScheduler.getInstance().buttonPressedThisLoop.getOrDefault(Input.Buttons.LEFT, false)));
        FunctionalInput.fromButtonJustPress(Input.Buttons.RIGHT).onTrue(new ConditionalRunnable(new PlaceAction(p), ()->!InputScheduler.getInstance().buttonPressedThisLoop.getOrDefault(Input.Buttons.RIGHT, false)));

    }

    public void create_Tile_with_Perlin(float[][] perlinNoise){
        /**
         * even Z tile - main tile
         * odd Z tile - transitional tile - might be two types
         * for the random Obstacle must use nextFloat same as when generating Perlin noise otherwise will cause different map from the same seed
         */
        for(int j = 0; j <= super.xValue; j++) {
            for (int i = 0; i <= super.yValue; i++) {
                switch ((int) (perlinNoise[j][i] * 20)) {
                    case 0:
                    case 1:
                        super.tiles[j][i] = new Tile(j, i, 0, World.WATER);
                        break;
                    case 2:
                        super.tiles[j][i] = new Tile(j, i, 1, World.WATER);
                        break;
                    case 3:
                        super.tiles[j][i] = new Tile(j, i, 1, World.SAND);
                        break;
                    case 4:
                    case 5:
                        super.tiles[j][i] = new Tile(j, i, 2, World.SAND);
                        break;
                    case 6:
                        super.tiles[j][i] = new Tile(j, i, 3, World.SAND);
                        break;
                    case 7:
                        super.tiles[j][i] = new Tile(j, i, 3, World.GRASS);
                        break;
                    case 8:
                    case 9:
                        super.tiles[j][i] = new Tile(j, i, 4, World.GRASS);
                        break;
                    case 10:
                    case 11:
                        super.tiles[j][i] = new Tile(j, i, 5, World.GRASS);
                        break;
                    case 12:
                    case 13:
                        super.tiles[j][i] = new Tile(j, i, 6, World.GRASS);
                        break;
                    case 14:
                        super.tiles[j][i] = new Tile(j, i, 7, World.GRASS);
                        break;
                    case 15:
                        super.tiles[j][i] = new Tile(j, i, 7, World.LAVA);
                        break;
                    case 16:
                    case 17:
                        super.tiles[j][i] = new Tile(j, i, 8, World.LAVA);
                        break;
                    case 18:
                    case 19:
                        super.tiles[j][i] = new Tile(j, i, 9, World.LAVA);
                        break;
                }
                if (Math.random()*100 < 1) {
                    if (Math.random() < 0.5) {
                        tiles[j][i].setObstacle(new Pumpkin(p));
                    }
                    else {
                        tiles[j][i].setObstacle(new Chest( p));
                    }
                }
            }
        }

        for (int i = 0; i < super.tiles.length; i++) {
            for (int j = 0; j < super.tiles[i].length; j++) {
                super.tiles[i][j].setNeighbors(super.tiles[Math.max(0,i-1)][j], super.tiles[Math.min(super.xValue,i+1)][j],
                        super.tiles[i][Math.min(super.yValue,j+1)], super.tiles[i][Math.max(0,j-1)]);
            }
        }
    }

    @Override
    public void render(float delta) {

        if(p.obstacleNear() != null && Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            ArrayList<Tile> tiles = p.obstacleNear();
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).obstacle instanceof ObstacleEntity<?> && ((ObstacleEntity<?>) tiles.get(i).obstacle).getGui().wasClicked(mouse, tiles.get(i))) {
                    //    if (tiles.size() != 0) {
                    //        ClickGUI chestGUI = ((ObstacleEntity<?>) tiles.get(i).obstacle).getGui();
                    //        if (chestGUI.isDisplaying()) {
                    //            if (previousChestGUI != null && previousChestGUI != chestGUI && previousChestGUI.isDisplaying()) {
                    //                previousChestGUI.changeDisplay();
                    //            }
                    //            previousChestGUI = chestGUI;
                    //        }
                    //        break;
                    //    }
                }
            }
        }
        super.render(delta);
        spriteBatch.begin();
        long totalTime = (-1)*(startTime - System.currentTimeMillis()) / 1000;
        countUpLabel.setText(String.format("%03d", totalTime));
        countUpLabel.setPosition(orthographicCamera.position.x, orthographicCamera.position.y+orthographicCamera.zoom*300);
        countUpLabel.setSize(400*orthographicCamera.zoom,200*orthographicCamera.zoom);
        s.targetTile(p.getTile());
        if(s.isAtTarget(p)) p.modifyHealth(-2);
        if(p.obstacleNear() != null && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            ArrayList<Tile> tiles = p.obstacleNear();
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).obstacle instanceof Chest && ((Chest) tiles.get(i).obstacle).getBlockInventoryGUI().wasClicked(mouse, tiles.get(i))) {
                    if (tiles.size() != 0) {
                        BlockInventoryGUI chestGUI = ((Chest) tiles.get(i).obstacle).getBlockInventoryGUI();
                        if (chestGUI.isDisplay()) {
                            if (previousChestGUI != null && previousChestGUI != chestGUI && previousChestGUI.isDisplay()) {
                                previousChestGUI.changeDisplay();
                            }
                            previousChestGUI = chestGUI;
                        }
                        break;
                    }
                }
            }
        }
        s.drawHealthBar(p, this);
        if (p.getRect().collidesWith(s.getRect())){
            health -= 0.001;
            System.out.print(health);
        }
        if (health<0){
            g.setScreen(g.dieGUI);
            health = 1;
        }

        super.spriteBatch.draw(healthbarTexture, orthographicCamera.position.x-350,
                    orthographicCamera.position.y-orthographicCamera.zoom*-400,
                    (healthbarTexture.getWidth()*health)/4, healthbarTexture.getHeight()/4);
        super.spriteBatch.draw(healthBarBorder, orthographicCamera.position.x-350,
                orthographicCamera.position.y-orthographicCamera.zoom*-400,
                (healthbarTexture.getWidth())/4, healthbarTexture.getHeight()/4);

        test.setPosition(500,500);
        //if(p.health <= 0)
        if(s.health <= 0) Spellshocked.getInstance().setScreen(Spellshocked.getInstance().dieGUI);

        spriteBatch.end();
    }

    @Override
    public void update_QuestGUI() {
        g.questGUI.task_1_progress.setText(g.world.timeCount+"/ 100");
        super.update_QuestGUI();
    }

    @Override
    public void print_debug(Entity entity, Tile tile) {
    }
}
