package lj.lolapi;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lj.database.database;
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
    
    public static void main (String[] args) throws IOException {
        playerInfo players = new playerInfo();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) { 
            prop.load(input);
        } 
        catch (IOException ex) {
        ex.printStackTrace();
        }
        players.getPlayerInfo();
    }
    

}
