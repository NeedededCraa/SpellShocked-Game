package com.spellshocked.game.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BasicScoreSender {
    private String gameID;

    public BasicScoreSender(String gameID){
        this.gameID = gameID;
    }

    private void create_player(String player_name) throws IOException {
        URL url = new URL("https://keepthescore.co/api/"+this.gameID+"/player/");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"player_name\": \""+player_name+"\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        System.out.println("score submitted to the leaderboard");
    }

    private int get_playerId(String player_name){

        return 0;
    }

    private void send_score(String player_name){

    }

    public void submit_to_leaderboard(String player_name){
        try{
            create_player(player_name);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
