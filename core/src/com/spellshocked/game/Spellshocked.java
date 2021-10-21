package com.spellshocked.game;

import com.badlogic.gdx.ApplicationAdapter;
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
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.Obstacle;
import com.spellshocked.game.world.World;

import java.util.Arrays;

import static com.badlogic.gdx.Input.Keys;

public class Spellshocked extends ApplicationAdapter {
	private World world;
	private SpriteBatch b;
	private OrthographicCamera c;
	private PlayerEntity p;
	private SheepEntity s;
	private GUI gui;
	@Override
	public void create() {
		world = new World(64, 64);//512, 512);
		b = new SpriteBatch();
		c = new OrthographicCamera(400, 240);
		c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);
		p = new PlayerEntity();
		s = new SheepEntity();
		p.followWithCamera(c);
		p.setSize(0.2f, 0.4f);
		s.setSize(0.3f, 0.2f);
		p.setPosition(200, 120);
		s.setPosition(250, 120);
		world.addEntity(s);
		world.addEntity(p);
		//item testing
		Obstacle pebble = new Obstacle("./jsons/Obstacle.json");

		gui = new GUI("./pixthulhu/skin/pixthulhu-ui.json");
		TextButton quit = new TextButton("Quit Game", gui.skin);
		quit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		quit.setSize(600, 100);
		quit.setPosition(100, 20);
		gui.addActor(quit);

		TextButton resume = new TextButton("Resume Game", gui.skin);
		resume.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				gui.deactivate();
			}
		});
		resume.setSize(600, 100);
		resume.setPosition(100, 140);
		gui.addActor(resume);

		TextButton settings = new TextButton("Game Settings", gui.skin);
		settings.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {

			}
		});
		settings.setSize(600, 100);
		settings.setPosition(100, 260);
		gui.addActor(settings);





		FunctionalInput.fromKeyJustPress(Keys.ESCAPE).onTrue(gui::activate);




		/* for more convenience hand position */
		FunctionalInput.fromKeyPress(Keys.Q).onTrue(()->c.zoom+=0.02);
		FunctionalInput.fromKeyPress(Keys.E).onTrue(()->c.zoom-=0.02);
		FunctionalInput.fromKeyPress(Keys.W).onTrue(p::moveUp);
		FunctionalInput.fromKeyPress(Keys.S).onTrue(p::moveDown);
		FunctionalInput.fromKeyPress(Keys.A).onTrue(p::moveLeft);
		FunctionalInput.fromKeyPress(Keys.D).onTrue(p::moveRight);
		FunctionalInput.fromKeyPress(Keys.UP).onTrue(s::moveUp);
		FunctionalInput.fromKeyPress(Keys.DOWN).onTrue(s::moveDown);
		FunctionalInput.fromKeyPress(Keys.LEFT).onTrue(s::moveLeft);
		FunctionalInput.fromKeyPress(Keys.RIGHT).onTrue(s::moveRight);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		c.update();
		b.setProjectionMatrix(c.combined);
		b.begin();
		InputScheduler.getInstance().run();

		world.draw(b, c.position);


		b.end();
		gui.draw();


	}

	@Override
	public void dispose() {
		b.dispose();
	}
}