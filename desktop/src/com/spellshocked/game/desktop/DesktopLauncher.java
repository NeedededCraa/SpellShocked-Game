package com.spellshocked.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.Spellshocked2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		/**
		 * something weird that must need something running first, otherwise can't read s JSON file at the beginning
		 * if crashed or TextButton not work try to restart the game again
		 * might be disposal issue
		 */
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1020;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		new LwjglApplication(Spellshocked.getInstance(), config);
	}
}
