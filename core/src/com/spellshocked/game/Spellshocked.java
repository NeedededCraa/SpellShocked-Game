package com.spellshocked.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import com.spellshocked.game.gui.GUI;
import com.spellshocked.game.gui.PauseGUI;
import com.spellshocked.game.gui.TitleGUI;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.Obstacle;
import com.spellshocked.game.world.World;

import java.util.Arrays;

import static com.badlogic.gdx.Input.Keys;

public class Spellshocked extends Game {
	public World world;
	public PauseGUI pause;
//	private PauseGUI gui;
	@Override
	public void create() {

		world = new World(64, 64);//512, 512);

		setScreen(new TitleGUI(this));
		//item testing


		pause = new PauseGUI(this);
		FunctionalInput.fromKeyJustPress(Keys.ESCAPE).onTrue(()->setScreen(pause));



	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose(){
	}
}