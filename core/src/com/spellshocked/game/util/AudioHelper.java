package com.spellshocked.game.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.player.Player;
import com.spellshocked.game.world.Tile;
import com.spellshocked.game.world.World;

public class AudioHelper {
    public Sound currentSound;
    public Music currentMusic;

    public World currentWorld;
    public Tile[][] allTile;
    public Tile currentTile;
    public int currentTileX;
    public int currentTileY;

    public Entity currentEntity;
    public Player currentPlayer;

    public AudioHelper(){

    }

    public AudioHelper(World world){
        this.currentWorld = world;
    }

    public AudioHelper(Tile tile){
        this.currentTile = tile;
    }

    public void set_Sound(Sound sound){
        this.currentSound = sound;
    }
    public void set_Music(Music music){
        this.currentMusic = music;
    }
}
