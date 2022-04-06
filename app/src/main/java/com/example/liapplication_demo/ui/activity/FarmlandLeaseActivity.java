package com.example.liapplication_demo.ui.activity;


import android.widget.Button;
import android.widget.Spinner;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;

import butterknife.BindView;

public class FarmlandLeaseActivity extends BaseActivity {

    @BindView(R.id.group_id)
    public Spinner mGroupSpinner;
    @BindView(R.id.farmland_lease_next_btn)
    public Button mNext;

    @Override
    protected void initView() {

        initAdapter();
        initPresenter();
    }


    /**
     * 设置适配器
     */
    private void initAdapter() {

    }


    /**
     * 设置presenter
     */
    private void initPresenter() {
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_farmland_lease;
    }
}
