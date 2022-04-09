package com.example.liapplication_demo.model.domain;

import java.util.List;

public class CommodityOrder {


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
        return "CommodityOrder{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        private String orderId;
        private String userId;
        private String userPhone;
        private String comId;
        private String comName;
        private String comSite;
        private String comPreview;
        private double comPrice;
        private int comQuantity;
        private double orderAmount;
        private int payStatus;
        private String recName;
        private String recPhone;
        private String recAddress;
        private String orderDate;
        private String createTime;
        private String updateTime;

        @Override
        public String toString() {
            return "DataBean{" +
                    "comName='" + comName + '\'' +
                    ", comPreview='" + comPreview + '\'' +
                    ", comQuantity=" + comQuantity +
                    ", orderAmount=" + orderAmount +
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

        public String getComId() {
            return comId;
        }

        public void setComId(String comId) {
            this.comId = comId;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getComSite() {
            return comSite;
        }

        public void setComSite(String comSite) {
            this.comSite = comSite;
        }

        public String getComPreview() {
            return comPreview;
        }

        public void setComPreview(String comPreview) {
            this.comPreview = comPreview;
        }

        public double getComPrice() {
            return comPrice;
        }

        public void setComPrice(int comPrice) {
            this.comPrice = comPrice;
        }

        public int getComQuantity() {
            return comQuantity;
        }

        public void setComQuantity(int comQuantity) {
            this.comQuantity = comQuantity;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getRecName() {
            return recName;
        }

        public void setRecName(String recName) {
            this.recName = recName;
        }

        public String getRecPhone() {
            return recPhone;
        }

        public void setRecPhone(String recPhone) {
            this.recPhone = recPhone;
        }

        public String getRecAddress() {
            return recAddress;
        }

        public void setRecAddress(String recAddress) {
            this.recAddress = recAddress;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
