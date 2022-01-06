package com.spellshocked.game.world.obstacle;

import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.gui.ClickGUI;
import com.spellshocked.game.gui.PickupGUI;
import com.spellshocked.game.item.Item;
import com.spellshocked.game.item.PumpkinItem;

public class Pumpkin extends ObstacleEntity<PickupGUI> {

    public Pumpkin(PlayerEntity p) {
        super("./json/Object/pumpkin.json", new PickupGUI(p, new PumpkinItem()));
    }
}
