package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class DieGUI extends GUI{
    public Label reason, score_text, score_number;

    public DieGUI(Spellshocked g){
        super("./pixthulhu/skin/pixthulhu-ui.json");
        reason = new Label("you died because ... ", skin);
        reason.setPosition(800, 900);
        addActor(reason);

        score_text = new Label("your score is: ", skin);
        score_text.setPosition(800, 830);
        addActor(score_text);

        score_number = new Label("", skin);
        score_number.setPosition(1000, 850);
        addActor(score_number);

        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.gameChooserGUI);
                g.world.dispose();
            }
        });
        newGame.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        newGame.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));
        addActor(newGame);

        TextButton submit_score = new TextButton("Submit Score", skin);
        submit_score.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                g.basicScoreSender.submit_to_leaderboard(1);
//                g.fireScoreSender.testExampleData();
                System.out.println("Unfortunately we don't have one right now");
            }
        });
        submit_score.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        submit_score.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));
        addActor(submit_score);

        TextButton back = new TextButton("Title Screen", skin);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.titleGUI);
                g.world.dispose();
            }
        });
        back.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        back.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/16f));
        addActor(back);
    }
}