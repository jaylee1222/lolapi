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
    private String name;
    private List<Integer> items;
    
    public Champion() {}
    
    public Champion(String name, List<Integer> items) {
        this.name = name;
        this.items = items;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setItems(List<Integer> items) {
        this.items = items;
    }
    
    public List<Integer> getItems() {
        return items;
    }
    
    public String getName() {
        return this.name;
    }
}
