package com.spellshocked.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.spellshocked.game.entity.Entity;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class World {

    public static final Texture GRASS = new Texture("./images/blocks/grass.png");

    public static final int RD = 16;
    private Tile[][] tiles;
    private Entity[] entities;
    private int entityIndex = 0;
    protected int xValue, yValue;
    private Perlin noise = new Perlin();
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
                tiles[i][j] = new Tile(i, j, (int) (perlinNoise[i][j]*10), "./jsons/tileDemo.json");
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].setNeighbors(tiles[Math.max(0,i-1)][j], tiles[Math.min(x,i+1)][j],
                        tiles[i][Math.min(y,j+1)], tiles[i][Math.max(0,j-1)]);
            }
        }
    }
    public void addEntity(Entity e){
        entities[entityIndex++] = e;
    }
    public void draw(SpriteBatch b, Vector3 v){
        int x = (int) v.x/16 + xValue/2;
        int y = (int) v.y/12 + yValue/2;
        for(int i = clamp(x-RD-xValue/2, 0, xValue); i <= clamp(x+RD-xValue/2, 0, xValue); i++){
            for(int j = clamp(y+RD-yValue/2, 0, yValue); j >= clamp(y-RD-yValue/2, 0, yValue); j--){
                tiles[i][j].draw(b);
            }
        }
        for(Entity e : entities){
            if(e == null) break;
            Tile t = tiles[(int) (e.getX()+8)/16][(int) ((e.getY()+2)/12-e.getTerrainHeight())];
            e.setTerrainHeight(t.zValue);
            e.periodic();
            e.draw(b);
            t.drawBlockingFront(b);
        }
    }
}
