package com.pasha.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class Downloader<T> {
    static private final String URLPASHA = "https://survarium.pro/api/v2/players/pasha7308/stats";

    private final Class<T> typeParameterClass;

    public Downloader(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
    public T downloadUrl()
    {
        T dto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134");
            URL url = new URL(URLPASHA);
            dto = mapper.readValue(url, typeParameterClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;

/*
        T dto = null;
        try {
            InputStream is = null;
            HttpURLConnection conn = null;
            int len = 64000;

            try {
                URL url = new URL(URLPASHA);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Cookie", "__cfduid=dd754726187f8f81e4e51e931b0af861c1525149714; _ga=GA1.2.761275528.1525149716; _ym_uid=15251497171016636968; _gid=GA1.2.581617781.1528631410; _ym_isad=1");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134");
                conn.setReadTimeout(10000); // milliseconds
                conn.setConnectTimeout(150060); // milliseconds
                is = conn.getInputStream();
                String strIn = readIt(is, len);
                dto = processIt(strIn);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                if (is != null) {
                    is.close();
                }
            }
        } catch (IOException E) {
            return dto;
        }
        return dto;
        */
    }

    private String readIt(
            InputStream stream,
            int len)
            throws IOException
    {
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private T processIt(
            String strIn)
    {
        T dto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            dto = mapper.readValue(strIn, typeParameterClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

}
