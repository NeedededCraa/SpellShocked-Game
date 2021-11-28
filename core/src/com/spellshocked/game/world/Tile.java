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

    public Sound walkSFX_type1;
    public Sound walkSFX_type2;
    public int walkSFX_type1_interval;
    public int walkSFX_type2_interval;

    public Tile(int x, int y, int z, String jsonPath){
        JsonValue jsonContent = new JsonReader().parse(Gdx.files.internal(jsonPath));
        this.name = jsonContent.getString("name");
        this.hardness = jsonContent.getFloat("hardness");
        this.element = jsonContent.getString("element");
        this.isFireSpellProof = jsonContent.getBoolean("isFireSpellProof");
        this.isWaterSpellProof = jsonContent.getBoolean("isWaterSpellProof");
        this.isEarthSpellProof = jsonContent.getBoolean("isEarthSpellProof");
        this.isAirSpellProof = jsonContent.getBoolean("isAirSpellProof");
        this.harmPerSecond = jsonContent.getFloat("harmPerSecond");
        this.allTextures = TextureRegion.split(new Texture(jsonContent.getString("texture")), 16, 12);
        this.currentTextures = new TextureRegion[100];
        this.xValue = x;
        this.yValue = y;
        this.zValue = z;
        this.isStandable = jsonContent.getBoolean("isStandable");
        try {
            if (jsonContent.getBoolean("has_SFX")) { // not yet add to all json
                walkSFX_type1 = Gdx.audio.newSound(Gdx.files.internal(jsonContent.getString("walk_SFX_type1_path")));
                walkSFX_type1_interval = jsonContent.getInt("walk_SFX_type1_interval");
                walkSFX_type2 = Gdx.audio.newSound(Gdx.files.internal(jsonContent.getString("walk_SFX_type2_path")));
                walkSFX_type2_interval = jsonContent.getInt("walk_SFX_type2_interval");
                System.out.println(name + " has SFX");
            } else {
                System.out.println(name + " doesn't have SFX");
            }
        }
        catch (Exception e){
            System.out.println("something wrong when loading the sound asset");
            System.out.println(e);
        }
    }

    public Tile(int x, int y, int z, Tile t){
        this.hardness = t.hardness;
        this.name = t.name;
        this.element = t.element;
        this.isFireSpellProof = t.isFireSpellProof;
        this.isWaterSpellProof = t.isWaterSpellProof;
        this.isEarthSpellProof = t.isEarthSpellProof;
        this.isAirSpellProof = t.isAirSpellProof;
        this.harmPerSecond = t.harmPerSecond;
        this.allTextures = t.allTextures;
        this.currentTextures = new TextureRegion[100];
        this.xValue = x;
        this.yValue = y;
        this.zValue = z;
        this.isStandable = t.isStandable;
        this.walkSFX_type1 = t.walkSFX_type1;
        this.walkSFX_type1_interval = t.walkSFX_type1_interval;
        this.walkSFX_type2 = t.walkSFX_type2;
        this.walkSFX_type2_interval = t.walkSFX_type2_interval;
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

    public Obstacle getObstacle() {
        return obstacle;
    }

    public float getZ(){
        return this.zValue;
    }

    public double distanceFrom(Tile other){
        return Math.sqrt(Math.pow(other.xValue-xValue, 2)+Math.pow(other.yValue-yValue, 2));
    }


    /**
     * not really working but better put on the player side
     */
//    public void playSFX(){
//        if (tileSFX != null){
//            soundCount++;
//            if (soundCount%15 == 0){
//                tileSFX.play();
//                System.out.println("sound played");
//            }
//        }
//        else {
//            System.out.println(name + " current tile doesn't have SFX");
//        }
//    }
}
