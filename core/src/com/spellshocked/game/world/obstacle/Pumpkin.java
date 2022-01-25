package com.spellshocked.game.world.obstacle;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.ClickGUI;
import com.spellshocked.game.gui.PickupGUI;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.PumpkinItem;
import com.spellshocked.game.world.Tile;

public class Pumpkin extends ObstacleEntity<PickupGUI> {
    public Tile tile;

    public Pumpkin(PlayerEntity p) {
        super("./json/Object/pumpkin.json", new PickupGUI(p, new PumpkinItem()));
    }
    public Pumpkin(PlayerEntity p, Tile t) {
        super("./json/Object/pumpkin.json", new PickupGUI(p, new PumpkinItem()));
        tile = t;
    }
    public int getPumpkinX(){ return tile.xValue;}
    public int getPumpkinY(){return tile.yValue;}
}
