package com.example.liapplication_demo.utils;

public class UrlUtils {

    public static String createShopPagerUrl(String title) {
        return "commodity/findByComCategory/" + title;
    }

    public static String createFarmlandIdUrl(String id) {
        return "farmland/findByGroup/" + id;
    }

    public static String createActivityOrderUrl(String userId){
        return "actOrder/findByUserId/" + userId;
    }

    public static String createCommodityOrderUrl(String userId){
        return "comOrder/findAllByUserIdAndAndPayStatus/"+userId+"/1";
    }

    public static String createGetVerifyCodeUrl(String userId){
        return "user/sendVerifyCodeSms/"+userId;
    }

    public static String createLoginUrl(String userId, String userCode){
        return "user/login/"+userId+"/"+userId;
    }
}
