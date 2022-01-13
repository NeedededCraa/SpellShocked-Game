package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.item.CollisionRect;

public class ProjectileEntity extends Entity {
    public double damage;
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


}
