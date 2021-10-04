package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerEntity extends Entity {
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./images/entities/player.png"), 16, 32);
    public static final float WALKSPEED = 1;
    public PlayerEntity() {
        super(TEXTURES, WALKSPEED);
    }
}
