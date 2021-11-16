package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.world.World;

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
//        instructions.setSize(600, 100);
//        instructions.setPosition(100, 260);
        instructions.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        instructions.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));
        addActor(instructions);

        TextButton settings = new TextButton("Game Settings", skin);
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.settingsGUI);
            }
        });
//        settings.setSize(600, 100);
//        settings.setPosition(100, 140);
        settings.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        settings.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));
        addActor(settings);

        TextButton quit = new TextButton("Quit", skin);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
//        quit.setSize(275, 100);
//        quit.setPosition(100, 20);
        quit.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        quit.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/24f));
        addActor(quit);

        TextButton start = new TextButton("Start", skin);
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.world = new World(g);
                g.setScreen(g.world);
            }
        });

//        start.setSize(275, 100);
//        start.setPosition(425, 20);
        start.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        start.setPosition((Gdx.graphics.getWidth()/1.88f), (Gdx.graphics.getHeight()/24f));
        addActor(start);
    }
}



