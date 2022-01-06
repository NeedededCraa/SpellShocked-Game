package com.spellshocked.game.world.obstacle;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.spellshocked.game.gui.ClickGUI;
import com.spellshocked.game.world.obstacle.Obstacle;

public class ObstacleEntity<T extends ClickGUI> extends Obstacle {
    public T stage;
    public ObstacleEntity(String jsonPath, T s) {
        super(jsonPath);
        stage = s;
    }
    public T getGui(){
        return stage;
    }
}
