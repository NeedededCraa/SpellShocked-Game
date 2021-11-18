package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.world.World;

public class TitleGUI extends GUI {
    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public TextButton witchHunt, shockwave, runeRun;
    public TitleGUI(Spellshocked g) {
        super(SKIN);

        witchHunt = new TextButton("Witch Hunt", skin);
        witchHunt.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                g.setScreen(g.gameChooserGUI);
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

    }
}

