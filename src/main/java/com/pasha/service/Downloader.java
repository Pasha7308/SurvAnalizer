package com.pasha.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.pasha.entity.Player;

import java.net.URL;

public class Downloader<T> {
    // https://survarium.pro/api/v2/players/pasha7308/stats?lang=english&limit=15&skip=0
    static private final String URL = "https://survarium.pro/api/v2/players/%s/stats?lang=english&limit=%d&skip=%d";
    static private final String URLPASHA = "https://survarium.pro/api/v2/players/pasha7308/stats";
    static private final String URLALKARIZ = "https://survarium.pro/api/v2/players/Alkariz/stats";
    static private final String AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134";

    private final Class<T> typeParameterClass;

    public Downloader(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
    public T downloadUrl(Player player, int limit, int skip) {
        T dto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            System.setProperty("http.agent", AGENT);
            String urlString = String.format(URL,
                    player == Player.Pasha ? "pasha7308" : "Alkariz",
                    limit,
                    skip);
            URL url = new URL(urlString);
            dto = mapper.readValue(url, typeParameterClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
