package com.spellshocked.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.entity.Entity;

public class Item extends TextureRegion {
    protected String name;
    protected String description;
    protected float baseValue;

    protected float rarityIndex;
    protected String[] tags;

    //constructors
    public Item(String path){
        JsonReader jsonReader = new JsonReader();
        JsonValue contents = jsonReader.parse(Gdx.files.internal(path));
        name = contents.getString("name");
        description = contents.getString("description");
        baseValue = contents.getFloat("baseValue");
        rarityIndex = contents.getFloat("rarityIndex");
        tags = contents.get("tags").asStringArray();
//        System.out.println(contents.hasChild("tags"));
        setRegion(new Texture(contents.getString("texture")));




    }
    //all the getters
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public float getBaseValue(){
        return baseValue;
    }
    public String[] getTags(){
        return tags;
    }

    public float getRarityIndex(){
        return rarityIndex;
    }

    public boolean hasTag(String tag){
        for(String s : tags) {
            if(tag.equals(s)) return true;
        }
        return false;
    }


    /**
     * @return nothing, just for test is this class being imported properly
     */
    public static String hello(){
        return "Hello World! from com.spellshocked.game.myPlayer";
    }

    public void drawInHand(Batch b, Entity e){
        float x = e.getX(), y = e.getY();
        switch (e.getLastDirection()){
            case UP:
                x+=8;
                y+=12;
                break;
            case DOWN:
                x+=2;
                y+=4;
                break;
            case LEFT:
                x+=2;
                y+=8;
                break;
            case RIGHT:
                x+=8;
                y+=8;
                break;
        }
        b.draw(getTexture(), x, y, 10, 10);
    }
    public void onUse(){

    }
}
