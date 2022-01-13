package com.spellshocked.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.spellshocked.game.Spellshocked;

public class ExplosionEntity extends ParticleEntity {
    public static final TextureRegion[] TEXTURES = TextureRegion.split(new Texture("./image/Entity/Projectile/explosion.png"), 20, 20)[0];
    long time;

    public ExplosionEntity() {
        super(TEXTURES, 0.1f);
        time = System.currentTimeMillis();
        setSize(0.4f, 0.4f);
    }
    @Override
    public void periodic() {
        super.periodic();
        if (time+500<System.currentTimeMillis()) Spellshocked.getInstance().world.removeEntity(this);
    }
}
