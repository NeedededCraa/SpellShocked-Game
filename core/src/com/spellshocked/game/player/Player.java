package com.spellshocked.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Player extends TextureRegion {
    protected String name;
    protected String element;
    public Player(String path){
        JsonReader jsonReader = new JsonReader();
        JsonValue contents = jsonReader.parse(Gdx.files.internal(path));





    }
}
