package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class TitleGUI extends GUI {
    public TextButton witchHunt, shockwave, runeRun;

    public TitleGUI(Spellshocked g) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        TextButton chooseMode = new TextButton("Game Mode", skin);
        chooseMode.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.gameChooserGUI);
            }
        });
//        chooseMode.setSize(1443.6f, 212.5f);
//        chooseMode.setPosition(240f, 531.25f);
        chooseMode.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        chooseMode.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.92f));
        addActor(chooseMode);

                g.setScreen(g.gameChooserGUI);
            }
        });
//        quit.setSize(600, 100);
//        quit.setPosition(100, 20);
        witchHunt.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        witchHunt.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/24f));

        TextButton leaderboard = new TextButton("Leaderboard", skin);
        leaderboard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Unfortunately we don't have one right now");
            }
        });
//        leaderboard.setSize(1443.6f, 212.5f);
//        leaderboard.setPosition(240f, 286.875f);
        leaderboard.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        leaderboard.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.555f));
        addActor(leaderboard);

        TextButton setting = new TextButton("Settings", skin);
        setting.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.settingsGUI);
            }
        });
//        setting.setSize(662f, 212.5f);
//        setting.setPosition(240f, 42.5f);
        setting.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        setting.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/24f));
        addActor(setting);

        addActor(witchHunt);

        shockwave = new TextButton("Shock Wave",skin);
        shockwave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.gameChooserGUI);
            }
        });
//        resume.setSize(600, 100);
//        resume.setPosition(100, 140);
        shockwave.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        shockwave.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));

        addActor(shockwave);

        runeRun = new TextButton("Rune Run", skin);
        runeRun.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.gameChooserGUI);



            }
        });
//        settings.setSize(600, 100);
//        settings.setPosition(100, 260);
        runeRun.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        runeRun.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));

        addActor(runeRun);

//        quit.setSize(662f, 212.5f);
//        quit.setPosition(970f, 42.5f);
        quit.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        quit.setPosition((Gdx.graphics.getWidth()/1.88f), (Gdx.graphics.getHeight()/24f));
        addActor(quit);
    }
}

