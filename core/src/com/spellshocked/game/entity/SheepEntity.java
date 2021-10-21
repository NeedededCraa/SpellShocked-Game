package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SheepEntity extends Entity{
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./images/entities/sheep.png"), 28, 17);
    public static final float WALKSPEED = 1;

    public SheepEntity() {
        super(TEXTURES, WALKSPEED);
    }
}
