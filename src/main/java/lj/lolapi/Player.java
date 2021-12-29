/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lj.lolapi;

import java.util.List;

/**
 *
 * @author lj
 */
public class Player {
    private String summonerName;
    private String summonerId;
    private String puuid;
    private List<String> matches;
    private List<Champion> champions;
    
    public Player() {}
    
    public Player(String summonerName, String summonerId, String puuid, List<String> matches, List<Champion> champions) {
        this.summonerName = summonerName;
        this.summonerId = summonerId;
        this.puuid = puuid;
        this.matches = matches;
        this.champions = champions;
    }
    
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
    
    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
    
    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }
    
    public void setMatches(List<String> matches) {
        this.matches = matches;
    }
    
    public void setChampions(List<Champion> champions) {
        this.champions = champions;
    }
    
    public List<Champion> getChampions() {
        return champions;
    }
    
    public List<String> getMatches() {
        return matches;
    }
    
    public String getPuuid() {
        return puuid;
    }
    
    public String getSummonerName() {
        return summonerName;
    }
    
    public String getSummonerId() {
        return summonerId;
    }
}
