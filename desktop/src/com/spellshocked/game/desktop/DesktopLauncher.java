package com.spellshocked.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spellshocked.game.Spellshocked;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Spellshocked";
		config.width = 1920; //width of maximized window
		config.height = 1017; //height of maximized window
		new LwjglApplication(new Spellshocked(), config);
	}
}
