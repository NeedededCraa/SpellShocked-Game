package com.spellshocked.game.item;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.FireballEntity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.ProjectileEntity;

public class WandItem extends WeaponItem {
    public WandItem() {
        super("./json/Inventory/Item/Weapon/wand.json");
    }

    @Override
    public void onUse(PlayerEntity p) {
        ProjectileEntity entity = new FireballEntity();
        entity.setTile(p.getTile());
        entity.setPosition(p.getX(), p.getY());
        entity.targetTile(p.getTile().findFromClick(Spellshocked.getInstance().world.mouse));
        entity.startMoving();
        Spellshocked.getInstance().world.addEntity(entity);
        entity.playSFX();
    }
}