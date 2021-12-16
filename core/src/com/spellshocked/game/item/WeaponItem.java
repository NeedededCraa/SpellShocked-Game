package com.spellshocked.game.item;

import com.spellshocked.game.entity.PlayerEntity;

public class WeaponItem extends Item implements Usable {
    public WeaponItem(String path) {
        super(path);
    }

    @Override
    public void onUse(PlayerEntity p) {

    }
}
