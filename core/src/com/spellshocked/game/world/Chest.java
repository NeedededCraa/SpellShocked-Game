package com.spellshocked.game.world;

import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.BlockInventoryGUI;
import com.spellshocked.game.item.inventory.Inventory;

public class Chest extends Obstacle{
  protected BlockInventoryGUI chestGUI;
    protected Inventory chestInventory;
    public Chest(String jsonPath, Spellshocked g, PlayerEntity p) {
        super(jsonPath);
        chestInventory = new Inventory(5, jsonPath);
        chestGUI = new BlockInventoryGUI(g, p, chestInventory);
    }

    public BlockInventoryGUI getBlockInventoryGUI() { return chestGUI; }
}

