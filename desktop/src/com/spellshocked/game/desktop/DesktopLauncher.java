package com.spellshocked.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.Spellshocked;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new Spellshocked()); //something weird that must need something running first, otherwise can't read s JSON file at the beginning

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		JsonReader jsonReader = new JsonReader();
		JsonValue contents = jsonReader.parse(Gdx.files.local("spellshocked.setting.json"));
		config.title = contents.getString("title");
		config.width = contents.getInt("window_width");
		config.height = contents.getInt("window_height");
		new LwjglApplication(new Spellshocked(), config);
	}
}
