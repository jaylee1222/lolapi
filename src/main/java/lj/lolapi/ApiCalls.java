package lj.lolapi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lj
 */
public class ApiCalls {
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
                    .header("X-Riot-Token", "<api_key>")
                    .build();
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ApiCalls.class.getName()).log(Level.SEVERE, null, ex);
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
                    .header("X-Riot-Token", "<api_key>")
                    .build();
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ApiCalls.class.getName()).log(Level.SEVERE, null, ex);
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
                .header("X-Riot-Token", "<api_key>")
                .build();
        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

                return response;
                } catch (IOException | InterruptedException ex) {
        Logger.getLogger(ApiCalls.class.getName()).log(Level.SEVERE, null, ex);
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
                .header("X-Riot-Token", "<api_key>")
                .build();
        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

                return response;
                } catch (IOException | InterruptedException ex) {
        Logger.getLogger(ApiCalls.class.getName()).log(Level.SEVERE, null, ex);
        return null;
                 }
    }
}
