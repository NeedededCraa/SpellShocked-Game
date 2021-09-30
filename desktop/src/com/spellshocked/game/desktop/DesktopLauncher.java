package com.spellshocked.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spellshocked.game.Spellshocked;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Spellshocked";
//		config.useGL20 = true;
		config.height = 1000;
		config.width = 1600;
		new LwjglApplication(new Spellshocked(), config);
	}
}
