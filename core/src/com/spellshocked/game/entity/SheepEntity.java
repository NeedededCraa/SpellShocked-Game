package com.spellshocked.game.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.input.FunctionalInput;

public class SheepEntity extends Entity{
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./images/entities/sheep.png"), 28, 17);
    public static final float WALKSPEED = 1;

    public SheepEntity() {
        super(TEXTURES, WALKSPEED);
        setSize(0.3f, 0.2f);
        setPosition(250, 120);
        sheepControls();
    }
    public void sheepControls() {
        FunctionalInput.fromKeyPress(Input.Keys.UP).onTrue(this::moveUp);
        FunctionalInput.fromKeyPress(Input.Keys.DOWN).onTrue(this::moveDown);
        FunctionalInput.fromKeyPress(Input.Keys.LEFT).onTrue(this::moveLeft);
        FunctionalInput.fromKeyPress(Input.Keys.RIGHT).onTrue(this::moveRight);
    }
}
