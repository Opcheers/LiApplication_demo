package com.example.liapplication_demo.model.domain;

import java.io.Serializable;
import java.util.List;

public class Calligraphy implements Serializable {


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
      return "Calligraphy{" +
              "code=" + code +
              ", msg='" + msg + '\'' +
              ", data=" + data +
              '}';
   }

   public static class DataBean implements Serializable{
      private String calId;
      private String calPreview;
      private List<String> calIcon;
      private String calAuthor;
      private String updateTime;
      private String createTime;

      public String getCalId() {
         return calId;
      }

      public void setCalId(String calId) {
         this.calId = calId;
      }

      public String getCalPreview() {
         return calPreview;
      }

      public void setCalPreview(String calPreview) {
         this.calPreview = calPreview;
      }

      public List<String> getCalIcon() {
         return calIcon;
      }

      public void setCalIcon(List<String> calIcon) {
         this.calIcon = calIcon;
      }

      public String getCalAuthor() {
         return calAuthor;
      }

      public void setCalAuthor(String calAuthor) {
         this.calAuthor = calAuthor;
      }

      public String getUpdateTime() {
         return updateTime;
      }

      public void setUpdateTime(String updateTime) {
         this.updateTime = updateTime;
      }

      public String getCreateTime() {
         return createTime;
      }

      public void setCreateTime(String createTime) {
         this.createTime = createTime;
      }

      @Override
      public String toString() {
         return "DataBean{" +
                 "calId='" + calId + '\'' +
                 ", calAuthor='" + calAuthor + '\'' +
                 '}';
      }
   }
}
