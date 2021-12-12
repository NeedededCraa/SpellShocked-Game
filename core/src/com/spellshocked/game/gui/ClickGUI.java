package com.spellshocked.game.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.Tile;
import com.spellshocked.game.world.obstacle.ObstacleEntity;

public class ClickGUI extends Stage {
    protected Spellshocked g;
    protected Batch b;
    protected PlayerEntity p;
    protected boolean display;
    protected Tile currentTile;

    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public static String JSON = "./json/Inventory/Hotbar/Hotbar.json";

    public ClickGUI(Spellshocked g1, PlayerEntity p1){
        g = g1;
        p = p1;
        display = false;
    }

    public boolean wasClicked(Vector3 mouse, Tile tile) {
        int objX = tile.xValue;
        int objY = tile.yValue;
        int mObjX = ((int)mouse.x)/16;
        int mObjY = (((int)mouse.y)/12) - (int)p.getTerrainHeight();
        if (mObjX == objX && mObjY == objY && tile.obstacle instanceof ObstacleEntity<?>) {
            currentTile = tile;
            if (!display) {
                g.world.activeStages.put(((ObstacleEntity<?>) currentTile.obstacle).getGui(), true);
            }
            else {
                g.world.activeStages.put(((ObstacleEntity<?>) currentTile.obstacle).getGui(), false);
            }
            changeDisplay();
            return true;
        }
        return false;
    }


    public void changeDisplay() {
        display = !display;
    }

    public boolean isDisplaying() {
        return display;
    }
}
