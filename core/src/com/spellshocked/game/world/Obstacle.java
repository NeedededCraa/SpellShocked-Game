package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Obstacle extends TextureRegion {
    protected String name;
    protected int radius;
    protected float hardness;
    protected boolean isDestructible;

    public Obstacle(String path){
        JsonReader jsonReader = new JsonReader();
        JsonValue contents = jsonReader.parse(Gdx.files.internal(path));
        name = contents.getString("name");
        radius = contents.getInt("radius");
        hardness = contents.getFloat("hardness");
        isDestructible = contents.getBoolean("isDestructible");
        setRegion(new Texture(contents.getString("texture")));

    }
    public String getName(){
        return name;
    }
    public float getHardness(){
        return hardness;
    }
    public int getRadius(){
        return radius;
    }

    public boolean isDestructible() {
        return isDestructible;
    }
}
