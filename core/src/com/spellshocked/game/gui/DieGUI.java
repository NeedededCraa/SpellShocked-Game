package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class DieGUI extends GUI{
    public DieGUI(Spellshocked g){
        super("./pixthulhu/skin/pixthulhu-ui.json");

        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.titleGUI);
                g.world.dispose();
            }
        });
//        newGame.setSize(600, 100);
//        newGame.setPosition(100, 260);
        newGame.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        newGame.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));
        addActor(newGame);

        TextButton submit_score = new TextButton("Submit Score", skin);
        submit_score.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.basicScoreSender.submit_to_leaderboard(1);
            }
        });
        submit_score.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        submit_score.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));
        addActor(submit_score);

        TextButton quit = new TextButton("Quit Game", skin);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
//        quit.setSize(600, 100);
//        quit.setPosition(100, 140);
        quit.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        quit.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/16f));
        addActor(quit);

    }

}
