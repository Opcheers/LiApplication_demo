package com.example.liapplication_demo.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class identityInfoVOList implements Parcelable {
    String name;
    String identity;
    String phone;
    int type;

    public identityInfoVOList(String name, String id, String phone) {
        this.name = name;
        this.identity = id;
        //this.phone = phone;
        this.type = 0;
    }

    protected identityInfoVOList(Parcel in) {
        name = in.readString();
        identity = in.readString();
        phone = in.readString();
    }

    public static final Creator<identityInfoVOList> CREATOR = new Creator<identityInfoVOList>() {
        @Override
        public identityInfoVOList createFromParcel(Parcel in) {
            return new identityInfoVOList(in);
        }

        @Override
        public identityInfoVOList[] newArray(int size) {
            return new identityInfoVOList[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "identityInfoVOList{" +
                "name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(identity);
        parcel.writeString(phone);
    }
}
