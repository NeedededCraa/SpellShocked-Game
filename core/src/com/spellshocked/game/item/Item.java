package com.spellshocked.game.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.sun.tools.javac.code.Attribute;

public class Item extends TextureRegion {
    protected String name;
    protected String description;
    protected TextureRegion texture;
    protected float baseValue;

    protected float rarityIndex;
    protected String[] tags;

    //constructors
    public Item(String jsonFile){

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



    /**
     * @return nothing, just for test is this class being imported properly
     */
    public static String hello(){
        return "Hello World! from com.spellshocked.game.myPlayer";
    }
    //Json json = new Json();
    //types = json.fromJson(Array.class,Item.class, Gdx.files.internal() )

}
