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
    public TextButton witchHunt, shockwave, ruinRun;
    public GameChooserGUI(Spellshocked g) {
        super(SKIN);

        witchHunt = new TextButton("Witch Hunt", skin);
        witchHunt.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                g.world = new WitchHuntMode(g);
                g.setScreen(g.world);
            }
        });
//        quit.setSize(600, 100);
//        quit.setPosition(100, 20);
        witchHunt.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        witchHunt.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/24f));


        addActor(witchHunt);

        shockwave = new TextButton("Shock Wave",skin);
        shockwave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                g.world = new ShockWaveMode(g);
                g.setScreen(g.world);
            }
        });
//        resume.setSize(600, 100);
//        resume.setPosition(100, 140);
        shockwave.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        shockwave.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));

        addActor(shockwave);

        ruinRun = new TextButton("Ruin Run", skin);
        ruinRun.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //g.setScreen(g.settingsGUI);
                g.world = new RuinRunMode(g);
                g.setScreen(g.world);


            }
        });
//        settings.setSize(600, 100);
//        settings.setPosition(100, 260);
        ruinRun.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        ruinRun.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));

        addActor(ruinRun);

    }
}
