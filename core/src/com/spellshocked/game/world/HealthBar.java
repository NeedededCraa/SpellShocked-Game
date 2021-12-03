package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HealthBar extends ProgressBar {
    Skin skin = new Skin();
    Pixmap pixmap = new Pixmap(10,10, Pixmap.Format.RGBA8888);
    TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("healthBar.png"))));
    ProgressBarStyle barStyle;
            //new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);


    public HealthBar(int min, int max, float stepSize, boolean vertical, ProgressBarStyle progressBarStyle){
        super(min, max,stepSize, vertical, progressBarStyle );
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        barStyle = progressBarStyle;
        barStyle.knobBefore = barStyle.knob;

        setPosition(10, 10);
        setSize(290, getPrefHeight());
        setAnimateDuration(2);

    }
}
/*
skin = new Skin();
Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
pixmap.setColor(Color.WHITE);
pixmap.fill();
skin.add("white", new Texture(pixmap));

textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
bar = new ProgressBar(0, 10, 0.5f, false, barStyle);
bar.setPosition(10, 10);
bar.setSize(290, bar.getPrefHeight());
bar.setAnimateDuration(2);
stage.addActor(bar);
 */