package com.example.liapplication_demo.data;

/**
 * 存储order信息类
 */
public class CommodityOrderDataBean {
    private String comIcon;
    private String comName;
    private double comPrice;
    private int comQuantity;

    public CommodityOrderDataBean(String comIcon, String comName, double comPrice, int comQuantity) {
        this.comIcon = comIcon;
        this.comName = comName;
        this.comPrice = comPrice;
        this.comQuantity = comQuantity;
    }


    public String getComIcon() {
        return comIcon;
    }

    public String getComPrice() {
        return new Double(comPrice).toString();
    }

    public String getComName() {
        return comName;
    }

    public String getComQuantity() {
        return new Integer(comQuantity).toString();
    }
}
