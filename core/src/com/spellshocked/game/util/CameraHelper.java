package com.spellshocked.game.util;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraHelper {
    public static void zoomIn(OrthographicCamera cam){
        if (cam.zoom > 0.5) cam.zoom-=0.02;
    }
    public static void zoomOut(OrthographicCamera cam){
        if (cam.zoom < 2) cam.zoom+=0.02;
    }
}
