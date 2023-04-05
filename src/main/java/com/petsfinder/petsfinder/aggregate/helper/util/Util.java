package com.petsfinder.petsfinder.aggregate.helper.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Util {

    public static JSONObject StringToJSONObject(String str) throws ParseException {

        return (JSONObject) new JSONParser().parse(str);
    }
    
}
