package com.spellshocked.game.item;

import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.inventory.Inventory;

@FunctionalInterface
public interface Consumable {
    void onConsume(PlayerEntity p);
}
