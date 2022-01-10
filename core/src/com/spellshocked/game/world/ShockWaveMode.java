package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    private PlayerEntity player;

    private ClickGUI previousChestGUI;

    float[][] perlinNoise;

    float player_health = 1;//0 = dead, 1 = full health
    Texture healthbarTexture;
    long worldTimer;
    long startTime;
    TextButton score_Label;
    protected Stage stage;

    public Texture healthBarBorder = new Texture("image/World/healthBars/healthBarBorder.png");

    float raid_counter = 0;
    float score_counter = 0;
    int enemies_counter = 0;

    public ShockWaveMode() {
        super( 100, 64, 64, 400, 240);
        this.randomSeed = new Random(this.mapSeed);
        this.perlinNoise = GeneratePerlinNoise(GenerateSmoothNoise(GenerateWhiteNoise(this.randomSeed ,super.xValue+1, super.yValue+1), 4), 6);

        this.player = new PlayerEntity(2);
        this.player.followWithCamera(super.orthographicCamera);
        this.player.setOrthographicCamera(super.orthographicCamera); //to get current zoom
        super.addEntity(this.player);

        stage = new Stage(this.viewport, this.spriteBatch);
        startTime = System.currentTimeMillis();
        score_Label = new TextButton(String.format("%03d", worldTimer), new Skin(Gdx.files.internal("./pixthulhu/skin/pixthulhu-ui.json")));
        score_Label.getLabel().setFontScale(0.5f, 0.5f);
        stage.addActor(score_Label);
        activeStages.put(stage, true);

        create_Tile_with_Perlin(this.perlinNoise);
        healthbarTexture = new Texture("image/World/healthBars/healthBarGreen.png");

        FunctionalInput.fromButtonJustPress(Input.Buttons.LEFT).onTrue(new ConditionalRunnable(new AttackAction(player), ()-> !InputScheduler.getInstance().buttonPressedThisLoop.getOrDefault(Input.Buttons.LEFT, false)));
        FunctionalInput.fromButtonJustPress(Input.Buttons.LEFT).onTrue(new ConditionalRunnable(new ConsumeAction(player), ()-> !InputScheduler.getInstance().buttonPressedThisLoop.getOrDefault(Input.Buttons.LEFT, false)));
        FunctionalInput.fromButtonJustPress(Input.Buttons.RIGHT).onTrue(new ConditionalRunnable(new PlaceAction(player), ()->!InputScheduler.getInstance().buttonPressedThisLoop.getOrDefault(Input.Buttons.RIGHT, false)));
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

                if (super.tiles[j][i].Obstacle_onTop){
                    if (randomSeed.nextInt(100) < 1){
                        if (randomSeed.nextBoolean()) {
                            tiles[j][i].setObstacle(new Pumpkin(player));
                        }
                        else {
                            tiles[j][i].setObstacle(new Chest(player));
                        }
                    }
                }
            }
        }

        /*
         * set neighbor Tile
         */
        for (int i = 0; i < super.tiles.length; i++) {
            for (int j = 0; j < super.tiles[i].length; j++) {
                super.tiles[i][j].setNeighbors(super.tiles[Math.max(0,i-1)][j], super.tiles[Math.min(super.xValue,i+1)][j],
                        super.tiles[i][Math.min(super.yValue,j+1)], super.tiles[i][Math.max(0,j-1)]);
            }
        }
    }

    @Override
    public void render(float delta) {
        if(player.obstacleNear() != null && Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            ArrayList<Tile> tiles = player.obstacleNear();
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
        score_Label.setText(String.valueOf((int) score_counter));
        score_Label.setPosition(orthographicCamera.position.x+230, orthographicCamera.position.y+120);
        score_Label.setSize(140,70);
        if(player.obstacleNear() != null && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            ArrayList<Tile> tiles = player.obstacleNear();
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).obstacle instanceof Chest && ((Chest) tiles.get(i).obstacle).getGui().wasClicked(mouse, tiles.get(i))) {
                    if (tiles.size() != 0) {
                        BlockInventoryGUI chestGUI = ((Chest) tiles.get(i).obstacle).getGui();
                        if (chestGUI.isDisplaying()) {
                            if (previousChestGUI != null && previousChestGUI != chestGUI && previousChestGUI.isDisplaying()) {
                                previousChestGUI.changeDisplay();
                            }
                            previousChestGUI = chestGUI;
                        }
                        break;
                    }
                }
            }
        }
        for (Entity e: entities){
            if (e instanceof SheepEntity){
                ((SheepEntity)e).getRect().move(e.getX(), e.getY());
                e.targetTile(player.getTile());
                if(e.isAtTarget(player)) player.modifyHealth(-2);
                e.drawHealthBar(player, this);
                if (player.getRect().collidesWith(((SheepEntity) e).getRect())){
                    player_health -= 0.001;
                }
                System.out.println("current enemy health: "+e.health);
                if (e.health <= 0) {
                    enemies_counter--;
                }
            }
        }

        if (player_health < 0){
            Spellshocked.getInstance().dieGUI.reason.setText("you ran out of HP");
            Spellshocked.getInstance().setScreen(Spellshocked.getInstance().dieGUI);
        }
        if (enemies_counter < 0){
            Spellshocked.getInstance().dieGUI.reason.setText("you eliminate all enemies");
            Spellshocked.getInstance().setScreen(Spellshocked.getInstance().dieGUI);
        }
        if (raid_counter <= 1){
            raid_counter += 0.002;
        }
        else {
            score_counter += 100;
            raid_counter = 0;
            if (enemies_counter <= 5){
                wave(3);
            }
        }

        super.spriteBatch.draw(healthbarTexture, orthographicCamera.position.x-350,
                    orthographicCamera.position.y-orthographicCamera.zoom*-400,
                    (healthbarTexture.getWidth()* player_health)/4, healthbarTexture.getHeight()/4f);
        super.spriteBatch.draw(healthBarBorder, orthographicCamera.position.x-350,
                orthographicCamera.position.y-orthographicCamera.zoom*-400,
                (healthbarTexture.getWidth())/4f, healthbarTexture.getHeight()/4f);

        super.spriteBatch.draw(healthbarTexture, orthographicCamera.position.x,
                orthographicCamera.position.y+160,
                (healthbarTexture.getWidth()*raid_counter)/4, healthbarTexture.getHeight()/4f);
        super.spriteBatch.draw(healthBarBorder, orthographicCamera.position.x,
                orthographicCamera.position.y+160,
                (healthbarTexture.getWidth())/4f, healthbarTexture.getHeight()/4f);
        spriteBatch.end();
        score_counter += 1f/60f;
    }

    @Override
    public void update_QuestGUI() {
        Spellshocked.getInstance().questGUI.title.setText("shockwave mode");
        Spellshocked.getInstance().questGUI.task_1_name.setText("survive 100 frames");
        Spellshocked.getInstance().questGUI.task_1_description.setText("just stand there");
        Spellshocked.getInstance().questGUI.task_1_progress.setText(Spellshocked.getInstance().world.timeCount+"/ 100");
        Spellshocked.getInstance().dieGUI.score_number.setText(String.valueOf(score_counter));
        super.update_QuestGUI();
    }

    @Override
    public void print_debug(Entity entity, Tile tile) {
        System.out.println("Enemies on field: "+enemies_counter);
    }
    public void wave(int mob_generation_count){
        int positionX, positionY;
        for (int i = 0; i < mob_generation_count; i++){
            positionX = (int)(player.getX() + (Math.random() * (100+100) -100));
            positionY = (int)(player.getY() + (Math.random() * (100+100) -100));
            SheepEntity monster = new SheepEntity();
            monster.setPosition(positionX, positionY);
            monster.setTile(tiles[positionX/16][positionY/16]);
            super.addEntity(monster);
            enemies_counter++;
        }
    }
}
