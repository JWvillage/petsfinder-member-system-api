package com.petsfinder.petsfinder.aggregate.helper.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class Util {

    public static JSONObject StringToJSONObject(String str) throws ParseException {

        return (JSONObject) new JSONParser().parse(str);
    }
    
    public static Map<String, String> JSONObectToMap(JSONObject jo, Map<String, String> addData) {
        
        Map<String, String> map = new HashMap<>();
        
        jo.keySet().forEach(key -> {
            String value = jo.get(key).toString();
            map.put(key.toString(), value);
            if (addData != null) {
                addData.keySet().forEach(addKey -> {
                    String addValue = addData.get(addKey);
                    map.put(addKey, addValue);
                });
            }
        });
        
        return map;
    }
    
    public static <T> T MapToEntity(JSONObject jo, Map<String, String> addData, Class<T> tClass) {
        
        Map<String, String> map = JSONObectToMap(jo, addData);
        
        return new ObjectMapper().convertValue(map, tClass);
    }
}
