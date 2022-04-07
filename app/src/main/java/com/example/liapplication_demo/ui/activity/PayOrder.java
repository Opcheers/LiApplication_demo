package com.example.liapplication_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;


import com.example.liapplication_demo.R;

public class PayOrder extends AppCompatActivity {

     private RadioButton alipayButton;
     private RadioButton wx_payButton;

     private RelativeLayout alipay;
     private RelativeLayout wx_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_pay);

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
    }
}