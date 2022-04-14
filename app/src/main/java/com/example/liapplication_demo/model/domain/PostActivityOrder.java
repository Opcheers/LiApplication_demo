package com.example.liapplication_demo.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PostActivityOrder implements Parcelable {


    private String actId;
    private List<com.example.liapplication_demo.model.domain.identityInfoVOList> identityInfoVOList;
    private String orderDate;
    private int orderQuantity;
    private String userAddress;
    private String userId;
    private String userPhone;

    @Override
    public String toString() {
        return "PostActivityOrder{" +
                "actId='" + actId + '\'' +
                ", identityInfoVOList=" + identityInfoVOList +
                ", orderDate='" + orderDate + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", userAddress='" + userAddress + '\'' +
                ", userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }

    public PostActivityOrder(String actId, List<com.example.liapplication_demo.model.domain.identityInfoVOList> identityInfoVOList, String orderDate, int orderQuantity, String userAddress, String userId, String userPhone) {
        this.actId = actId;
        this.identityInfoVOList = identityInfoVOList;
        this.orderDate = orderDate;
        this.orderQuantity = orderQuantity;
        this.userAddress = userAddress;
        this.userId = userId;
        this.userPhone = userPhone;
    }

    protected PostActivityOrder(Parcel in) {
        actId = in.readString();
        identityInfoVOList = in.createTypedArrayList(com.example.liapplication_demo.model.domain.identityInfoVOList.CREATOR);
        orderDate = in.readString();
        orderQuantity = in.readInt();
        userAddress = in.readString();
        userId = in.readString();
        userPhone = in.readString();
    }

    public static final Creator<PostActivityOrder> CREATOR = new Creator<PostActivityOrder>() {
        @Override
        public PostActivityOrder createFromParcel(Parcel in) {
            return new PostActivityOrder(in);
        }

        @Override
        public PostActivityOrder[] newArray(int size) {
            return new PostActivityOrder[size];
        }
    };

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public List<com.example.liapplication_demo.model.domain.identityInfoVOList> getIdentityInfoVOList() {
        return identityInfoVOList;
    }

    public void setIdentityInfoVOList(List<com.example.liapplication_demo.model.domain.identityInfoVOList> identityInfoVOList) {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actId);
        dest.writeTypedList(identityInfoVOList);
        dest.writeString(orderDate);
        dest.writeInt(orderQuantity);
        dest.writeString(userAddress);
        dest.writeString(userId);
        dest.writeString(userPhone);
    }
}
