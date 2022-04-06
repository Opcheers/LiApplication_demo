package com.example.liapplication_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.liapplication_demo.R;

public class CommodityDetail extends AppCompatActivity {

    private TextView subButton;
    private TextView addButton;
    private TextView comNum;

    private Integer num = new Integer(1);

    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);

        subButton = findViewById(R.id.SubButton);
        addButton = findViewById(R.id.AddButton);
        comNum = findViewById(R.id.CommodityNum);

        payButton = findViewById(R.id.payButton);

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num > 1) {
                    num--;
                    comNum.setText(num.toString());
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                comNum.setText(num.toString());
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommodityDetail.this, PayOrder.class));
            }
        });
    }
}