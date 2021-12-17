package com.spellshocked.game.item;

import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.world.World;

@FunctionalInterface
public interface Placeable {
    void onPlace(World w, PlayerEntity p);

}
