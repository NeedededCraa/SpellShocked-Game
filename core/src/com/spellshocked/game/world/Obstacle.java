package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Obstacle extends TextureRegion {
    protected String name;
    protected float hardness;
    protected boolean isDestructible;

    public Obstacle(String jsonPath){
        JsonValue jsonContent = new JsonReader().parse(Gdx.files.internal(jsonPath));
        name = jsonContent.getString("name");
        hardness = jsonContent.getFloat("hardness");
        isDestructible = jsonContent.getBoolean("isDestructible");
        setRegion(new Texture(jsonContent.getString("texture")));
    }

    public String getName(){
        return name;
    }
    public float getHardness(){
        return hardness;
    }

    public boolean isDestructible() {
        return isDestructible;
    }

}
