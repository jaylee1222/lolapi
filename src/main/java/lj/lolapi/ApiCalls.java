package lj.lolapi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.LogManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lj
 */
public class ApiCalls {
    
    private static Logger logger = LoggerFactory.getLogger(ApiCalls.class);
    
    public String getItemNames(int itemid) {
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader("/Users/lj/Documents/GitHub/lolapi/src/main/resources/static/item.json"));

            JSONObject jsonObject = (JSONObject) obj;
            
            JSONObject itemList = (JSONObject) jsonObject.get("data");
            
            String iid = String.valueOf(itemid);
            System.out.println(iid);
            
            JSONObject itemProps = (JSONObject) itemList.get(iid);
            
            return (String) itemProps.get("name");
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public HttpResponse<String> Get5v5s() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(
                    URI.create("https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5"))
                    .GET()
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.2 Safari/605.1.15")
                    .header("Accept-Language", "en-us")
                    .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Origin", "https://developer.riotgames.com")
                    .header("X-Riot-Token", <Riot_Token>)
                    .build();
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
        } catch (IOException | InterruptedException ex) {
            logger.error(null, ex);
            return null;
        }
    }
    
    public HttpResponse<String> getSummonerInfo(String summonerName) {
                try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(
                    URI.create("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + URLEncoder.encode(summonerName, StandardCharsets.UTF_8)))
                    .GET()
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.2 Safari/605.1.15")
                    .header("Accept-Language", "en-us")
                    .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Origin", "https://developer.riotgames.com")
                    .header("X-Riot-Token", <Riot_Token>)
                    .build();
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
        } catch (IOException | InterruptedException ex) {
            logger.error(null, ex);
            return null;
        }
    }
    
    public HttpResponse<String> getMatchIds(String puuid) {
            try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?type=ranked&start=0&count=2"))
                .GET()
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.2 Safari/605.1.15")
                .header("Accept-Language", "en-us")
                .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Origin", "https://developer.riotgames.com")
                .header("X-Riot-Token", <Riot_Token>)
                .build();
        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

                return response;
                } catch (IOException | InterruptedException ex) {
                    logger.error(null, ex);
                    return null;
                  }
    }
    
        public HttpResponse<String> getMatchDetails(String matchId) {
            try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId))
                .GET()
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.2 Safari/605.1.15")
                .header("Accept-Language", "en-us")
                .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Origin", "https://developer.riotgames.com")
                .header("X-Riot-Token", <Riot_Token>)
                .build();
        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

                return response;
                } catch (IOException | InterruptedException ex) {
                    logger.error(null, ex);
                    return null;
                  }
    }
}
