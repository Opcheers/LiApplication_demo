package com.example.liapplication_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;


import com.example.liapplication_demo.R;

public class PayOrder extends AppCompatActivity {

     private RadioButton alipayButton;
     private RadioButton wx_payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_pay);

        alipayButton = findViewById(R.id.alipayButton);
        wx_payButton = findViewById(R.id.wx_button);

        alipayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alipayButton.setChecked(true);
                wx_payButton.setChecked(false);
            }
        });

        wx_payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alipayButton.setChecked(false);
                wx_payButton.setChecked(true);
            }
        });
    }
}