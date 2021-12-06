package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;

import com.spellshocked.game.world.Chest;
import com.spellshocked.game.gui.GUI;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.Tile;

public class BlockInventoryGUI extends GUI {
    private Spellshocked g;
    private Inventory inv;
    private Batch b = super.getBatch();
    private Item currentItem;
    private PlayerEntity p;
    private Item test1;
    private boolean display;
    private Tile currentTile;

    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public static String JSON = "./json/Inventory/Hotbar/Hotbar.json";


    public BlockInventoryGUI(Spellshocked g1, PlayerEntity p1) {
        super(SKIN);
        g = g1;
        p = p1;
        inv = new Inventory(5, JSON);
        test1 = new Item("./json/Inventory/Item/Weapon/bucket.json");
        display = false;
        for (int j = 0; j < inv.size(); j++) {
            if (Math.random() > 0.9){
                inv.set(j, test1);
                return;
            }
        }
    }

    @Override
    public void render(float delta) {
        OrthographicCamera cam = g.world.getC();
        g.world.render(delta);
        g.world.getC().update();
        b.setProjectionMatrix(g.world.getC().combined);
        Vector3 actualMouse = g.world.getMouse();
        b.begin();
        inv.draw(b, cam.position.x-80, cam.position.y-cam.zoom*70);
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int cam_x = (int) cam.position.x-80;
            int cam_y = (int) (cam.position.y-cam.zoom*70);
            int i = getFromSlot(actualMouse, cam_x, cam_y);
            if (currentItem != null && i != -1 && inv.get(i) == null) {
                inv.add(i, currentItem);
                currentItem = null;
            }
            else if (currentItem == null && i != -1 && inv.get(i) != null) {
                currentItem = inv.remove(i);
                p.hotbar.add(currentItem);
            }
        }
        if (currentItem != null) {
            b.draw(currentItem, (int) actualMouse.x, (int) actualMouse.y, 24, 24);
        }
        if (currentTile != null && !p.obstacleNear().contains(currentTile)) {
            g.setScreen(g.world);
            changeDisplay();
        }

        // adding and removing items to inv for testing
//        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
//            inv.add(test1);
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
//            inv.remove(test1);
//        }

        b.end();
    }

    public int getFromSlot(Vector3 mouse, int camX, int camY) {
        for (int i = 0; i < inv.size(); i++) {
            int intX = (int) mouse.x;
            int intY = (int) mouse.y-2;
            if ((intX >= camX + (i * 31) && intX <= camX + ((i+1) * 31)) && (intY >= camY && intY <= camY + 28)) {
                return i;
            }
        }
        return -1;
    }

    public boolean wasClicked(Vector3 mouse, Tile tile) {
        int objX = tile.xValue;
        int objY = tile.yValue;
        int mObjX = ((int)mouse.x)/16;
        int mObjY = (((int)mouse.y)/12) - (int)p.getTerrainHeight();
        if (mObjX == objX && mObjY == objY && tile.obstacle instanceof Chest) {
            currentTile = tile;
            if (!display) {
                g.setScreen(((Chest) currentTile.obstacle).getBlockInventoryGUI());
            }
            else {
                g.setScreen(g.world);
            }
            changeDisplay();
            return true;
        }
        return false;
    }

    public void changeDisplay() {
        if (!display) {
            currentTile.obstacle.setTexture("texture2");
            display = true;
        }
        else {
            currentTile.obstacle.setTexture("texture");
            display = false;
        }
    }

    public boolean isDisplay() {
        return display;
    }

    public Inventory getInv() {
        return inv;
    }
}