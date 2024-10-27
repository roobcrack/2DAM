package com.ruben.WebtherChecker.Utils;

import com.google.gson.Gson;

public class JsonUtils {
    public static <T> T readGeneric(String url, Class<T> object) {
        try {
            return new Gson().fromJson(InternetUtils.readUrl(url), object);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public static <T> String toJson(T object) {
        return new Gson().toJson(object);
    }
}
