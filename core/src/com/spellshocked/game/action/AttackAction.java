package com.spellshocked.game.action;

import com.spellshocked.game.entity.Entity;

public class AttackAction<T extends Entity, U extends Entity> implements Action<T, U, Double>{


    @Override
    public boolean act(T actor, U target, Double amount) {
        return actor.getTile().distanceFrom(target.getTile())<4 && !target.invincible;
    }

    @Override
    public void onSuccess(T actor, U target, Double amount) {
        target.modifyHealth(-amount);
    }

}
