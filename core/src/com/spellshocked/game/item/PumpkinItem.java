package com.spellshocked.game.item;

import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.Consumable;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;

public class PumpkinItem extends Item implements Consumable {
    public PumpkinItem() {
        super("./json/Inventory/Item/Weapon/pumpkin.json");
    }

    @Override
    public void onConsume(PlayerEntity p) {
        p.setWalkSpeed(p.getWalkSpeed()+1);
        p.hotbar.remove(this);
    }
}
