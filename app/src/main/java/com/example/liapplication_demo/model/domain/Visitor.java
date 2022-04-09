package com.example.liapplication_demo.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Visitor implements Parcelable {
    String name;
    String id;
    String phone;
    int type;

    public Visitor(String name, String id, String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    protected Visitor(Parcel in) {
        name = in.readString();
        id = in.readString();
        phone = in.readString();
    }

    public static final Creator<Visitor> CREATOR = new Creator<Visitor>() {
        @Override
        public Visitor createFromParcel(Parcel in) {
            return new Visitor(in);
        }

        @Override
        public Visitor[] newArray(int size) {
            return new Visitor[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(phone);
    }
}
