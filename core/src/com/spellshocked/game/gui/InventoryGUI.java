package com.spellshocked.game.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.item.inventory.Inventory;

public class InventoryGUI extends GUI {
    private Spellshocked g;
    private Inventory inv;

    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public InventoryGUI(Spellshocked g1, Inventory inv1) {
        super(SKIN);
        g = g1;
        inv = inv1;
    }

    @Override
    public void render(float delta) {
        g.world.render(delta);
        super.getBatch().begin();
        //inv.draw(super.getBatch(), ortCam.position.x-144, ortCam.position.y-ortCam.zoom*120);
        super.getBatch().end();
    }
}
