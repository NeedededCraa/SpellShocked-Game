package com.spellshocked.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

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



    /**
     * @return nothing, just for test is this class being imported properly
     */
    public static String hello(){
        return "Hello World! from com.spellshocked.game.myPlayer";
    }

}
