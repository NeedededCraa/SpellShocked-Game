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
import com.spellshocked.game.item.Item;
import com.spellshocked.game.world.Obstacle;
import com.spellshocked.game.world.World;

import static com.badlogic.gdx.Input.Keys;

public class Spellshocked extends ApplicationAdapter {
	private World world;
	private SpriteBatch b;
	private OrthographicCamera c;
	private PlayerEntity p;
	@Override
	public void create() {
		world = new World(64, 64);//512, 512);
		b = new SpriteBatch();
		c = new OrthographicCamera(400, 240);
		c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);
		p = new PlayerEntity();
		p.followWithCamera(c);
		p.setSize(0.2f, 0.4f);
		p.setPosition(200, 120);
		world.addEntity(p);
		//item testing
		Item i = new Item("./jsons/item.json");
		Obstacle pebble = new Obstacle("./jsons/Obstacle.json");
		System.out.println(pebble.getName());
		System.out.print(i.getName());
		String[] iTags = i.getTags();
		for (int j=0; j<iTags.length;j++){
			System.out.println(iTags[j]);
		}




 		FunctionalInput.fromKeyPress(Keys.A).onTrue(()->c.zoom+=0.02);
		FunctionalInput.fromKeyPress(Keys.Q).onTrue(()->c.zoom-=0.02);
		FunctionalInput.fromKeyPress(Keys.UP).onTrue(p::moveUp);
		FunctionalInput.fromKeyPress(Keys.DOWN).onTrue(p::moveDown);
		FunctionalInput.fromKeyPress(Keys.LEFT).onTrue(p::moveLeft);
		FunctionalInput.fromKeyPress(Keys.RIGHT).onTrue(p::moveRight);

	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		c.update();
		b.setProjectionMatrix(c.combined);
		b.begin();
		InputScheduler.getInstance().run();
		world.draw(b, c.position);
//		p.draw(b);

		b.end();
	}

	@Override
	public void dispose() {
		b.dispose();
	}
}