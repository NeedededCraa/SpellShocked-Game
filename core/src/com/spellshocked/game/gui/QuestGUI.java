package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class QuestGUI extends GUI{
    public TextButton resume;
    public Label title;

    public QuestGUI(Spellshocked g) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        title = new Label("current gamemode does not have any quest", super.skin);
        title.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        title.setPosition((Gdx.graphics.getWidth()/3f), (Gdx.graphics.getHeight()/2f));
        addActor(title);

        resume = new TextButton("Resume Game", super.skin);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.world);
            }
        });
        resume.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        resume.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/8f));
        addActor(resume);
    }

    public QuestGUI(Spellshocked g, String quest_file_path) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        title = new Label("current gamemode does not have any quest", super.skin);
        title.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        title.setPosition((Gdx.graphics.getWidth()/3f), (Gdx.graphics.getHeight()/2f));
        addActor(title);

        resume = new TextButton("Resume Game", super.skin);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.world);
            }
        });
        resume.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        resume.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/8f));
        addActor(resume);
    }
}
