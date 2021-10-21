package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GUI extends Stage {
    public Skin skin;
    public boolean isActive;
    public GUI(String s){
        super(new ScreenViewport());
        skin = new Skin(Gdx.files.internal(s));
    }

    public void activate(){
        Gdx.input.setInputProcessor(this);
        isActive = true;
    }
    public void deactivate(){
        Gdx.input.setInputProcessor(null);
        isActive = false;
    }
    public void toggleActive(){
        if (isActive) deactivate();
        else activate();
    }
    @Override
    public void draw(){
        if(isActive){
            act(Gdx.graphics.getDeltaTime());
            super.draw();
        }
    }
    public Skin getSkin(){
        return skin;
    }


}
