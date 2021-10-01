package com.spellshocked.game.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item extends Sprite {
    protected String name;
    protected String description;
    protected TextureRegion image;
    protected float baseValue;
    protected int baseQuantity;
    protected float rarityIndex;
    protected String[] tags;
    protected float hitPoints;
    //constructors
    public Item(){

    }
    public String getName(){
        return this.name;
    }


    /**
     * @return nothing, just for test is this class being imported properly
     */
    public static String hello(){
        return "Hello World! from com.spellshocked.game.myPlayer";
    }
}
