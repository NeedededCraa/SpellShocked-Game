package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.world.PumpkinRush;
import com.spellshocked.game.world.ShockWaveMode;

public class GameChooserGUI extends GUI{
    public TextButton pumpkinRush, shockwave, ruinRun, tutorial, titleScreen;
    public GameChooserGUI() {
        super("pixthulhu/skin/pixthulhu-ui.json");


        shockwave = new TextButton("Shock Wave",skin);
        shockwave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Spellshocked.getInstance().world = new ShockWaveMode();
                Spellshocked.getInstance().setScreen(Spellshocked.getInstance().world);
            }
        });
        shockwave.setSize((Gdx.graphics.getWidth()/1.75f), (Gdx.graphics.getHeight()/4.8f));
        shockwave.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.5f));
//        shockwave.setPosition((Gdx.graphics.getWidth()/8f), (531.25f));
        addActor(shockwave);

        Texture w = new Texture("image/Inventory/Item/wandBig.png");
        Image wand = new Image(w);
        wand.setSize((Gdx.graphics.getWidth()/10f), (Gdx.graphics.getHeight()/7f));
        wand.setPosition((Gdx.graphics.getWidth()/1.38f), (Gdx.graphics.getHeight()/1.43f));
        addActor(wand);

        pumpkinRush = new TextButton("Pumpkin Rush", skin);
        pumpkinRush.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Spellshocked.getInstance().world = new PumpkinRush();
                Spellshocked.getInstance().setScreen(Spellshocked.getInstance().world);
            }
        });
        pumpkinRush.setSize((Gdx.graphics.getWidth()/1.75f), (Gdx.graphics.getHeight() / 4.8f));
        pumpkinRush.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/2.8f));
//        pumpkinRush.setPosition((Gdx.graphics.getWidth() / 8f), (286.875f));
        addActor(pumpkinRush);

        Texture p = new Texture("image/World/Object/pumpkin.png");
        Image pumpkin = new Image(p);
        pumpkin.setSize((Gdx.graphics.getWidth()/10f), (Gdx.graphics.getHeight()/7f));
        pumpkin.setPosition((Gdx.graphics.getWidth()/1.38f), (Gdx.graphics.getHeight()/2.55f));
        addActor(pumpkin);

        tutorial = new TextButton("Tutorial", skin);
        tutorial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Spellshocked.getInstance().setScreen(Spellshocked.getInstance().tutorial);
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
                Spellshocked.getInstance().setScreen(Spellshocked.getInstance().titleGUI);
            }
        });
        titleScreen.setSize((Gdx.graphics.getWidth()/2.9f), (Gdx.graphics.getHeight()/4.8f));
        titleScreen.setPosition((Gdx.graphics.getWidth()/1.88f), (Gdx.graphics.getHeight()/24f));
//        titleScreen.setPosition((Gdx.graphics.getWidth()/1.88f), (42.5f));
        addActor(titleScreen);
    }
}
