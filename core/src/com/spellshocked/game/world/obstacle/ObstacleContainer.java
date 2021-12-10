package com.spellshocked.game.world.obstacle;

import com.spellshocked.game.gui.BlockInventoryGUI;
import com.spellshocked.game.item.inventory.Inventory;

public class ObstacleContainer extends ObstacleEntity<BlockInventoryGUI> {
    public Inventory inventory;

    public ObstacleContainer(String jsonPath, BlockInventoryGUI s, Inventory i) {
        super(jsonPath, s.setInventory(i));
        inventory = i;
    }

}
