package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Tile {
    protected String name;
    protected float hardness;
    protected String element;
    protected boolean isFireSpellProof;
    protected boolean isWaterSpellProof;
    protected boolean isEarthSpellProof;
    protected boolean isAirSpellProof;
    protected boolean isStandable;
    protected float harmPerSecond;
    protected Obstacle obstacle;


    protected TextureRegion[][] allTextures;
    protected TextureRegion[] currentTextures;
    public Tile left, right, front, back;
    public int xValue, yValue, zValue;

    public Sound tileSFX = null;

    public Tile(int x, int y, int z, String JsonPath){
        JsonReader jsonReader = new JsonReader();
        JsonValue jsonContent = jsonReader.parse(Gdx.files.internal(JsonPath));
        name = jsonContent.getString("name");
        hardness = jsonContent.getFloat("hardness");
        element = jsonContent.getString("element");
        isFireSpellProof = jsonContent.getBoolean("isFireSpellProof");
        isWaterSpellProof = jsonContent.getBoolean("isWaterSpellProof");
        isEarthSpellProof = jsonContent.getBoolean("isEarthSpellProof");
        isAirSpellProof = jsonContent.getBoolean("isAirSpellProof");
        harmPerSecond = jsonContent.getFloat("harmPerSecond");
        allTextures = TextureRegion.split(new Texture(jsonContent.getString("texture")), 16, 12);
        currentTextures = new TextureRegion[100];
        xValue = x;
        yValue = y;
        zValue = z;
        isStandable = jsonContent.getBoolean("isStandable");
        if (jsonContent.has("SFX")){ // not yet add to all json
            tileSFX = Gdx.audio.newSound(Gdx.files.internal(jsonContent.getString("SFX")));
            System.out.println(name + " has SFX");
        }
        else {
            System.out.println(name + " doesn't have SFX");
        }
    }

    public Tile(int x, int y, int z, Tile t){
        name = t.name;
        hardness = t.hardness;
        element = t.element;
        isFireSpellProof = t.isFireSpellProof;
        isWaterSpellProof = t.isWaterSpellProof;
        isEarthSpellProof = t.isEarthSpellProof;
        isAirSpellProof = t.isAirSpellProof;
        harmPerSecond = t.harmPerSecond;
        allTextures = t.allTextures;
        currentTextures = new TextureRegion[100];
        xValue = x;
        yValue = y;
        zValue = z;
        isStandable = t.isStandable;
    }

    public String getName(){
        return name;
    }
    public float getHardness(){
        return hardness;
    }
    public float getHarmPerSecond(){
        return harmPerSecond;
    }
    public boolean isFireSpellProof(){
        return isFireSpellProof;
    }
    public boolean isWaterSpellProof(){
        return isWaterSpellProof;
    }
    public boolean isEarthSpellProof(){
        return isEarthSpellProof;
    }
    public boolean isAirSpellProof(){
        return isAirSpellProof;
    }
    public boolean isStandable(){ return isStandable;}

    public Tile draw(SpriteBatch batch){
        for(int i = zValue; i >= 0; i--){
            if(currentTextures[i] == null) break;
            batch.draw(currentTextures[i], xValue*16, (yValue+i)*12);
        }
        if(obstacle!=null) batch.draw(obstacle.getTexture(), xValue*16, (yValue+zValue)*12);
        return this;
    }
    public Tile drawOnlyTop(SpriteBatch batch){
        batch.draw(currentTextures[zValue], xValue*16, (yValue+zValue)*12);
        return this;
    }
    public Tile drawBlockingFront(SpriteBatch b){
        drawFrontIfAbove(b, this.front, this.front.front, left.front, left.front.front, right.front, right.front.front);
        return this;
    }
    public void drawFrontIfAbove(SpriteBatch batch, Tile... tiles){
        for(Tile t : tiles){
            if(t.zValue>this.zValue) t.drawOnlyTop(batch);
            if(t.obstacle!=null) batch.draw(t.obstacle.getTexture(), t.xValue*16, (t.yValue+t.zValue)*12);
        }
    }

    public Tile updateTextures(){
        int x=0,y=1;
        if(left.zValue < zValue) x+=1;
        if(right.zValue < zValue) x+=2;
        if(back.zValue < zValue) y = 0;
        currentTextures[zValue] = allTextures[y][x];
        for(int i = zValue-1; i >= front.zValue; i--){
            y=2;
            if(i == zValue-1) y+=1;
            if(i == front.zValue) y+=2;
            currentTextures[i] = allTextures[y][x];
        }
        return this;
    }


    public Tile setNeighbors(Tile l, Tile r, Tile b, Tile f){
        left = l;
        right = r;
        front = f;
        back = b;
        updateTextures();
        return this;
    }

    protected void incrementz(){
        zValue++;
    }

    public void setObstacle(Obstacle obs){
        obstacle = obs;
        isStandable = false;
    }

    public float getZ(){
        return this.zValue;
    }

    public void playSFX(){
        if (tileSFX != null){
            tileSFX.play();
            System.out.println("sound played");
        }
        else {
            System.out.println(name + " current tile doesn't have SFX");
        }
    }
}
