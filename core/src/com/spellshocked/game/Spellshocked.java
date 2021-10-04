package com.spellshocked.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.world.World;

import static com.badlogic.gdx.Input.Keys;

public class Spellshocked extends ApplicationAdapter {
	private World world;
	private SpriteBatch b;
	private OrthographicCamera c;
	private PlayerEntity p;
	@Override
	public void create() {
		world = new World(512, 512);
		b = new SpriteBatch();
		c = new OrthographicCamera(400, 240);
		c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);
		p = new PlayerEntity();
		p.setSize(0.2f, 0.4f);
		p.setPosition(200, 120);
		world.addEntity(p);




 		FunctionalInput.fromKeyPress(Keys.A).onTrue(()->c.zoom+=0.02);
		FunctionalInput.fromKeyPress(Keys.Q).onTrue(()->c.zoom-=0.02);
		FunctionalInput.fromKeyPress(Keys.UP).onTrue(()->c.translate(0, 1, 0)).onTrue(p::moveUp);
		FunctionalInput.fromKeyPress(Keys.DOWN).onTrue(()->c.translate(0, -1, 0)).onTrue(p::moveDown);
		FunctionalInput.fromKeyPress(Keys.LEFT).onTrue(()->c.translate(-1, 0, 0)).onTrue(p::moveLeft);
		FunctionalInput.fromKeyPress(Keys.RIGHT).onTrue(()->c.translate(1, 0, 0)).onTrue(p::moveRight);

	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		c.update();
		b.setProjectionMatrix(c.combined);
		b.begin();
		InputScheduler.getInstance().run();
		world.draw(b, c.position);
		//p.draw(b);

		b.end();
	}

	@Override
	public void dispose() {
		b.dispose();
	}
}