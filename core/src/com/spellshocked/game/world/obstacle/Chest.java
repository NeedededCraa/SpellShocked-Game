package com.spellshocked.game.world.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.BlockInventoryGUI;
import com.spellshocked.game.item.inventory.Inventory;

public class Chest extends ObstacleContainer {
    Texture opened, closed;
    public Chest(PlayerEntity p) {
        super("./json/Object/chest.json", new BlockInventoryGUI(p), new Inventory(5, "./json/Object/chest.json"));
        closed = getTexture("texture");
        opened = getTexture("texture2");
    }

    @Override
    public Texture getTexture() {
        return getGui().isDisplaying() ? opened : closed;
    }
}

