package com.example.liapplication_demo.model.domain;

import java.util.List;

public class FarmActivities {


    /**
     * code : 0
     * msg : 成功
     * data : [{"actId":"6d42fb47-478a-4e9d-b674-0db8e4b6beca","actName":"参观开心农场","actStock":10000,"actSite":"北京市房山区城关街道田各庄村东","actPrice":0,"actDescription":"亲身参观开心农场，体验农场美景。不仅可以享受线下签约开心农场的优惠，还可以亲自为自己的农田挑选播种的种子以及农具，体会播种的乐趣。推广期内可来此预约免费的农场参观，李氏农场欢迎您","actIcon":["https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/activity/参观开心农场/icon/2022/03/08/ce207476-2936-4b4c-9d2b-3a1583d73d76.jpeg"],"actPreview":"https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/activity/参观开心农场/preview/2022/03/08/728b04f9-82da-45ad-8472-9270adfa9177.jpeg","actDate":["2022-03-12","2022-03-13","2022-03-19","2022-03-20","2022-03-26","2022-03-27"],"actPriority":0,"onShelf":1}]
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
         * actId : 6d42fb47-478a-4e9d-b674-0db8e4b6beca
         * actName : 参观开心农场
         * actStock : 10000
         * actSite : 北京市房山区城关街道田各庄村东
         * actPrice : 0
         * actDescription : 亲身参观开心农场，体验农场美景。不仅可以享受线下签约开心农场的优惠，还可以亲自为自己的农田挑选播种的种子以及农具，体会播种的乐趣。推广期内可来此预约免费的农场参观，李氏农场欢迎您
         * actIcon : ["https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/activity/参观开心农场/icon/2022/03/08/ce207476-2936-4b4c-9d2b-3a1583d73d76.jpeg"]
         * actPreview : https://farm-1309134744.cos.ap-beijing.myqcloud.com/farm/activity/参观开心农场/preview/2022/03/08/728b04f9-82da-45ad-8472-9270adfa9177.jpeg
         * actDate : ["2022-03-12","2022-03-13","2022-03-19","2022-03-20","2022-03-26","2022-03-27"]
         * actPriority : 0
         * onShelf : 1
         */

        private String actId;
        private String actName;
        private int actStock;
        private String actSite;
        private int actPrice;
        private String actDescription;
        private String actPreview;
        private int actPriority;
        private int onShelf;
        private List<String> actIcon;
        private List<String> actDate;

        public String getActId() {
            return actId;
        }

        public void setActId(String actId) {
            this.actId = actId;
        }

        public String getActName() {
            return actName;
        }

        public void setActName(String actName) {
            this.actName = actName;
        }

        public int getActStock() {
            return actStock;
        }

        public void setActStock(int actStock) {
            this.actStock = actStock;
        }

        public String getActSite() {
            return actSite;
        }

        public void setActSite(String actSite) {
            this.actSite = actSite;
        }

        public int getActPrice() {
            return actPrice;
        }

        public void setActPrice(int actPrice) {
            this.actPrice = actPrice;
        }

        public String getActDescription() {
            return actDescription;
        }

        public void setActDescription(String actDescription) {
            this.actDescription = actDescription;
        }

        public String getActPreview() {
            return actPreview;
        }

        public void setActPreview(String actPreview) {
            this.actPreview = actPreview;
        }

        public int getActPriority() {
            return actPriority;
        }

        public void setActPriority(int actPriority) {
            this.actPriority = actPriority;
        }

        public int getOnShelf() {
            return onShelf;
        }

        public void setOnShelf(int onShelf) {
            this.onShelf = onShelf;
        }

        public List<String> getActIcon() {
            return actIcon;
        }

        public void setActIcon(List<String> actIcon) {
            this.actIcon = actIcon;
        }

        public List<String> getActDate() {
            return actDate;
        }

        public void setActDate(List<String> actDate) {
            this.actDate = actDate;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "actId='" + actId + '\'' +
                    ", actName='" + actName + '\'' +
                    ", actStock=" + actStock +
                    ", actSite='" + actSite + '\'' +
                    ", actPrice=" + actPrice +
                    ", actDescription='" + actDescription + '\'' +
                    ", actPreview='" + actPreview + '\'' +
                    ", actPriority=" + actPriority +
                    ", onShelf=" + onShelf +
                    ", actIcon=" + actIcon +
                    ", actDate=" + actDate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FarmActivities{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
