package com.spellshocked.game.input;

import com.badlogic.gdx.Gdx;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

@FunctionalInterface
public interface FunctionalInput {
    boolean input();

    default boolean isTrue(){
        return input();
    }

    default boolean isFalse(){
        return !input();
    }

    default FunctionalInput onTrue(Runnable r){
        return schedule(new ConditionalRunnable(r, this::isTrue));
    }
    default FunctionalInput onFalse(Runnable r){
        return schedule(new ConditionalRunnable(r, this::isFalse));
    }
    default FunctionalInput schedule(Runnable r){
        InputScheduler.getInstance().schedule(r);
        return this;
    }
    static FunctionalInput from(BooleanSupplier b){
        return b::getAsBoolean;
    }

    static FunctionalInput fromKeyPress(int... is){
        return from(()->{
            for(int i : is) if(!Gdx.input.isKeyPressed(i)) return false;
            return true;
        });
    }
    static FunctionalInput fromKeyJustPress(int i){
        return from(()-> Gdx.input.isKeyJustPressed(i));
    }
    static void keyJustPressedMultiplexer(IntConsumer cons, int... is){
        for(int i = 0; i<is.length; i++){
            int finalI = i;
            fromKeyJustPress(is[i]).onTrue(()->cons.accept(finalI));
        }
    }
}
