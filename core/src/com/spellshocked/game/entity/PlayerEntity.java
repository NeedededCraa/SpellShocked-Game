package com.spellshocked.game.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.item.CollisionRect;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Hotbar;
import com.spellshocked.game.player.Team;

public class PlayerEntity extends Entity {
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./image/Entity/PlayerEntity/player.png"), 16, 24);
    public static final float WALKSPEED = 1;
    CollisionRect rect;

    public Hotbar inventory;
    public int id;
    public Team team;

    public PlayerEntity() {
        super(TEXTURES, WALKSPEED);
        inventory = new Hotbar(9);
        setSize(0.2f, 0.4f);
        setPosition(200, 120);
        inventory.set(3, new Item("./json/Inventory/Item/Weapon/bucket.json"));
        playerControls();
        //rect = new CollisionRect(this.getX(), this.getY(), this.getRegionWidth(), this.getHeight());
    }
    public void playerControls(){
        FunctionalInput.fromKeyPress(Input.Keys.W).onTrue(this::moveUp);
        FunctionalInput.fromKeyPress(Input.Keys.S).onTrue(this::moveDown);
        FunctionalInput.fromKeyPress(Input.Keys.A).onTrue(this::moveLeft);
        FunctionalInput.fromKeyPress(Input.Keys.D).onTrue(this::moveRight);
        FunctionalInput.keyJustPressedMultiplexer(inventory::setActiveSlot,
                Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4, Input.Keys.NUM_5, Input.Keys.NUM_6, Input.Keys.NUM_7, Input.Keys.NUM_8, Input.Keys.NUM_9);
    }

    @Override
    public void draw(Batch batch) {
        inventory.draw(batch, ortCam.position.x-144, ortCam.position.y-ortCam.zoom*120);
        super.draw(batch);
    }
}
