package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;

public class PlayerEntity extends Entity {
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./images/entities/player.png"), 16, 24);
    public static final float WALKSPEED = 1;

    public PlayerEntity() {
        super(TEXTURES, WALKSPEED);
    }

}
