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
public class Champion {
    private String player_id;
    private String name;
    private String[] items;
    
    public Champion() {}
    
    public Champion(String player_id, String name, String[] items) {
        this.player_id = player_id;
        this.name = name;
        this.items = items;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setItems(String[] items) {
        this.items = items;
    }
    
    public void setPlayerId(String player_id) {
        this.player_id = player_id;
    }
    
    public String getPlayerId() {
        return player_id;
    }
    
    public String[] getItems() {
        return items;
    }
    
    public String getName() {
        return name;
    }
}
