package com.example.liapplication_demo.model.domain;


import java.io.Serializable;

/**
 * 土地
 */
public class Farmland implements Serializable {


    private String fmId;
    private String fmGroup;
    private String fmItem;//作物
    private String fmPreview;//预览图
    private Object fmTemp;//气温
    private String fmSun;//光照
    private String fmMuck;//废料
    private String fmWater;//水分
    private int isLeased;//是否被租赁
    private double fmPrice;//价格
    private int leaseMode;//租赁模式

    public String getFmId() {
        return fmId;
    }

    public void setFmId(String fmId) {
        this.fmId = fmId;
    }

    public String getFmGroup() {
        return fmGroup;
    }

    public void setFmGroup(String fmGroup) {
        this.fmGroup = fmGroup;
    }

    public String getFmItem() {
        return fmItem;
    }

    public void setFmItem(String fmItem) {
        this.fmItem = fmItem;
    }

    public String getFmPreview() {
        return fmPreview;
    }

    public void setFmPreview(String fmPreview) {
        this.fmPreview = fmPreview;
    }

    public Object getFmTemp() {
        return fmTemp;
    }

    public void setFmTemp(Object fmTemp) {
        this.fmTemp = fmTemp;
    }

    public String getFmSun() {
        return fmSun;
    }

    public void setFmSun(String fmSun) {
        this.fmSun = fmSun;
    }

    public String getFmMuck() {
        return fmMuck;
    }

    public void setFmMuck(String fmMuck) {
        this.fmMuck = fmMuck;
    }

    public String getFmWater() {
        return fmWater;
    }

    public void setFmWater(String fmWater) {
        this.fmWater = fmWater;
    }

    public int getIsLeased() {
        return isLeased;
    }

    public void setIsLeased(int isLeased) {
        this.isLeased = isLeased;
    }

    public double getFmPrice() {
        return fmPrice;
    }

    public void setFmPrice(double fmPrice) {
        this.fmPrice = fmPrice;
    }

    public int getLeaseMode() {
        return leaseMode;
    }

    public void setLeaseMode(int leaseMode) {
        this.leaseMode = leaseMode;
    }

    @Override
    public String toString() {
        return "Farmland{" +
                "fmId='" + fmId + '\'' +
                ", fmGroup='" + fmGroup + '\'' +
                ", fmPrice=" + fmPrice +
                '}';
    }
}
