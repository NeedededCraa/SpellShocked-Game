package com.spellshocked.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.spellshocked.game.myPlayer.playerDirection;

// public class Spellshocked implements ApplicationListener {
public class Spellshocked extends ApplicationAdapter {

	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 6, FRAME_ROWS = 5;

	// Objects used
	Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
	Animation<TextureRegion> walkAnimationA; // Must declare frame type (TextureRegion)
	Animation<TextureRegion> walkAnimationB; // Must declare frame type (TextureRegion)
	Texture walkSheet;
	SpriteBatch spriteBatch;
	Texture character_asset_attribute;

	// A variable for tracking elapsed time for the animation
	float stateTime;

	//custom added variables
	private int count = 0;
	final int move_speed = 10;
	int x_coord = 0;
	int y_coord = 0;
	// enum playerDirection {IDLE, LEFT, RIGHT, UP, DOWN}
	// playerDirection currentDirection;

	//custom class variable
	myPlayer player1;
	
	//variable used by methods temporary
	static Texture temp_Texture;

	@Override
	public void create() {
		System.out.println(myPlayer.hello());
		walkAnimationA = initialize_charater_animation("animation_sheetA.png");
		walkAnimationB = initialize_charater_animation("animation_sheetB.png");
		character_asset_attribute = new Texture(Gdx.files.internal("animation_sheetA.png"));

		//other important initialization, do not change
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
		// currentDirection = characterDirection.IDLE;
		player1 = new myPlayer();
        player1.set_XY_limit(0, Gdx.graphics.getWidth() - (character_asset_attribute.getHeight() / FRAME_ROWS), 0, Gdx.graphics.getHeight() - (character_asset_attribute.getWidth() / FRAME_COLS));
	}

	@Override
	public void render() {
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			player1.set_direction_left();
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.D)){
			player1.set_direction_right();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			player1.set_direction_up();
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S)){
			player1.set_direction_down();
		}

		ScreenUtils.clear(1, 0, 0, 1);
		spriteBatch.begin();
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		// Making background
		BitmapFont font = new BitmapFont();
		font.getData().setScale(4);
		font.draw(spriteBatch, String.valueOf(count), 500, 500);
		count++;

		// // Get current frame of animation for the current stateTime
		TextureRegion currentFrameA = walkAnimationA.getKeyFrame(stateTime, true);
		TextureRegion currentFrameB = walkAnimationB.getKeyFrame(stateTime, true);
		playerDirection tempDirection = player1.get_direction();
		switch (tempDirection) {
			case RIGHT:
				spriteBatch.draw(currentFrameA, player1.get_X(), player1.get_Y()); // Draw current frame at designated coordinate
				break;
			case LEFT:
				spriteBatch.draw(currentFrameB, player1.get_X(), player1.get_Y()); // Draw current frame at designated coordinate
				break;
			case UP:
				spriteBatch.draw(currentFrameA, player1.get_X(), player1.get_Y()); // Draw current frame at designated coordinate
				break;
			case DOWN:
				spriteBatch.draw(currentFrameA, player1.get_X(), player1.get_Y()); // Draw current frame at designated coordinate
				break;
			default:
				spriteBatch.draw(currentFrameA, player1.get_X(), player1.get_Y()); // Draw current frame at designated coordinate
				break;
		}
		spriteBatch.end();
	}

	@Override
	public void dispose() { // SpriteBatches and Textures must always be disposed
		spriteBatch.dispose();
		temp_Texture.dispose();
	}

	public static Animation<TextureRegion> initialize_charater_animation(String spritesheet_path){
		// Load the sprite sheet as a Texture
		temp_Texture = new Texture(Gdx.files.internal(spritesheet_path));
		// Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(temp_Texture,
				temp_Texture.getWidth() / FRAME_COLS,
				temp_Texture.getHeight() / FRAME_ROWS);

		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		return new Animation<TextureRegion>(0.025f, walkFrames);		
	}
}