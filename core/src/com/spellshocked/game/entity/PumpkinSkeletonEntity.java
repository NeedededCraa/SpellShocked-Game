package com.spellshocked.game.entity;

import com.spellshocked.game.world.obstacle.Pumpkin;

public class PumpkinSkeletonEntity extends SkeletonEntity{
    Pumpkin pumpkin;
    boolean isInAttackRange=true;
    public PumpkinSkeletonEntity(Pumpkin p){
        super();
        pumpkin = p;

    }
    public Pumpkin getPumpkin(){
        return pumpkin;
    }
    public boolean isInAttackRange(){
        return isInAttackRange;
    }
    public void setInAttackRange(boolean value){
        isInAttackRange = value;
    }
}

