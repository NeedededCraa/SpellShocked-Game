package com.spellshocked.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spellshocked.game.gui.*;
import com.spellshocked.game.input.AppPreferences;
import com.spellshocked.game.gui.DieGUI;
import com.spellshocked.game.gui.PauseGUI;
import com.spellshocked.game.gui.TitleGUI;
import com.spellshocked.game.util.BasicScoreSender;
import com.spellshocked.game.util.FireScoreSender;
import com.spellshocked.game.world.World;

public class Spellshocked extends Game {

	public World world;
	public PauseGUI pauseGUI;
	public TitleGUI titleGUI;
	public DieGUI dieGUI;




	public SettingsGUI settingsGUI;
	public GameChooserGUI gameChooserGUI;
	public AppPreferences preferences;
	public QuestGUI questGUI;

	public BasicScoreSender basicScoreSender;
	public FireScoreSender fireScoreSender;



	@Override
	public void create() {
		titleGUI = new TitleGUI(this);
		setScreen(titleGUI);
		preferences = new AppPreferences();
		settingsGUI = new SettingsGUI(this);

		//settingsGUI = new SettingsGUI(this);

		dieGUI = new DieGUI(this);
		gameChooserGUI = new GameChooserGUI(this);


		//item testing
		pauseGUI = new PauseGUI(this);

		questGUI = new QuestGUI(this);

//		basicScoreSender = new BasicScoreSender("sglnrlayspe", "hello");
//		fireScoreSender =  new FireScoreSender();
	}
	@Override
	public void render() {

		super.render();
	}

	@Override
	public void dispose(){
	}

	public AppPreferences getPreferences() {
		if (preferences == null) {
			//preferences = Gdx.app.getPreferences("myPrefs");
		}
		return preferences;
	}


}