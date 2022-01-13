package com.spellshocked.game.world.obstacle;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.ClickGUI;
import com.spellshocked.game.gui.PickupGUI;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.PumpkinItem;

public class Pumpkin extends ObstacleEntity<PickupGUI> {
    int pumpkinX;
    int pumpkinY;

    public Pumpkin(PlayerEntity p) {
        super("./json/Object/pumpkin.json", new PickupGUI(p, new PumpkinItem()));
    }
    public Pumpkin(PlayerEntity p, int x, int y) {
        super("./json/Object/pumpkin.json", new PickupGUI(p, new PumpkinItem()));
        pumpkinX = x;
        pumpkinY = y;

    }
    public int getPumpkinX(){ return pumpkinX;}
    public int getPumpkinY(){return pumpkinY;}
}
