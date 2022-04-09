package com.example.liapplication_demo.data;

public class ActOrderDataBean {
    private String actOrderId;
    private String actName;
    private String actTotalPay;
    private String orderNum;
    private String actImgUrl;


    public ActOrderDataBean(String actOrderId, String actName, String actTotalPay, String orderNum, String actImgUrl) {
        this.actOrderId = actOrderId;
        this.actName = actName;
        this.actTotalPay = actTotalPay;
        this.orderNum = orderNum;
        this.actImgUrl = actImgUrl;
    }


    public String getActOrderId() {
        return actOrderId;
    }

    public String getActName() {
        return actName;
    }

    public String getActTotalPay() {
        return actTotalPay;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public String getActImgUrl() {
        return actImgUrl;
    }
}
