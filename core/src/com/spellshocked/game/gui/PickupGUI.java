package com.spellshocked.game.gui;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.world.obstacle.ObstacleEntity;

public class PickupGUI extends ClickGUI {
    private Item pickup;
    public PickupGUI(PlayerEntity p1, Item p) {
        super(p1);
        pickup = p;
    }

    @Override
    public void draw() {
        changeDisplay();
        Spellshocked.getInstance().world.activeStages.put(((ObstacleEntity<?>) currentTile.obstacle).getGui(), false);
        if(p.hotbar.isFull()) return;
        p.hotbar.add(pickup);
        currentTile.setObstacle(null);
    }
}
