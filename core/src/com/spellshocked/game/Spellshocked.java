package com.spellshocked.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.gui.*;
import com.spellshocked.game.input.AppPreferences;
import com.spellshocked.game.gui.DieGUI;
import com.spellshocked.game.gui.PauseGUI;
import com.spellshocked.game.gui.TitleGUI;
import com.spellshocked.game.world.World;

public class Spellshocked extends Game {
	private static Spellshocked instance;
	public static Spellshocked getInstance(){
		if (instance == null) instance = new Spellshocked();
		return instance;
	}

	public World world;
	public PauseGUI pauseGUI;
	public TitleGUI titleGUI;
	public DieGUI dieGUI;
	public Tutorial tutorial;
	public SettingsGUI settingsGUI;

	//public SettingsGUI settingsGUI;
	public GameChooserGUI gameChooserGUI;
	//public AppPreferences preferences;
	public QuestGUI questGUI;


	@Override
	public void create() {

		titleGUI = new TitleGUI();
		setScreen(titleGUI);;
		tutorial = new Tutorial();


		dieGUI = new DieGUI(this);
		gameChooserGUI = new GameChooserGUI();
//
//
//		//item testing
		pauseGUI = new PauseGUI();
//
		questGUI = new QuestGUI(this);

//		basicScoreSender = new BasicScoreSender("sglnrlayspe", "hello");
//		fireScoreSender =  new FireScoreSender();
	}



}