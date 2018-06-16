package com.pasha.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.pasha.entity.Player;

import java.net.URL;

public class Downloader<T> {
    // https://survarium.pro/api/v2/players/pasha7308/stats?lang=english&limit=15&skip=0
    static private final String URLPASHA = "https://survarium.pro/api/v2/players/pasha7308/stats";
    static private final String URLALKARIZ = "https://survarium.pro/api/v2/players/Alkariz/stats";

    private final Class<T> typeParameterClass;

    public Downloader(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
    public T downloadUrl(Player player) {
        T dto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134");
            URL url = new URL(player == Player.Pasha ? URLPASHA : URLALKARIZ);
            dto = mapper.readValue(url, typeParameterClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
