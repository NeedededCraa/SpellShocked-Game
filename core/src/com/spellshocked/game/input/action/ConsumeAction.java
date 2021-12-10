package com.spellshocked.game.input.action;

import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.Consumable;

public class ConsumeAction implements Runnable {
    public PlayerEntity entity;
    public ConsumeAction(PlayerEntity e){
        entity = e;
    }
    @Override
    public void run() {
        if(!(entity.hotbar.getActiveSlot() instanceof Consumable)) return;
        ((Consumable) entity.hotbar.getActiveSlot()).onConsume(entity);

    }
}
