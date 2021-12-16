package com.spellshocked.game.item.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.item.Item;

public class Hotbar extends Inventory {
    public static String JSON = "./json/Inventory/Hotbar/Hotbar.json";
    private int activeSlot;
    private Texture select;


    public Hotbar(int size) {
        super(size, JSON);
        activeSlot = 0;
        select = new Texture(jsonContent.getString("select"));
    }

    public Item getActiveSlot() {
        return inventory[activeSlot];
    }

    public void setActiveSlot(int newActiveSlot) {
        activeSlot = newActiveSlot;
    }

    @Override
    public void draw(Batch b, float x, float y) {

        super.draw(b, x, y);
        b.draw(select, x+activeSlot*32, y,  32, 32);

    }
}
