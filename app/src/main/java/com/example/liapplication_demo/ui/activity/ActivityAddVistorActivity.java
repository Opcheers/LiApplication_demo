package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;

import butterknife.BindView;

public class ActivityAddVistorActivity extends BaseActivity {

    @BindView(R.id.next_btn)
    public TextView mNextBtn;

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAddVistorActivity.this, ActivityOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_add_visitor;
    }
}
