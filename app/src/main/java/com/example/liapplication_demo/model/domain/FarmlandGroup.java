package com.example.liapplication_demo.model.domain;

import java.util.List;

public class FarmlandGroup {


    private int code;
    private String msg;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "FarmlandGroup{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

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

    public static class DataBean {
        private String group;
        private List<FarmlandItem> farmlands;

        public String getGroup() {
            return group;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "group='" + group + '\'' +
                    ", farmlands=" + farmlands +
                    '}';
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public List<FarmlandItem> getFarmlands() {
            return farmlands;
        }

        public void setFarmlands(List<FarmlandItem> farmlands) {
            this.farmlands = farmlands;
        }
    }
}
