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
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.World;

import java.util.Arrays;

import static com.badlogic.gdx.Input.Keys;

public class Spellshocked extends ApplicationAdapter {
	private World world;
	private SpriteBatch b;
	private OrthographicCamera c;
	private PlayerEntity p;
	@Override
	public void create() {
		world = new World(128, 128);//512, 512);
		b = new SpriteBatch();
		c = new OrthographicCamera(400, 240);
		c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);
		p = new PlayerEntity();
		p.setSize(0.2f, 0.4f);
		p.setPosition(200, 120);
		world.addEntity(p);
		Item i = new Item("./jsons/item.json");
		System.out.print(i.getName());
		String[] iTags = i.getTags();
		for (int j=0; j<iTags.length;j++){
			System.out.println(iTags[j]);
		}

		/* inventory testing
		Inventory inv = new Inventory(5);
		Item test1 = new Item("./jsons/item.json");
		Item test2 = new Item("./jsons/item.json");
		System.out.println();
		System.out.println(inv.size());
		inv.add(test1);
		inv.add(test1);
		System.out.println();
		System.out.println("first: " + inv);
		//inv.add(test2);
		System.out.println();
		System.out.println("second: " + inv);
		//inv.add(4, test2);
		Item test3 = inv.get(0);
		System.out.println();
		System.out.println("third: " + inv);
		System.out.println("test1: " + inv.indexOf(test1));
		System.out.println("test2: " + inv.indexOf(test2));
		System.out.println("last test1: " + inv.lastIndexOf(test1));
		System.out.println("last test2: " + inv.lastIndexOf(test2));

		//inv.set(0, test2);
		inv.set(4, test1);
		//inv.remove(inv.lastIndexOf(test2));
		System.out.println();
		System.out.println(inv);
		System.out.println("test1: " + inv.indexOf(test1));
		System.out.println("test2: " + inv.indexOf(test2));
		System.out.println("last test1: " + inv.lastIndexOf(test1));
		System.out.println("last test2: " + inv.lastIndexOf(test2));

		System.out.println();
		System.out.println("Collection testing");
		System.out.println();
		Inventory inv2 = new Inventory(5);
		Item[] items = new Item[3];
		items[0] = test1;
		items[1] = test1;
		items[2] = test1;
		System.out.println("first: " + inv2);
		inv2.addAll(1, Arrays.asList(items));
		inv2.add(test2);
		inv2.add(test2);
		System.out.println("second: " + inv2);
		inv2.retainAll(Arrays.asList(items));
		inv2.addAll(2, Arrays.asList(items));
		System.out.println("third: " + inv2);
		// end inventory testing */

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
//		p.draw(b);

		b.end();
	}

	@Override
	public void dispose() {
		b.dispose();
	}
}