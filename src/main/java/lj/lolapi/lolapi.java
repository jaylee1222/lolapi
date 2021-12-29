package lj.lolapi;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lj
 */
public class lolapi {
    static final Properties prop = new Properties();
    ApiCalls apicalls = new ApiCalls();
    public static void main (String[] args) {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) { 
            prop.load(input);
        } catch (IOException ex) {
        ex.printStackTrace();
          }
    System.out.println(prop.getProperty("key"));
    }
    
    private List<Player> getTopPlayers() {
        HttpResponse<String> response = apicalls.Get5v5s();
        JSONObject fives = new JSONObject(response.body());
        List<Player> players = new ArrayList();
        JSONArray fiveslist = fives.getJSONArray("entries");
        for(int i = 0; i < 19; i++) {
            Player player = new Player();
            player.setSummonerId(fiveslist.getJSONObject(i).getString("summonerId"));
            player.setSummonerName(fiveslist.getJSONObject(i).getString("summonerName"));
            players.add(player);
        }
        return players;
    }
    
    private List<Player> getPlayerPuuids(List<Player> players) {
        for(int i = 0; i < players.size(); i++) {
            HttpResponse<String> nameResponse = apicalls.getSummonerInfo(players.get(i).getSummonerName());
            JSONObject summonerInfo = new JSONObject(nameResponse.body());
            if(summonerInfo.has("puuid")) {
                players.get(i).setPuuid(summonerInfo.getString("puuid")); 
            }
            else {
                players.remove(i);
            }
        }
        return players;
    }
    
    private List<Player> getPlayerMatches(List<Player> players) {
        for(int i = 0; i < players.size(); i++) {
            HttpResponse<String> matchesResponse = apicalls.getMatchIds(players.get(i).getPuuid());
            JSONArray matchesInfo = new JSONArray(matchesResponse.body());
            List<String> matches = new ArrayList();
            for(int j = 0; j < matchesInfo.length(); j++) {
                matches.add(matchesInfo.getString(j));
            }
            players.get(i).setMatches(matches);
        }
        return players;
    }
    
    private List<Player> getStuff(List<Player> players) {
        for(int i = 0; i < players.size(); i++) {
            List<Champion> champions = new ArrayList();
            for(int j = 0; j < players.get(i).getMatches().size(); j++) {
                HttpResponse<String> matchInfo = apicalls.getMatchDetails(players.get(i).getMatches().get(j));
                JSONObject details = new JSONObject(matchInfo.body());
                JSONObject info = details.getJSONObject("info");
                JSONArray participants = info.getJSONArray("participants");
                for(int k = 0; k < participants.length(); k++) {
                    if(participants.getJSONObject(k).getString("summonerName").equals(players.get(i).getSummonerName())) {
                        Champion champion = new Champion();
                        champion.setName(participants.getJSONObject(k).getString("championName"));
                        List<Integer> playerItems = new ArrayList();
                        for(int l = 0; l < 6; l++) {
                            playerItems.add(participants.getJSONObject(k).getInt("item" + l));
                        }
                        champion.setItems(playerItems);
                        champions.add(champion);
                    }
                }
            }
            players.get(i).setChampions(champions);
        }
        return players;
    }
}
