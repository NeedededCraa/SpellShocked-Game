package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileEntity extends Entity {
    public TileEntity(TextureRegion[][] t, float walkspeed) {
        super(t, walkspeed);
    }
}
