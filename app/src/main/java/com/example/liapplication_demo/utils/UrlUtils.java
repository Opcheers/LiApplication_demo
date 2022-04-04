package com.example.liapplication_demo.utils;

public class UrlUtils {

    public static String createShopPagerUrl(String title){
        return "commodity/findByComCategory/"+title;
    }

    public static String createActivityDetailUrl(String id){
        return "activity/findById/" + id;
    }
}
