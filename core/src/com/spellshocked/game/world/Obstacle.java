package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.BlockInventoryGUI;
import com.spellshocked.game.Spellshocked;

public class Obstacle extends TextureRegion {
    protected String name;
    protected float hardness;
    protected boolean isDestructible;
    protected BlockInventoryGUI testing;

    public Obstacle(String jsonPath){
        JsonValue jsonContent = new JsonReader().parse(Gdx.files.internal(jsonPath));
        name = jsonContent.getString("name");
        hardness = jsonContent.getFloat("hardness");
        isDestructible = jsonContent.getBoolean("isDestructible");
        setRegion(new Texture(jsonContent.getString("texture")));
    }

    // block inv testing
    public Obstacle(String jsonPath, Spellshocked g, PlayerEntity p) {
        JsonValue jsonContent = new JsonReader().parse(Gdx.files.internal(jsonPath));
        name = jsonContent.getString("name");
        hardness = jsonContent.getFloat("hardness");
        isDestructible = jsonContent.getBoolean("isDestructible");
        setRegion(new Texture(jsonContent.getString("texture")));
        testing = new BlockInventoryGUI(g, p);
    }

    public String getName(){
        return name;
    }
    public float getHardness(){
        return hardness;
    }

    // block inv testing
    public BlockInventoryGUI getBlockInventoryGUI() { return testing; }

    public boolean isDestructible() {
        return isDestructible;
    }

}
