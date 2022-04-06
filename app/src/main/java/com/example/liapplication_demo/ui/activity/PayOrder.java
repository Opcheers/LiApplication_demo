package com.example.liapplication_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.RadioButton;


import com.example.liapplication_demo.R;

public class PayOrder extends AppCompatActivity {

     private RadioButton alipayButton;
     private RadioButton wx_payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alipayButton = findViewById(R.id.alipay_button);
        wx_payButton = findViewById(R.id.wx_button);

        alipayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wx_payButton.setChecked(false);
                alipayButton.setChecked(true);
            }
        });

        wx_payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wx_payButton.setChecked(true);
                alipayButton.setChecked(false);
            }
        });
    }
}