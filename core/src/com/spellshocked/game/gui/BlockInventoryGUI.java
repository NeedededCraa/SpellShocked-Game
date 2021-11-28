package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.gui.GUI;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;

public class BlockInventoryGUI extends GUI {
    private final Spellshocked g;
    private final Inventory inv;
    private final Batch b = super.getBatch();
    private Item currentItem;

    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public static String JSON = "./json/Inventory/Hotbar/Hotbar.json";


    public BlockInventoryGUI(Spellshocked g1) {
        super(SKIN);
        g = g1;
        inv = new Inventory(5, JSON);
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
            }
        }
        if (currentItem != null) {
            b.draw(currentItem, (int) actualMouse.x, (int) actualMouse.y, 24, 24);
        }
        b.end();
    }

    public int getFromSlot(Vector3 mouse, int camX, int camY) {
        for (int i = 0; i < inv.size(); i++) {
            int intX = (int) mouse.x;
            int intY = (int) mouse.y-2;
            System.out.println("mouse: " + intX + " " + intY);
            System.out.println("cam: " + camX + " " + camY);
            if ((intX >= camX + (i * 31) && intX <= camX + ((i+1) * 31)) && (intY >= camY && intY <= camY + 28)) {
                System.out.println("test" + i);
                return i;
            }
        }
        return -1;
    }

    public Inventory getInv() {
        return inv;
    }
}