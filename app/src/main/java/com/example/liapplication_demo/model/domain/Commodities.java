package com.example.liapplication_demo.model.domain;

import java.util.List;

public class Commodities {


    /**
     * code : 0
     * msg : 成功
     * data : {"comId":"016d1598-c613-44e1-99bd-37aa11afec46","comName":"薯条","comCategory":"农场商品","comSite":"厂家直发","comPrice":32.5,"comSpec":null,"comStock":10000,"paidNum":0,"isRecommend":1,"comDescription":"2千克/袋,原料优质，自然原味，推荐油炸食用","comEvaluation":null,"comIconList":["https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/commodity/薯条/icon/2022/03/08/8880d004-e9a5-499f-b326-4d64f9c5dacb.png"],"comPreview":"https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/commodity/薯条/preview/2022/03/08/d4b51d5f-c29f-476e-a363-2e5087e3ba1a.png","createTime":"2022-03-08","updateTime":"2022-03-08"}
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


    @Override
    public String toString() {
        return "Commodities{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * comId : 016d1598-c613-44e1-99bd-37aa11afec46
         * comName : 薯条
         * comCategory : 农场商品
         * comSite : 厂家直发
         * comPrice : 32.5
         * comSpec : null
         * comStock : 10000
         * paidNum : 0
         * isRecommend : 1
         * comDescription : 2千克/袋,原料优质，自然原味，推荐油炸食用
         * comEvaluation : null
         * comIconList : ["https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/commodity/薯条/icon/2022/03/08/8880d004-e9a5-499f-b326-4d64f9c5dacb.png"]
         * comPreview : https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/commodity/薯条/preview/2022/03/08/d4b51d5f-c29f-476e-a363-2e5087e3ba1a.png
         * createTime : 2022-03-08
         * updateTime : 2022-03-08
         */

        private String comId;
        private String comName;
        private String comCategory;
        private String comSite;
        private double comPrice;
        private Object comSpec;
        private int comStock;
        private int paidNum;
        private int isRecommend;
        private String comDescription;
        private Object comEvaluation;
        private String comPreview;
        private String createTime;
        private String updateTime;
        private List<String> comIconList;

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

        public String getComCategory() {
            return comCategory;
        }

        public void setComCategory(String comCategory) {
            this.comCategory = comCategory;
        }

        public String getComSite() {
            return comSite;
        }

        public void setComSite(String comSite) {
            this.comSite = comSite;
        }

        public double getComPrice() {
            return comPrice;
        }

        public void setComPrice(double comPrice) {
            this.comPrice = comPrice;
        }

        public Object getComSpec() {
            return comSpec;
        }

        public void setComSpec(Object comSpec) {
            this.comSpec = comSpec;
        }

        public int getComStock() {
            return comStock;
        }

        public void setComStock(int comStock) {
            this.comStock = comStock;
        }

        public int getPaidNum() {
            return paidNum;
        }

        public void setPaidNum(int paidNum) {
            this.paidNum = paidNum;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getComDescription() {
            return comDescription;
        }

        public void setComDescription(String comDescription) {
            this.comDescription = comDescription;
        }

        public Object getComEvaluation() {
            return comEvaluation;
        }

        public void setComEvaluation(Object comEvaluation) {
            this.comEvaluation = comEvaluation;
        }

        public String getComPreview() {
            return comPreview;
        }

        public void setComPreview(String comPreview) {
            this.comPreview = comPreview;
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

        public List<String> getComIconList() {
            return comIconList;
        }

        public void setComIconList(List<String> comIconList) {
            this.comIconList = comIconList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "comId='" + comId + '\'' +
                    ", comName='" + comName + '\'' +
                    ", comCategory='" + comCategory + '\'' +
                    ", comSite='" + comSite + '\'' +
                    ", comPrice=" + comPrice +
                    '}';
        }
    }
}
