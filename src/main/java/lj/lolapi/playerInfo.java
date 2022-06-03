/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lj.lolapi;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lj.database.database;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lj
 */
public class playerInfo {
    ApiCalls apicalls = new ApiCalls();
    String SOURCE_JSON = "item.json";
    
    public List<Player> getTopPlayers() {
        HttpResponse<String> response = apicalls.Get5v5s();
        JSONObject fives = new JSONObject(response.body());
        List<Player> players = new ArrayList();
        JSONArray fiveslist = fives.getJSONArray("entries");
        for(int i = 0; i < 10; i++) {
            Player player = new Player();
            player.setSummonerId(fiveslist.getJSONObject(i).getString("summonerId"));
            player.setSummonerName(fiveslist.getJSONObject(i).getString("summonerName"));
            players.add(player);
        }
        return players;
    }
    
    public List<Player> getPlayerPuuids(List<Player> players) {
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
    
    public List<Player> getPlayerMatches(List<Player> players) {
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
    
    public void getStuff(List<Player> players) throws IOException {
        String itemName;
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
                        int arrayLength = participants.getJSONObject(k).length();
                        int[] playerItems = new int[6];
                        for(int l = 0; l < 6; l++) {
                            playerItems[l] = participants.getJSONObject(k).getInt("item" + l);
                        }
                        String[] itemNames = new String[6];
                        for (int l = 0; l < playerItems.length; l++) {
                            if (playerItems[l] == 0) {
                                playerItems = ArrayUtils.remove(playerItems, l);
                                continue;
                            }
                            itemName = apicalls.getItemNames(playerItems[l]);

                            itemNames[l] = itemName;
                        }
                        champion.setItems(itemNames);
                        champion.setPlayerId(players.get(i).getSummonerName());
                        champions.add(champion);
                    }    
                }
            }
            players.get(i).setChampions(champions);
        }
        getChampions(players);
    }
    
    public void getChampions(List<Player> players) throws IOException {
        database Database = new database();

        for(int i = 0; i < players.size(); i++) {
            List<Champion> champions = players.get(i).getChampions();
            for (int j = 0; j < champions.size(); j++) {
                String[] itemsArray = champions.get(j).getItems();
                List<String> list = new ArrayList<String>();
                for(String s : champions.get(j).getItems()) {
                    if(s != null && s.length() > 0) {
                       list.add(s);
                    }
                }
                itemsArray = list.toArray(new String[list.size()]);
                champions.get(j).setItems(itemsArray);
            }
            Database.insertChampions(champions);
        }
    }
    
    public void getPlayerInfo() throws IOException {
        List<Player> players = getTopPlayers();
        List<Player> players2 = getPlayerPuuids(players);
        List<Player> players3 = getPlayerMatches(players2);
        getStuff(players3);
    }
}
