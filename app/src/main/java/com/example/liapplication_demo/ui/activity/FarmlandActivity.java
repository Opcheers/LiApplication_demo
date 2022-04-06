package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;

import butterknife.BindView;

public class FarmlandActivity extends BaseActivity {
    @BindView(R.id.lease_btn)
    public Button mLeaseBtn;
    @BindView(R.id.my_farmland_btn)
    public Button mMyFarmlandBtn;

    @Override
    protected void initView() {
        //监听按钮
        mLeaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmlandActivity.this, FarmlandLeaseActivity.class);
                startActivity(intent);
            }
        });

        mMyFarmlandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmlandActivity.this, FarmlandMineAcitivty.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_farmland;
    }
}
