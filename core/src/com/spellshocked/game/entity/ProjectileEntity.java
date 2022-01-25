package com.spellshocked.game.entity;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ProjectileEntity extends Entity {
    public double damage;
    public Sound SFX_type1;
    public ProjectileEntity(double dmg, TextureRegion[][] tex) {
        super(tex, 2);
        damage = dmg;
    }

    public TextureRegion[] parseWalkingSheetRow(TextureRegion[] t) {
        return new TextureRegion[]{t[0], t[1], t[0], t[2]};
    }
    @Override
    public Animation<TextureRegion>[] getAnimations() {
        Animation<TextureRegion>[] a = new Animation[textures.length];
        for (int i = 0; i < textures.length; i++) {
            a[i] = new Animation<>(0.1f, parseWalkingSheetRow(textures[i]));
        }
        return a;
    }

    public void playSFX(){
        if (SFX_type1 != null){
            SFX_type1.play(super.VOLUME);
        }
    }
}
