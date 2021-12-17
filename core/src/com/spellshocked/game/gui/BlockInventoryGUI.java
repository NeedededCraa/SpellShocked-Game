package com.spellshocked.game.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;

import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.inventory.Inventory;
import com.spellshocked.game.world.obstacle.ObstacleContainer;

public class BlockInventoryGUI extends ClickGUI{

//    private Item currentItem;
    private Item test1;
    protected Inventory inv;
    protected InventoryGUI gui;
    protected float x;
    protected float y;

    public BlockInventoryGUI(PlayerEntity p1) {
        super(p1);
        test1 = new Item("./json/Inventory/Item/Weapon/bucket.json");
        gui = p.getHotbar().getInvGUI();
    }

    public BlockInventoryGUI setInventory(Inventory i){
        inv = i;
        for (int j = 0; j < inv.size(); j++) {
            if (Math.random() > 0.9){
                inv.set(j, test1);
                return this;
            }
        }
        return this;
    }

    @Override
    public void draw() {
        if (gui.getBlockGUI() != this) {
            gui.setBlockGUI(this);
        }
        b = Spellshocked.getInstance().world.spriteBatch;
        OrthographicCamera cam = Spellshocked.getInstance().world.getOrthographicCamera();
//        b.setProjectionMatrix(g.world.getC().combined);
//        Vector3 actualMouse = g.world.getMouse();
        x = cam.position.x-80;
        y = cam.position.y-cam.zoom*200;
        b.begin();
        inv.draw(b, x, y);
//        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
//            int i = getFromSlot(actualMouse, (int) x, (int) y);
//            clickInventory(i, inv);
//            if (currentItem != null) {
//                int j = gui.getFromSlot(actualMouse, (int) gui.getX(), (int) gui.getY());
//                gui.clickInventory(j, p.getHotbar());
//            }
//        }
//        if (currentItem != null) {
//            b.draw(currentItem, (int) actualMouse.x, (int) actualMouse.y, 24, 24);
//        }
        if (currentTile != null && Math.abs(p.getTile().xValue-currentTile.xValue) > 5 || Math.abs(p.getTile().yValue-currentTile.yValue) > 5) {
            Spellshocked.getInstance().world.activeStages.put(((ObstacleContainer) currentTile.obstacle).getGui(), false);
            changeDisplay();
            gui.setBlockGUI(null);
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

//    public int getFromSlot(Vector3 mouse, int camX, int camY) {
//        for (int i = 0; i < inv.size(); i++) {
//            int intX = (int) mouse.x;
//            int intY = (int) mouse.y-2;
//            if ((intX >= camX + (i * 31) && intX <= camX + ((i+1) * 31)) && (intY >= camY && intY <= camY + 28)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public void clickInventory(int index, Inventory inventory) {
//        if (currentItem != null && index != -1 && inventory.get(index) == null) {
//            inventory.add(index, currentItem);
//            currentItem = null;
//            System.out.println("test1");
//        }
//        else if (currentItem == null && index != -1 && inventory.get(index) != null) {
//            currentItem = inventory.remove(index);
//        }
//    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Inventory getInv() {
        return inv;
    }
}