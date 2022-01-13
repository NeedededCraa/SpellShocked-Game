package com.spellshocked.game.entity;

import com.spellshocked.game.world.obstacle.Pumpkin;

public class PumpkinSheepEntity extends SheepEntity{
    Pumpkin pumpkin;
    boolean isInAttackRange=true;
    public PumpkinSheepEntity(Pumpkin p){
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

