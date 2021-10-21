package com.spellshocked.game.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spellshocked.game.Spellshocked;

public class PauseGUI extends GUI {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public PauseGUI(Game game){
        this.game = game;
    }

}
