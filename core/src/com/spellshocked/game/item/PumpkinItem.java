package com.spellshocked.game.item;

import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.world.World;

public class PumpkinItem extends Item implements Usable, Placeable {
    public PumpkinItem() {
        super("./json/Inventory/Item/Weapon/pumpkin.json");
    }

    @Override
    public void onUse(PlayerEntity p) {
        p.setWalkSpeed(p.getWalkSpeed()+1);
        p.entityNear().forEach((entity1, aDouble) -> entity1.modifyHealth(-5));
        remove(p);
    }

    @Override
    public void onPlace(World w, PlayerEntity p) {
        if(p.getTile().obstacle != null) return;
        remove(p);
    }
}
