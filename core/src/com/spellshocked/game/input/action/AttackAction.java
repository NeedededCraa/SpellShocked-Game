package com.spellshocked.game.input.action;

import com.badlogic.gdx.Input;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.item.Usable;


public class AttackAction implements Runnable{
    public PlayerEntity entity;
    public AttackAction(PlayerEntity e){
        entity = e;
    }
    @Override
    public void run() {
        InputScheduler.getInstance().buttonPressedThisLoop.put(Input.Buttons.LEFT, true);
        if(!(entity.hotbar.getActiveSlot() instanceof Usable)) return;
        ((Usable) entity.hotbar.getActiveSlot()).onUse(entity);
    }
}
