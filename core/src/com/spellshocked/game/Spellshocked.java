package com.spellshocked.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.spellshocked.game.gui.*;
import com.spellshocked.game.input.AppPreferences;
import com.spellshocked.game.world.World;

public class Spellshocked extends Game {
	public World world;
	public PauseGUI pause;
	public TitleGUI titleGUI;
	public DieGUI dieGUI;
	private PauseGUI gui;
	public SettingsGUI settingsGUI;
	public AppPreferences preferences;
	public GameChooserGUI gameChooserGUI;
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


		pause = new PauseGUI(this);

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