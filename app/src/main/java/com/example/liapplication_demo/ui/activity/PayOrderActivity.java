package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        price = findViewById(R.id.price);
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

        Intent intent = getIntent();
        PostActivityOrder activityOrder = (PostActivityOrder) intent.getParcelableExtra("activityOrder");
        double actPrice = intent.getDoubleExtra("actPrice", Double.parseDouble("0"));
        price.setText(actPrice+"￥");
    }
}