package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.Spellshocked;

public class QuestGUI extends GUI{
    public TextButton resume, update_btn;
    public Label title, dummy_text;
    int counter = 0;

    public QuestGUI(Spellshocked g) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        title = new Label("current gamemode does not have any quest", super.skin);
        title.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        title.setPosition((Gdx.graphics.getWidth()/3f), (Gdx.graphics.getHeight()/1.2f));
        addActor(title);

        dummy_text = new Label(String.valueOf(counter), super.skin);
        dummy_text.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        dummy_text.setPosition((Gdx.graphics.getWidth()/3f), (Gdx.graphics.getHeight()/2f));
        addActor(dummy_text);

        resume = new TextButton("Resume Game", super.skin);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.world);
            }
        });
        resume.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        resume.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/32f));
        addActor(resume);
    }

    public QuestGUI(Spellshocked g, String quest_jsonPath) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        JsonValue jsonContent = new JsonReader().parse(Gdx.files.internal(quest_jsonPath));
//        jsonContent.get("quest_page").

        JsonValue.JsonIterator it = jsonContent.get("quest_page").iterator();

        for (int i = 1; i <= jsonContent.getInt("total_quest_page"); i++){
            System.out.println(it.next().asInt());
        }

        title = new Label(jsonContent.getString("gamemode_name"), super.skin);
        title.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        title.setPosition((Gdx.graphics.getWidth()/2.2f), (Gdx.graphics.getHeight()/1.2f));
        addActor(title);

        resume = new TextButton("Resume Game", super.skin);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                g.setScreen(g.world);
            }
        });
        resume.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        resume.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/32f));
        addActor(resume);
    }

    public void update(){
        /*
        * Should not addActor() unless removed the old one
        */
        if (dummy_text != null) dummy_text.setText(String.valueOf(counter));
    }

    @Override
    public void render(float delta) {
        counter++;
        super.render(delta);
    }
}
