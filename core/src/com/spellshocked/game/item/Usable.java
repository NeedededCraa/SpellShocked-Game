package com.spellshocked.game.item;

import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.inventory.Inventory;

@FunctionalInterface
public interface Usable {
    void onUse(PlayerEntity p);
}
