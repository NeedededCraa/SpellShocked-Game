package com.spellshocked.game;

import com.badlogic.gdx.Game;
import com.spellshocked.game.gui.DieGUI;
import com.spellshocked.game.gui.PauseGUI;
import com.spellshocked.game.world.World;

public class Spellshocked extends Game {
	public World world;
	public PauseGUI pause;
	public TitleGUI titleGUI;
	public DieGUI dieGUI;
//	private PauseGUI gui;
	@Override
	public void create() {
		//titleGUI = new TitleGUI(this);
		setScreen(titleGUI);

		dieGUI = new DieGUI(this);


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
}