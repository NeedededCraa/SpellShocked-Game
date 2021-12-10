package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import com.spellshocked.game.gui.BlockInventoryGUI;
import com.spellshocked.game.gui.QuestGUI;
import com.spellshocked.game.input.FunctionalInput;

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

    private BlockInventoryGUI previousChestGUI;

    float[][] perlinNoise;

    long worldTimer;
    long startTime;
    TextButton countUpLabel;
    protected Stage stage;
    Obstacle CHEST;

    public ShockWaveMode(Spellshocked g) {
        super(g, 100, 64, 64, 400, 240);
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
        countUpLabel.setPosition((Gdx.graphics.getWidth()/2f)-100, (Gdx.graphics.getHeight()/30f)+orthographicCamera.zoom*700);
        countUpLabel.getLabel().setFontScale(0.5f, 0.5f);
        countUpLabel.setSize(50,50);
        stage.addActor(countUpLabel);
        activeStages.put(stage, true);

        create_Tile_with_Perlin(this.perlinNoise);

        this.CHEST = new Chest("./json/Object/chest.json", g, this.p);

        g.questGUI = new QuestGUI(g);
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

                if (super.tiles[j][i].Obstacle_onTop == true){
                    if (randomSeed.nextInt(250) < 1) {
                        super.tiles[j][i].setObstacle(World.ROCK);
                    }
                    else if (randomSeed.nextInt(5000) < 1){
                        super.tiles[j][i].setObstacle(this.CHEST);
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
        super.render(delta);

        spriteBatch.begin();
        long totalTime = (-1)*(startTime - System.currentTimeMillis()) / 1000;
        countUpLabel.setText(String.format("%03d", totalTime));
        countUpLabel.setPosition(orthographicCamera.position.x, orthographicCamera.position.y+orthographicCamera.zoom*10+100);
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
        spriteBatch.end();
    }

    @Override
    public void print_debug(Entity entity, Tile tile) {
    }
}
