package com.example.liapplication_demo.model.domain;

import java.util.List;

public class ActivityOrder {

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ActivityOrder{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        private String orderId;
        private String userId;
        private String userPhone;
        private String userAddress;
        private String actId;
        private String actName;
        private int actPrice;
        private String actPreview;
        private int orderQuantity;
        private int orderAmount;
        private String orderDate;
        private int payStatus;
        private int isUsed;
        private Object orderBill;
        private List<com.example.liapplication_demo.model.domain.identityInfoVOList> identityInfoVOList;

        @Override
        public String toString() {
            return "DataBean{" +
                    "actName='" + actName + '\'' +
                    ", actPrice=" + actPrice +
                    ", actPreview='" + actPreview + '\'' +
                    ", orderQuantity=" + orderQuantity +
                    ", orderAmount=" + orderAmount +
                    ", identityInfoVOList=" + identityInfoVOList +
                    '}';
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getActId() {
            return actId;
        }

        public void setActId(String actId) {
            this.actId = actId;
        }

        public String getActName() {
            return actName;
        }

        public void setActName(String actName) {
            this.actName = actName;
        }

        public int getActPrice() {
            return actPrice;
        }

        public void setActPrice(int actPrice) {
            this.actPrice = actPrice;
        }

        public String getActPreview() {
            return actPreview;
        }

        public void setActPreview(String actPreview) {
            this.actPreview = actPreview;
        }

        public int getOrderQuantity() {
            return orderQuantity;
        }

        public void setOrderQuantity(int orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
            this.isUsed = isUsed;
        }

        public Object getOrderBill() {
            return orderBill;
        }

        public void setOrderBill(Object orderBill) {
            this.orderBill = orderBill;
        }

        public List<com.example.liapplication_demo.model.domain.identityInfoVOList> getIdentityInfoVOList() {
            return identityInfoVOList;
        }

        public void setIdentityInfoVOList(List<com.example.liapplication_demo.model.domain.identityInfoVOList> identityInfoVOList) {
            this.identityInfoVOList = identityInfoVOList;
        }

    }
}
