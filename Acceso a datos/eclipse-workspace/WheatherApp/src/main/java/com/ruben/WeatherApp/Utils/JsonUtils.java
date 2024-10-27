package com.ruben.WeatherApp.Utils;

import com.google.gson.Gson;

public class JsonUtils {
    public static <T> T readGeneric(String url, Class<T> clase) {
        try {
            return new Gson().fromJson(InternetUtils.readUrl(url), clase);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
