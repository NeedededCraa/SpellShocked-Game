package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GUI extends Stage implements Screen {
    public Skin skin;
    public GUI(String s){
        super(new ScreenViewport());
        skin = new Skin(Gdx.files.internal(s));
    }
    public Skin getSkin(){
        return skin;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        act(Gdx.graphics.getDeltaTime());
        super.draw();
        System.out.println("X: "+Gdx.input.getX()+" Y: "+Gdx.input.getY()); //to get the mouse position
    }

    @Override
    public void resize(int width, int height) {
//        getActors();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }
}
