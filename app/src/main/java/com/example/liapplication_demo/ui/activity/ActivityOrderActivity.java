package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Visitor;
import com.example.liapplication_demo.ui.adapter.ActivityOrderAdapter;
import com.example.liapplication_demo.ui.adapter.ActivityOrderVisitorAdapter;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityOrderActivity extends BaseActivity {

    @BindView(R.id.date_list_view)
    public RecyclerView mDateList;
    @BindView(R.id.add_visitor)
    public ImageButton mAddVisitorBtn;
    @BindView(R.id.visitor_info_list_view)
    public RecyclerView mVisitorList;
    @BindView(R.id.order_pay_btn)
    public Button mPayBtn;
    @BindView(R.id.total_price)
    public TextView mTotalPrice;


    private ArrayList<String> mActDate;
    private double mActPrice;
    private ActivityOrderAdapter mOrderDateAdapter;
    private List<Visitor> mVisitorData = new ArrayList<>();
    private ActivityOrderVisitorAdapter mVisitorAdapter;
    private double mTotalPriceNum;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_order;
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
        mActDate = intent.getStringArrayListExtra("actDate");
        mActPrice = intent.getDoubleExtra("actPrice", -1);

        //加载dateListView
        loadDateListView();
    }


    @Override
    protected void initEvent() {
        //去支付
        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d(this, "pay button click...");
            }

        });

        //添加游客
        mAddVisitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOrderActivity.this, ActivityAddVistorActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //创建适配器,加载数据
        mVisitorAdapter = new ActivityOrderVisitorAdapter(this);
        mVisitorList.setAdapter(mVisitorAdapter);
    }

    public void getPrice() {
        mTotalPriceNum = mActPrice * mVisitorData.size();
        mTotalPrice.setText("总额：￥" + String.format("%.2f", mTotalPriceNum));
    }

    @Override
    public void onResume() {
        super.onResume();
        mTotalPriceNum = mActPrice * mVisitorData.size();
        mTotalPrice.setText("总额：￥" + String.format("%.2f", mTotalPriceNum));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Visitor visitor = data.getParcelableExtra("visitor");
                    Log.d("iCube", visitor.toString());
                    mVisitorData.add(visitor);
                    loadVisitorListView();
                }
                break;
        }
    }

    /**
     * 加载dateListView
     */
    private void loadDateListView() {
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDateList.setLayoutManager(layoutManager);

        //创建日期适配器
        mOrderDateAdapter = new ActivityOrderAdapter();
        mDateList.setAdapter(mOrderDateAdapter);
        mOrderDateAdapter.setData(mActDate, mActPrice);
    }


    /**
     * 添加或删除游客信息时都要重新加载VistorListView
     */
    private void loadVisitorListView() {
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVisitorList.setLayoutManager(layoutManager);

        LogUtils.d(this, "loadVisitorListView visitors --> "+mVisitorData.toString());
        mTotalPriceNum = mActPrice * mVisitorData.size();
        mTotalPrice.setText("总额：￥" + String.format("%.2f", mTotalPriceNum));

        mVisitorAdapter.setData(mVisitorData);
    }


}
