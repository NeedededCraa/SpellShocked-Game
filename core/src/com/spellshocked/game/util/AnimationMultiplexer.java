package com.spellshocked.game.util;

import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Map;

public class AnimationMultiplexer<T, U> extends Animation<T> {

    public Map<U, T[]> animationMap;
    public U currentSelection;

    public AnimationMultiplexer(float frameDuration, Map<U, T[]> map){
        super(frameDuration, map.values().stream().findFirst().get()[0]);
        animationMap = map;
    }

    public void chooseAnimation(U selector){
        setKeyFrames(animationMap.get(selector));
    }
    public U getCurrent(){
        return currentSelection;
    }
}
