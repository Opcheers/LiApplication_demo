package com.example.liapplication_demo.ui.activity;


import android.widget.Spinner;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;

import butterknife.BindView;

public class FarmlandLeaseActivity extends BaseActivity {

    @BindView(R.id.group)
    public Spinner mGroupSpinner;

    @Override
    protected void initView() {

        //todo
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_farmland_lease;
    }
}
