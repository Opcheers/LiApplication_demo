package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.PostActivityOrder;
import com.example.liapplication_demo.model.domain.User;
import com.example.liapplication_demo.model.domain.Visitor;
import com.example.liapplication_demo.ui.adapter.ActivityOrderAdapter;
import com.example.liapplication_demo.ui.adapter.ActivityOrderVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActivityOrderActivity extends BaseActivity implements ActivityOrderAdapter.OnClickDateListListener {

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
    @BindView(R.id.actOrderSite)
    public EditText mActSiteEt;
    @BindView(R.id.actOrderTel)
    public EditText mActTelEt;

    private ActivityOrderAdapter mOrderDateAdapter;
    private ActivityOrderVisitorAdapter mVisitorAdapter;

    private ArrayList<String> mActDateList;//订单日期
    private double mActPrice;//价格
    private String mActId;
    private List<Visitor> mVisitorData = new ArrayList<>();//游客
    private double mTotalPriceNum;
    private String mActDate = null;//选中的日期


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_order;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mActId = intent.getStringExtra("actId");;
        mActDateList = intent.getStringArrayListExtra("actDate");
        mActPrice = intent.getDoubleExtra("actPrice", -1);

        if (mActDateList == null || mActDateList.size() == 0){
            mActDateList.add("该活动已过期");
        }

        //加载dateListView
        loadDateListView();

        //加载visitorListView
        loadVisitorListView();
    }


    @Override
    protected void initEvent() {
        onPayBtnListener();

        //添加游客
        mAddVisitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOrderActivity.this, ActivityAddVistorActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //给日期适配器设置监听
        mOrderDateAdapter.setOnClickDateListListener(this);
    }

    private void onPayBtnListener() {
        //去支付
        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //日期、乘车地点、联系电话、游客信息校验
                handlerOrderInfo();
            }

        });
    }

    private void handlerOrderInfo() {
        String actSite = mActSiteEt.getText().toString().trim();
        String actPhone = mActTelEt.getText().toString().trim();
        if (TextUtils.isEmpty(mActDate)) {
            Toast.makeText(this, "请选择预定日期", Toast.LENGTH_SHORT).show();
            return ;
        }
        if (TextUtils.isEmpty(actSite)){
            Toast.makeText(this, "请填写乘车地点", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(actPhone)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isDigitsOnly(actPhone) || actPhone.length()!=11){
            Toast.makeText(this, "手机号为11位数字，不能包含其他字符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mVisitorData == null || mVisitorData.size() == 0){
            Toast.makeText(this, "请添加乘客信息", Toast.LENGTH_SHORT).show();
            return;
        }

        PostActivityOrder activityOrder = new PostActivityOrder(mActId, mVisitorData, mActDate, mVisitorData.size(), actSite, User.userId, actPhone);

        Intent intent = new Intent(ActivityOrderActivity.this, PayOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("activityOrder", activityOrder);
        intent.putExtras(bundle);
        intent.putExtra("actPrice", mActPrice);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Visitor visitor = data.getParcelableExtra("visitor");
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
        mOrderDateAdapter.setData(mActDateList, mActPrice);
    }


    /**
     * 加载VistorListView
     */
    private void loadVisitorListView() {
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVisitorList.setLayoutManager(layoutManager);

        mVisitorAdapter = new ActivityOrderVisitorAdapter(this);
        mVisitorList.setAdapter(mVisitorAdapter);
        mVisitorAdapter.setData(mVisitorData);

        setPrice();
    }


    public void setPrice() {
        mTotalPriceNum = mActPrice * mVisitorData.size();
        mTotalPrice.setText("总额：￥" + String.format("%.2f", mTotalPriceNum));
    }


    @Override
    public void OnClickItemListener(String item) {
        /**
         * 监听到点击事件：
         * 1.框框变色
         * 2.获取orderdate
         */
        mActDate = item;
        //LogUtils.d(this, "date item click --> date: " + item);

    }


}
