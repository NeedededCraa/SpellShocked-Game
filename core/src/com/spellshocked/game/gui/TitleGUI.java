package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class TitleGUI extends GUI {
    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public TitleGUI(Spellshocked g) {
        super(SKIN);
        TextButton instructions = new TextButton("How to play", skin);
        //for right now just resumes game
        instructions.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.world);
            }
        });
        instructions.setSize(600, 100);
        instructions.setPosition(100, 260);
        addActor(instructions);

        TextButton settings = new TextButton("Game Settings", skin);
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        settings.setSize(600, 100);
        settings.setPosition(100, 140);
        addActor(settings);

        TextButton quit = new TextButton("Quit", skin);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        quit.setSize(275, 100);
        quit.setPosition(100, 20);
        addActor(quit);

        TextButton start = new TextButton("Start", skin);
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.world);
            }
        });

        start.setSize(275, 100);
        start.setPosition(425, 20);
        addActor(start);
    }
}



