package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.item.CollisionRect;

public class SlimeEntity extends Entity implements Hostile{
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("image/Entity/SlimeEntity/slime.png"), 16, 14);
    public static final float WALKSPEED = 1;
    public int size;

    public SlimeEntity(int s){
        super(TEXTURES, WALKSPEED);
        size = s;
        setSize(0.1f*size, 0.1f*size);
        rect = new CollisionRect(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
        health = 5*size;
    }

    public TextureRegion[] parseWalkingSheetRow(TextureRegion[] t) {
        return new TextureRegion[]{t[0], t[1], t[0], t[2]};
    }

    @Override
    public Animation<TextureRegion>[] getAnimations() {
        Animation<TextureRegion>[] a = new Animation[TEXTURES.length];
        for (int i = 0; i < TEXTURES.length; i++) {
            a[i] = new Animation<>(0.05f, parseWalkingSheetRow(TEXTURES[i]));
        }
        return a;
    }

    public CollisionRect getRect(){
        return rect;
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    @Override
    public void onDeath() {
        if (size <= 1) return;
        SlimeEntity slime1 = new SlimeEntity(size - 1);
        SlimeEntity slime2 = new SlimeEntity(size - 1);
        Spellshocked.getInstance().world.replaceEntity(this,
                slime1,
                slime2);
        slime1.setPosition(this.getX()+10, this.getY()+10);
        slime2.setPosition(this.getX()-10, this.getY()-10);
    }
}
