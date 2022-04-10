package com.example.liapplication_demo.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Calligraphy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;

public class CalligraphyDetailActivity extends BaseActivity {

   @BindView(R.id.calligraphyDetail)
   public ImageView detail;


   private EditText userNameText;
   private EditText userPhoneText;
   private androidx.appcompat.app.AlertDialog.Builder dialogBuilder;

   @Override
   protected void initView() {
      Intent intent = getIntent();
      Calligraphy.DataBean calligraphy = (Calligraphy.DataBean) intent.getSerializableExtra("calligraphy");
      List<String> icon = calligraphy.getCalIcon();

      //加载详情大图
      Glide.with(this).load(icon.get(0)).into(detail);
   }


   private void onIconLoaded(List<String> icon) {

      //拿到Url转成bitmap
      try {
         URL url = new URL(icon.get(0));
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setConnectTimeout(10000);
         connection.setRequestMethod("GET");
         connection.setRequestProperty("Accept-Language" ,"zh-CN, zh; q=0.9");
         connection.setRequestProperty("Accept", "*/*");
         connection.connect();
         int responseCode = connection.getResponseCode();
         if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            runOnUiThread(new Runnable() {
               @Override
               public void run() {
                  detail.setImageBitmap(bitmap);
               }
            });
         }
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   @Override
   protected void initEvent() {

   }

   @Override
   protected int getLayoutResId() {
      return R.layout.activity_calligraphy_detail;
   }

   public void showDialogClick(View view) {
      View informationView = getLayoutInflater().inflate(R.layout.dialog_calligraphy_contact, null);
      dialogBuilder = new AlertDialog.Builder(this);
      dialogBuilder.setView(informationView)
              .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                    userNameText = (EditText) informationView.findViewById(R.id.userName);
                    userPhoneText = (EditText) informationView.findViewById(R.id.userPhone);
                    String userName = userNameText.getText().toString();
                    Log.d("iCUbe", userName);
                    String userPhone = userPhoneText.getText().toString();
                    Log.d("iCUbe", userPhone);
                 }
              })
              .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                 }
              })
              .create()
              .show();

   }
}
