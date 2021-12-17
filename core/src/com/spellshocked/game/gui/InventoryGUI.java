package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Hotbar;
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.obstacle.ObstacleContainer;

public class InventoryGUI {
    private Item currentItem;
    protected Inventory inv;
    protected float x;
    protected float y;
    protected BlockInventoryGUI blockGUI;

    public InventoryGUI(Inventory inventory) {
        inv = inventory;
    }

    public void draw(Batch b, float x1, float y1) {
        x = x1;
        y = y1;
        Vector3 actualMouse = Spellshocked.getInstance().world.getMouse();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if(!(inv instanceof Hotbar)) InputScheduler.getInstance().buttonPressedThisLoop.put(Input.Buttons.LEFT, true);
            int i = getFromSlot(actualMouse, (int) x, (int) y, inv);
            if (blockGUI != null && i == -1) {
                i = getFromSlot(actualMouse, (int) blockGUI.getX(), (int) blockGUI.getY(), blockGUI.inv);
                clickInventory(i, blockGUI.getInv());

            }else clickInventory(i, inv);
        }
        if (currentItem != null) {
            b.draw(currentItem, (int) actualMouse.x, (int) actualMouse.y, 24, 24);
        }
    }

    public int getFromSlot(Vector3 mouse, int camX, int camY, Inventory in) {
        for (int i = 0; i < in.size(); i++) {
            int intX = (int) mouse.x;
            int intY = (int) mouse.y-2;
            if ((intX >= camX + (i * 31) && intX <= camX + ((i+1) * 31)) && (intY >= camY && intY <= camY + 28)) {
                return i;
            }
        }
        return -1;
    }

    public void clickInventory(int index, Inventory inventory) {
        if (currentItem != null && index != -1 && inventory.get(index) == null) {
            inventory.add(index, currentItem);
            System.out.println("eeee"+ this);
            currentItem = null;
        }
        else if (currentItem == null && index != -1 && inventory.get(index) != null) {
            System.out.println("ffff"+ this);
            currentItem = inventory.remove(index);
        }
    }

    public void setBlockGUI(BlockInventoryGUI blockGUI1) {
        blockGUI = blockGUI1;
    }

    public BlockInventoryGUI getBlockGUI() {
        return blockGUI;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
