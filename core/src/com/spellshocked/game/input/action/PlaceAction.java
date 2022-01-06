package com.spellshocked.game.input.action;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.Placeable;
import com.spellshocked.game.world.obstacle.Pumpkin;

public class PlaceAction implements Runnable {
    public PlayerEntity entity;
    public PlaceAction(PlayerEntity e){
        entity = e;
    }
    @Override
    public void run() {
        if(!(entity.hotbar.getActiveSlot() instanceof Placeable) || !entity.getTile().isClickInRange(Spellshocked.getInstance().world.mouse) || entity.getTile().obstacle != null) return;
        ((Placeable) entity.hotbar.getActiveSlot()).onPlace(Spellshocked.getInstance().world, entity);
        entity.getTile().setObstacle(new Pumpkin(entity));


    }
}
