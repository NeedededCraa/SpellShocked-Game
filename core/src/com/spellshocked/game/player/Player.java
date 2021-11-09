package com.spellshocked.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Player extends TextureRegion {
    protected String name;
    protected String element;
    public Player(String jsonPath){
        JsonValue jsonContent = new JsonReader().parse(Gdx.files.internal(jsonPath));





    }
}
