package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class QuestGUI extends GUI{
    static final int line_height = 10;

    public TextButton resume;
    public Label title, dummy_text;

    public Label task_1_name, task_1_progress, task_1_description;
    public Label task_2_name, task_2_progress, task_2_description;
    public Label task_3_name, task_3_progress, task_3_description;
    public Label task_4_name, task_4_progress, task_4_description;
    public Label task_5_name, task_5_progress, task_5_description;

    public Spellshocked currentGame;

    public QuestGUI(Spellshocked g) {
        super("./pixthulhu/skin/pixthulhu-ui.json");

        title = new Label("current gamemode does not have any quest", super.skin);
        title.setPosition((Gdx.graphics.getWidth()/2.25f), (Gdx.graphics.getHeight()/1.1f));
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

        dummy_text = new Label("!", super.skin);
        dummy_text.setPosition((Gdx.graphics.getWidth()/7f), (Gdx.graphics.getHeight()/1.1f));
        addActor(dummy_text);

        /*
         * position of all others Label are relative to the first one
         */
        task_1_name = new Label("task_1_name", super.skin);
        task_1_name.setPosition((Gdx.graphics.getWidth()/7f), (Gdx.graphics.getHeight()/1.25f));
        addActor(task_1_name);
        task_1_progress = new Label("task_1_progress", super.skin);
        task_1_progress.setPosition((Gdx.graphics.getWidth()/1.5f), (task_1_name.getY()));
        addActor(task_1_progress);
        task_1_description = new Label("task_1_description", super.skin);
        task_1_description.setPosition((task_1_name.getX()), (task_1_name.getY() - task_1_name.getHeight() - line_height));
        addActor(task_1_description);

        task_2_name = new Label("task_2_name", super.skin);
        task_2_name.setPosition((task_1_name.getX()), (task_1_description.getY() - task_2_name.getHeight() - line_height*4));
        addActor(task_2_name);
        task_2_progress = new Label("task_2_progress", super.skin);
        task_2_progress.setPosition((task_1_progress.getX()), (task_2_name.getY()));
        addActor(task_2_progress);
        task_2_description = new Label("task_2_description", super.skin);
        task_2_description.setPosition((task_1_name.getX()), (task_2_name.getY() - task_2_name.getHeight() - line_height));
        addActor(task_2_description);

        task_3_name = new Label("task_3_name", super.skin);
        task_3_name.setPosition((task_1_name.getX()), (task_2_description.getY() - task_3_name.getHeight() - line_height*4));
        addActor(task_3_name);
        task_3_progress = new Label("task_3_progress", super.skin);
        task_3_progress.setPosition((task_1_progress.getX()), (task_3_name.getY()));
        addActor(task_3_progress);
        task_3_description = new Label("task_3_description", super.skin);
        task_3_description.setPosition((task_1_name.getX()), (task_3_name.getY() - task_3_name.getHeight() - line_height));
        addActor(task_3_description);

        task_4_name = new Label("task_4_name", super.skin);
        task_4_name.setPosition((task_1_name.getX()), (task_3_description.getY() - task_4_name.getHeight() - line_height*4));
        addActor(task_4_name);
        task_4_progress = new Label("task_4_progress", super.skin);
        task_4_progress.setPosition((task_1_progress.getX()), (task_4_name.getY()));
        addActor(task_4_progress);
        task_4_description = new Label("task_4_description", super.skin);
        task_4_description.setPosition((task_1_name.getX()), (task_4_name.getY() - task_4_name.getHeight() - line_height));
        addActor(task_4_description);

        task_5_name = new Label("task_5_name", super.skin);
        task_5_name.setPosition((task_1_name.getX()), (task_4_description.getY() - task_5_name.getHeight() - line_height*4));
        addActor(task_5_name);
        task_5_progress = new Label("task_5_progress", super.skin);
        task_5_progress.setPosition((task_1_progress.getX()), (task_5_name.getY()));
        addActor(task_5_progress);
        task_5_description = new Label("task_5_description", super.skin);
        task_5_description.setPosition((task_1_name.getX()), (task_5_name.getY() - task_5_name.getHeight() - line_height));
        addActor(task_5_description);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
