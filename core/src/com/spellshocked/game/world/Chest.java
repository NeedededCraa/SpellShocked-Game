package com.spellshocked.game.world;

import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.BlockInventoryGUI;

public class Chest extends Obstacle {
    protected BlockInventoryGUI chestGUI;

    public Chest(String jsonPath, Spellshocked g, PlayerEntity p) {
        super(jsonPath);
        chestGUI = new BlockInventoryGUI(g, p);
    }

    public BlockInventoryGUI getBlockInventoryGUI() { return chestGUI; }
}