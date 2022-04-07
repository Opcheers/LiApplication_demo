package com.example.liapplication_demo.model.domain;

import java.util.List;

public class ActivityOrder {

    private String actId;//活动id
    private List<Visitor> identityInfoVOList;//游客列表
    private String orderDate;//预定日期
    private int orderQuantity;//订单数量——游客人数
    private String userAddress;//用户地址
    private String userId;//用户ID
    private String userPhone;//用户联系方式
    /*
        private String actName;
        private String actPreview;
        private double actPrice;
        private int isUsed;
        private int orderAmount;
        private String orderBill;
        private int payStatus;
    */


    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public List<Visitor> getIdentityInfoVOList() {
        return identityInfoVOList;
    }

    public void setIdentityInfoVOList(List<Visitor> identityInfoVOList) {
        this.identityInfoVOList = identityInfoVOList;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "ActivityOrder{" +
                "actId='" + actId + '\'' +
                ", identityInfoVOList=" + identityInfoVOList +
                ", orderDate='" + orderDate + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", userAddress='" + userAddress + '\'' +
                ", userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
