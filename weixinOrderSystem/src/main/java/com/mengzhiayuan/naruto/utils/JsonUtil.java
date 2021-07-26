package com.mengzhiayuan.naruto.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/24 19:28
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
