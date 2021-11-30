package com.spellshocked.game.world;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import static com.spellshocked.game.world.Perlin.GenerateWhiteNoise;
import static com.spellshocked.game.world.Perlin.GenerateSmoothNoise;
import static com.spellshocked.game.world.Perlin.GeneratePerlinNoise;

import java.util.Random;

public class RuinRunMode extends World{
    final static long mapSeed = 10000000;
    Random randomSeed;

    private PlayerEntity p;
    private SheepEntity s;

    float[][] perlinNoise;

    public RuinRunMode(Spellshocked g) {
        super(g, 100, 5000, 5000, 400, 240);
        this.randomSeed = new Random(this.mapSeed);
        this.perlinNoise = GeneratePerlinNoise(GenerateSmoothNoise(GenerateWhiteNoise(this.randomSeed ,5001, 5001), 4), 6);
        create_Tile_with_Perlin(this.perlinNoise);
        this.p = new PlayerEntity(2);
        this.s = new SheepEntity();
        this.p.followWithCamera(super.orthographicCamera);
        this.p.setOrthographicCamera(super.orthographicCamera); //to get current zoom
        super.addEntity(this.s);
        super.addEntity(this.p);

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
                        super.tiles[j][i] = new Tile(j, i, 0, super.SAND);
                        break;
                    case 2:
                        super.tiles[j][i] = new Tile(j, i, 1, super.SAND);
                        break;
                    case 3:
                        super.tiles[j][i] = new Tile(j, i, 1, super.GRASS);
                        break;
                    case 4:
                    case 5:
                        super.tiles[j][i] = new Tile(j, i, 2, super.GRASS);
                        break;
                    case 6:
                    case 7:
                        super.tiles[j][i] = new Tile(j, i, 3, super.GRASS);
                        break;
                    case 8:
                    case 9:
                        super.tiles[j][i] = new Tile(j, i, 4, super.GRASS);
                        break;
                    case 10:
                    case 11:
                        super.tiles[j][i] = new Tile(j, i, 5, super.GRASS);
                        break;
                    case 12:
                    case 13:
                        super.tiles[j][i] = new Tile(j, i, 6, super.GRASS);
                        break;
                    case 14:
                        super.tiles[j][i] = new Tile(j, i, 7, super.GRASS);
                        break;
                    case 15:
                        super.tiles[j][i] = new Tile(j, i, 7, super.LAVA);
                        break;
                    case 16:
                    case 17:
                        super.tiles[j][i] = new Tile(j, i, 8, super.LAVA);
                        break;
                    case 18:
                    case 19:
                        super.tiles[j][i] = new Tile(j, i, 9, super.LAVA);
                        break;
                }
                if (randomSeed.nextDouble() * 200 < 1) {
                    super.tiles[j][i].setObstacle(super.ROCK);
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
    public void print_debug(Entity entity, Tile tile) {
    }
}
