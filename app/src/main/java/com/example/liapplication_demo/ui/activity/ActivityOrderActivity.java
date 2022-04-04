package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.ui.adapter.ActivityOrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class ActivityOrderActivity extends BaseActivity {

    @BindView(R.id.date_list_view)
    public RecyclerView mDateList;
    @BindView(R.id.add_visitor)
    public Button mAddVistor;

    private ArrayList<String> mActDate;
    private int mActPrice;
    private ActivityOrderAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_order;
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
        mActDate = intent.getStringArrayListExtra("actDate");
        mActPrice = intent.getIntExtra("actPrice", -1);

        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDateList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new ActivityOrderAdapter();
        mDateList.setAdapter(mAdapter);

        mAdapter.setData(mActDate, mActPrice);

    }

    @Override
    protected void initEvent() {
        mAddVistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOrderActivity.this, ActivityAddVistorActivity.class);
                startActivity(intent);
            }
        });
    }

}
