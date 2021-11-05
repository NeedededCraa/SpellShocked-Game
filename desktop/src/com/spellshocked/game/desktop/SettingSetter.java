package com.spellshocked.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.spellshocked.game.Spellshocked;

public class SettingSetter {
    public static void main(String[] args) {
        new LwjglApplication(new Spellshocked());
        System.out.println("Preference start");
		Preferences prefs = Gdx.app.getPreferences("SpellshockedPreferences");
//		prefs.putString("name", "hello");
        prefs.putString("title", "Spellshocked");
        prefs.putInteger("window_width", 1920);
        prefs.putInteger("window_height", 1017);
		prefs.flush(); // save preference
        System.out.println(prefs.getString("title"));
		System.out.println(prefs.getInteger("window_width"));
        System.out.println(prefs.getInteger("window_height"));
		System.out.println("Preference end");
        Gdx.app.exit();
    }
}
