package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.world.RuinRunMode;
import com.spellshocked.game.world.ShockWaveMode;
import com.spellshocked.game.world.WitchHuntMode;

public class GameChooserGUI extends GUI{
    public TextButton witchHunt, shockwave, ruinRun, tutorial, titleScreen;
    public GameChooserGUI(Spellshocked g) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        ruinRun = new TextButton("Ruin Run", skin);
        ruinRun.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //g.setScreen(g.settingsGUI);
                g.world = new RuinRunMode(g);
                g.setScreen(g.world);
            }
        });
        ruinRun.setSize((Gdx.graphics.getWidth() / 1.33f), (Gdx.graphics.getHeight() / 4.8f));
        ruinRun.setPosition((Gdx.graphics.getWidth() / 8f), (Gdx.graphics.getHeight() / 1.315f));
//        ruinRun.setPosition((Gdx.graphics.getWidth() / 8f), (775.625f));
        addActor(ruinRun);

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
                g.world = new ShockWaveMode(g);
                g.setScreen(g.world);
            }
        });
        shockwave.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        shockwave.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.92f));
//        shockwave.setPosition((Gdx.graphics.getWidth()/8f), (531.25f));
        addActor(shockwave);

        witchHunt = new TextButton("Witch Hunt", skin);
        witchHunt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.world = new WitchHuntMode(g);
                g.setScreen(g.world);
            }
        });
        witchHunt.setSize((Gdx.graphics.getWidth() / 1.33f), (Gdx.graphics.getHeight() / 4.8f));
        witchHunt.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.555f));
//        witchHunt.setPosition((Gdx.graphics.getWidth() / 8f), (286.875f));
        addActor(witchHunt);

        tutorial = new TextButton("Tutorial", skin);
        tutorial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Unfortunately we don't have one right now");
            }
        });
        tutorial.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        tutorial.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/24f));
//        tutorial.setPosition((Gdx.graphics.getWidth()/8f), (42.5f));
        addActor(tutorial);

        titleScreen = new TextButton("Back", skin);
        titleScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.titleGUI);
            }
        });
        titleScreen.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        titleScreen.setPosition((Gdx.graphics.getWidth()/1.88f), (Gdx.graphics.getHeight()/24f));
//        titleScreen.setPosition((Gdx.graphics.getWidth()/1.88f), (42.5f));
        addActor(titleScreen);
    }

}
