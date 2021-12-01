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
    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public TextButton witchHunt, shockwave, ruinRun, tutorial, titleScreen;
    public GameChooserGUI(Spellshocked g) {
        super(SKIN);

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

        shockwave = new TextButton("Shock Wave",skin);
        shockwave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
