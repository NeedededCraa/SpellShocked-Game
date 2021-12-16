package com.spellshocked.game.input.action;

import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.Usable;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;

public class AttackAction implements Runnable{
    public PlayerEntity entity;
    public DoubleUnaryOperator operator = d -> 5;
    public AttackAction(PlayerEntity e){
        entity = e;
    }
    @Override
    public void run() {
        if(!(entity.hotbar.getActiveSlot() instanceof Usable)) return;
        entity.entityNear().forEach((entity1, aDouble) -> entity1.modifyHealth(-operator.applyAsDouble(aDouble)));
        ((Usable) entity.hotbar.getActiveSlot()).onUse(entity);
    }
}
