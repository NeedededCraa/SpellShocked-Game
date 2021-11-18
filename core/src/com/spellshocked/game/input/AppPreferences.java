package com.spellshocked.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {
    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_WASD_ENABLED  = "wasd.enabled";
    private static final String PREF_ARROWKEYS_ENABLED = "arrowkeys.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "b2dtut";
    public Preferences prefs;

    public Preferences getPrefs() {
        if (prefs == null) {
            prefs = Gdx.app.getPreferences("myPrefs");
        }
        return prefs;
    }


    public boolean isSoundEffectsEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }
    public boolean isWasdEnabled(){
        return getPrefs().getBoolean(PREF_WASD_ENABLED, true);
    }
    public void setWasdEnabled(boolean wasdEnabled){
        getPrefs().putBoolean(PREF_WASD_ENABLED, wasdEnabled);
        getPrefs().flush();
    }
    public boolean isArrowKeysEnabled(){
        return getPrefs().getBoolean(PREF_ARROWKEYS_ENABLED, true);
    }

    public void setArrowKeysEnabled(boolean arrowKeysEnabled){
        getPrefs().putBoolean(PREF_ARROWKEYS_ENABLED, arrowKeysEnabled);
        getPrefs().flush();
    }


    public boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }

    public float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume);
        getPrefs().flush();
    }

    public float getSoundVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }


    public void setSoundVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOL, volume);
        getPrefs().flush();
    }
}
