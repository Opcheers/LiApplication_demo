package com.example.liapplication_demo.model.domain;


import java.util.List;

/**
 * 网络数据——土地
 */
public class FarmlandItem {


    private int code;
    private String msg;
    private List<Farmland> data;

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

    public List<Farmland> getData() {
        return data;
    }

    public void setData(List<Farmland> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FarmlandItem{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
