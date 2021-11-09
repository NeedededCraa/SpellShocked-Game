package com.spellshocked.game.action;

@FunctionalInterface
public interface Action<P, T, U> {

    boolean act(P actor, T target, U amount);

    default void run(P actor, T target, U amount){
        if(act(actor, target, amount)) onSuccess(actor, target, amount);
        else onFailure(actor, target, amount);
    }

    default void onSuccess(P actor, T target, U amount) {

    }
    default void onFailure(P actor, T target, U amount) {

    }

}
