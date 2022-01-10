package com.spellshocked.game.input;

import java.util.*;
import java.util.function.BooleanSupplier;

public class InputScheduler {

    public final Map<Integer, Boolean> buttonPressedThisLoop;

    private Runnable[] inputs;
    private int index;
    public static final int RUNNABLE_CAPACITY = 1024;
    private static InputScheduler instance;
    public static InputScheduler getInstance(){
        if(instance == null) instance = new InputScheduler(RUNNABLE_CAPACITY);
        return instance;
    }
    public static InputScheduler resetInstance(){
        instance = null;
        return getInstance();
    }

    public InputScheduler(int capacity){
        inputs = new Runnable[capacity];
        index = 0;
        buttonPressedThisLoop = new HashMap<>();
    }

    public InputScheduler(){
        this(100);
    }
    public InputScheduler schedule(Runnable... runnables){
        for(Runnable r : runnables) inputs[index++] = r;
        return this;
    }


    public void run(){
        for(Runnable r : inputs) {
            if(r==null) break;
            r.run();
        }
        buttonPressedThisLoop.replaceAll((i, b)->false);
    }
}
