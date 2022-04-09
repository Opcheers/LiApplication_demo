package com.example.liapplication_demo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 从网络加载图片的工具类
 */

public class NetImageView extends AppCompatImageView {
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what==1){
                setImageBitmap((Bitmap) message.obj);
            }
            return false;
        }
    });
    public NetImageView(Context context) {
        super(context);
    }

    public NetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageURL(final String path){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(10000);
                    if (connection.getResponseCode()==200){
                        InputStream inputStream=connection.getInputStream();
                        Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                        Message message=new Message();
                        message.obj=bitmap;
                        message.what=1;
                        handler.sendMessage(message);
                    }
                    else {
                        handler.sendEmptyMessage(2);
                    }

                } catch (Exception e) {
                    handler.sendEmptyMessage(3);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
