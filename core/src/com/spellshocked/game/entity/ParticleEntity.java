package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParticleEntity extends Entity {

    protected float frameTime;

    public ParticleEntity(TextureRegion[] t, float frameTime) {
        super(new TextureRegion[][]{t});
        this.frameTime = frameTime;
    }

    @Override
    public void periodic() {
        setRegion(getAnimations()[0].getKeyFrame(stateTime, false));
        super.periodic();
    }

    @Override
    public Animation<TextureRegion>[] getAnimations() {
        return new Animation[]{new Animation<>(frameTime, textures[0])};
    }
}
