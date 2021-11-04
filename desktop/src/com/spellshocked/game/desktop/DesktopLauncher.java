package com.spellshocked.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.Spellshocked;

import java.util.Objects;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new Spellshocked()); //something weird that must need something running first, otherwise can't read s JSON file at the beginning

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		JsonReader jsonReader = new JsonReader(); // or use libgdx built-in Preference
		JsonValue jsonContent = jsonReader.parse(Gdx.files.local("spellshocked.setting.json")); //or Gdx.files.external(), Gdx.files.internal() is read only
		config.title = jsonContent.getString("title");
		if (Objects.equals(jsonContent.getString("DisplayType"), "window")){ // "==" not working
			config.fullscreen = false;
			config.width = jsonContent.getInt("window_width");
			config.height = jsonContent.getInt("window_height");
			System.out.println("window");
		}
		else if (Objects.equals(jsonContent.getString("DisplayType"), "fullscreen")){ // "==" not working
			System.out.println("Warning: do not fullscreen, 100% not working");
			config.fullscreen = true;
			config.width = 1920;
			config.height = 1080;
			System.out.println("fullscreen");
		}

		new LwjglApplication(new Spellshocked(), config);
	}
}
