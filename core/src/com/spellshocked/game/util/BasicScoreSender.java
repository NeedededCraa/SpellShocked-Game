package com.spellshocked.game.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BasicScoreSender {
    private String gameID;
    private String player_name;
    private int player_ID;

    public BasicScoreSender(String gameID, String player_name){
        this.gameID = gameID;
        this.player_name = player_name;
    }

    /**
     * https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private void create_player() throws IOException {
        URL url = new URL("https://keepthescore.co/api/"+this.gameID+"/player/");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"player_name\": \""+this.player_name+"\"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        System.out.println("player created");
    }

    private void get_playerId() throws IOException {
        JSONArray ret_player_array;
        JSONObject json = readJsonFromUrl("https://keepthescore.co/api/"+gameID+"/board/");
        ret_player_array = json.getJSONArray("players");
        for (int i = 0; i < ret_player_array.length(); i++){
           if (ret_player_array.getJSONObject(i).getString("player_name").equals(this.player_name)){
                this.player_ID = ret_player_array.getJSONObject(i).getInt("id");
                break;
           }
        }
        System.out.println("playerID of "+this.player_name+" is "+this.player_ID);
    }

    private void send_score(int score) throws IOException {
        URL url = new URL("https://keepthescore.co/api/"+this.gameID+"/score/");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\n  \"player_id\": \""+this.player_ID+"\", \"score\": "+score+"\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        System.out.println("score submitted to the leaderboard");
    }

    public void submit_to_leaderboard(int score){
        try{
            create_player();
            get_playerId();
            send_score(score);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
