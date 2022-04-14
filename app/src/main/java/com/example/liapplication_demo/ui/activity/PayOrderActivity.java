package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.model.domain.PostActivityOrder;

public class PayOrderActivity extends AppCompatActivity {

     private RadioButton alipayButton;
     private RadioButton wx_payButton;

     private RelativeLayout alipay;
     private RelativeLayout wx_pay;

     private TextView price;
     private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        price = findViewById(R.id.price);
        payBtn = findViewById(R.id.OrderPay);
        alipayButton = findViewById(R.id.alipayButton);
        wx_payButton = findViewById(R.id.wx_button);
        alipay = findViewById(R.id.alipay);
        wx_pay = findViewById(R.id.wx_pay);

        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alipayButton.setChecked(true);
                wx_payButton.setChecked(false);
            }
        });

        wx_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alipayButton.setChecked(false);
                wx_payButton.setChecked(true);
            }
        });

        //接收price数据
        Intent intent = getIntent();
        PostActivityOrder activityOrder = (PostActivityOrder) intent.getParcelableExtra("activityOrder");
        double actPrice = intent.getDoubleExtra("actPrice", Double.parseDouble("0"));
        price.setText(actPrice+"￥");


        initListener();
    }

    private void initListener() {
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //网络访问充值



                Intent intent = new Intent();



                //充值成功
                intent.putExtra("resultContent", "充值成功！");
                setResult(21, intent);//充值成功的resultCode为21
                finish();

                //充值失败
//                intent.putExtra("resultContent", "充值失败！");
//                setResult(22, intent);//充值失败的resultCode为22
//                finish();

            }
        });
    }
}