package com.spellshocked.game.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.item.CollisionRect;

public class SkeletonEntity extends Entity{
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./image/Entity/SkeletonEntity/skeleton.png"), 17, 25);
    public static final float WALKSPEED = 1;
    public SkeletonEntity() {
        super(TEXTURES, WALKSPEED);
        setSize(0.34f, 0.5f);
        setPosition(250, 120);
        sheepControls();
        rect = new CollisionRect(getX(), getY(), getTexture().getWidth(), getTexture().getHeight());
        setRegion(TEXTURES[3][0]);
        health = 10;
    }
    public void sheepControls() {
//        FunctionalInput.fromKeyPress(Input.Keys.UP).onTrue(this::moveUp);
//        FunctionalInput.fromKeyPress(Input.Keys.DOWN).onTrue(this::moveDown);
//        FunctionalInput.fromKeyPress(Input.Keys.LEFT).onTrue(this::moveLeft);
//        FunctionalInput.fromKeyPress(Input.Keys.RIGHT).onTrue(this::moveRight);

        FunctionalInput.fromKeyJustPress(Input.Keys.L).onTrue(this::startMoving);
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
    public void update(){

    }

    public CollisionRect getRect(){
        return rect;
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}
