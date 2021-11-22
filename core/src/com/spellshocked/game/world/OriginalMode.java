package com.spellshocked.game.world;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;

public class OriginalMode extends World{
    private PlayerEntity p;
    private SheepEntity s;

    float[][] seed =  Perlin.GenerateWhiteNoise(65, 65);
    float[][] seedE = Perlin.GenerateSmoothNoise( seed, 4);
    float[][] perlinNoise = Perlin.GeneratePerlinNoise(seedE, 6);

    public OriginalMode(Spellshocked g) {
        super(g, 100, 64, 64, 400, 240);
        create_Tile_with_Perlin(perlinNoise);
        this.p = new PlayerEntity(10);
        this.s = new SheepEntity();
        this.p.followWithCamera(orthographicCamera);
        this.p.setOrthographicCamera(orthographicCamera); //to get current zoom
        super.addEntity(s);
        super.addEntity(p);
    }

    public void create_Tile_with_Perlin(float[][] perlinNoise){
        /**
         * even Z tile - main tile
         * odd Z tile - transitional tile - might be two types
         */
        for(int i = 0; i <= super.xValue; i++) {
            for (int j = 0; j <= super.yValue; j++) {
                switch ((int) (perlinNoise[i][j] * 20)) {
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
                if (Math.random() * 200 < 1) {
                    super.tiles[j][i].setObstacle(super.ROCK);
                }
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].setNeighbors(tiles[Math.max(0,i-1)][j], tiles[Math.min(super.xValue,i+1)][j],
                        tiles[i][Math.min(super.yValue,j+1)], tiles[i][Math.max(0,j-1)]);
            }
        }
    }
}
