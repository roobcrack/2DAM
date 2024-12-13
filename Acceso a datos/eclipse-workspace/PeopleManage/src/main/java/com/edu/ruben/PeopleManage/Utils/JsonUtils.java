package com.edu.ruben.PeopleManage.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
    public static <T> String toJson(T object) {
        try {
            return new Gson().toJson(object);
        } catch (Exception e) {
            System.out.println("Error al convertir objeto a JSON: " + e.getMessage());
            return "{}";
        }
    }
}
