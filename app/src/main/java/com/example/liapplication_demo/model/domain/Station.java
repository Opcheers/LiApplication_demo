package com.example.liapplication_demo.model.domain;

import java.util.List;

public class Station {


    /**
     * code : 0
     * msg : 成功
     * data : [{"stationId":10,"stationName":"清华大学"},{"stationId":11,"stationName":"学清苑小区"}]
     */

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

    public static class DataBean {
        /**
         * stationId : 10
         * stationName : 清华大学
         */

        private int stationId;
        private String stationName;

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "stationId=" + stationId +
                    ", stationName='" + stationName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Station{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
