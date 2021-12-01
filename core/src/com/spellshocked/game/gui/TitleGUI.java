package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.world.World;

public class TitleGUI extends GUI {
    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public TitleGUI(Spellshocked g) {
        super(SKIN);

        TextButton chooseMode = new TextButton("Game Mode", skin);
        chooseMode.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.gameChooserGUI);
            }
        });
//        chooseMode.setSize(600, 100);
//        chooseMode.setPosition(100, 260);
        chooseMode.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        chooseMode.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));
        addActor(chooseMode);

        TextButton leaderboard = new TextButton("Leaderboard", skin);
        leaderboard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Unfortunately we don't have one right now");
            }
        });
//        leaderboard.setSize(600, 100);
//        leaderboard.setPosition(100, 140);
        leaderboard.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        leaderboard.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));
        addActor(leaderboard);

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

        TextButton setting = new TextButton("Settings", skin);
        setting.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.settingsGUI);
            }
        });
//        setting.setSize(275, 100);
//        setting.setPosition(425, 20);
        setting.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        setting.setPosition((Gdx.graphics.getWidth()/1.88f), (Gdx.graphics.getHeight()/24f));
        addActor(setting);
    }
}

