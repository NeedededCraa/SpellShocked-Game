package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class DieGUI extends GUI{
    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public DieGUI(Spellshocked g){
        super(SKIN);

        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {


                g.setScreen(g.titleGUI);
            }
        });
        newGame.setSize(600, 100);
        newGame.setPosition(100, 260);
        addActor(newGame);

        TextButton quit = new TextButton("Quit Game", skin);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        quit.setSize(600, 100);
        quit.setPosition(100, 140);
        addActor(quit);

    }

}
