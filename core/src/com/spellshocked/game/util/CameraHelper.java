package com.spellshocked.game.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraHelper {
    static final float ZOOM_MIN = 0.5f;
    static final float ZOOM_MAX = 2f;
    static final float ZOOM_SPEED = 0.02f;

    private OrthographicCamera ortCam;
    private Camera regCam;

    private int currentZoomLevel;
    private int render_distance;

    public CameraHelper(OrthographicCamera ortCam){
        this.ortCam = ortCam;
        currentZoomLevel = 2;
        update_zoom_by_level();
    }
    public CameraHelper(Camera regCam){
        this.regCam = regCam;
        currentZoomLevel = 2;
        update_zoom_by_level();
    }

    public static void zoomInOld(OrthographicCamera cam){
        if (cam.zoom > ZOOM_MIN) cam.zoom-=ZOOM_SPEED;
    }
    public static void zoomOutOld(OrthographicCamera cam){
        if (cam.zoom < ZOOM_MAX) cam.zoom+=ZOOM_SPEED;
    }

    public void zoomIn(){
        zoomLevelDown();
        update_zoom_by_level();
    }
    public void zoomOut(){
        zoomLevelUp();
        update_zoom_by_level();
    }

    private void zoomLevelDown(){
        if (currentZoomLevel > 1 + 1) currentZoomLevel--;
    }
    private void zoomLevelUp(){
        if (currentZoomLevel <= 4) currentZoomLevel++;
    }
    private void update_zoom_by_level(){
        switch (currentZoomLevel){
            case 1:
                ortCam.zoom = 0.5f;
                render_distance = 12;
                break;
            case 2:
                ortCam.zoom = 1f;
                render_distance = 18;
                break;
            case 3:
                ortCam.zoom = 1.5f;
                render_distance = 24;
                break;
            case 4:
                ortCam.zoom = 2f;
                render_distance = 28;
                break;
        }
    }

    public int get_zoom_level(){
        return this.currentZoomLevel;
    }
    public float get_camera_zoom(){
        return this.ortCam.zoom;
    }
    public int get_render_distance(){
        return this.render_distance;
    }
}
