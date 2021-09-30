package com.spellshocked.game;

// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.spellshocked.game.myPlayer;

public class myPlayer_tester {
    public static void main(String[] args) {
        System.out.println(myPlayer.hello());

        myPlayer player1 = new myPlayer();
        player1.set_XY_limit(0, 100, 0, 100);
        player1.move_left();
        player1.move_up();

        System.out.println(player1.get_X() + " " + player1.get_Y());
        player1.set_direction_up();
        System.out.println(player1.get_direction());
        switch (player1.get_direction()) {
			case UP:
                System.out.println("Moving up!");
                break;
            default:
                System.out.println("Nothing here!");
                break;
        }

        System.out.println("-----------");
        System.out.println("Finished");
    }
}
