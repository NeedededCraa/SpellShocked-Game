package com.spellshocked.game.util;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraHelper {
    static final float ZOOM_MIN = 0.5f;
    static final float ZOOM_MAX = 2f;
    static final float ZOOM_SPEED = 0.02f;
    public static void zoomIn(OrthographicCamera cam){
        if (cam.zoom > ZOOM_MIN) cam.zoom-=ZOOM_SPEED;
    }
    public static void zoomOut(OrthographicCamera cam){
        if (cam.zoom < ZOOM_MAX) cam.zoom+=ZOOM_SPEED;
    }
}
