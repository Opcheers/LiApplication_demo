package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class ActivityDetailActivity extends BaseActivity  {


    private FarmActivities.DataBean mfarmActivity;

    @BindView(R.id.actName)
    public TextView actName;
    @BindView(R.id.actPreview)
    public ImageView actPreview;
    @BindView(R.id.actDescription)
    public TextView actDescription;
    @BindView(R.id.actSite)
    public TextView actSite;
    @BindView(R.id.actIcon_container)
    public LinearLayout actIconContainer;
    @BindView(R.id.actPrice)
    public TextView actPrice;
    @BindView(R.id.order_btn)
    public Button mOrderBtn;



    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_detail;
    }

    @Override
    protected void initView() {

        //接收FarmActivities.DataBean
        Intent intent = getIntent();
        mfarmActivity = (FarmActivities.DataBean) intent.getSerializableExtra("topActivity");
        LogUtils.d(this, "mfarmActivity --> " + mfarmActivity.toString());
        //加载数据

        setData();
    }

    private void setData() {
        actName.setText(mfarmActivity.getActName());
        actDescription.setText(mfarmActivity.getActDescription());
        actSite.setText("联系地址："+mfarmActivity.getActSite());
        actPrice.setText("￥"+mfarmActivity.getActPrice());
        Glide.with(this).load(mfarmActivity.getActPreview()).into(actPreview);
        //设置icon
        if (mfarmActivity.getActIcon()!=null) {
            for (int i = 0; i < mfarmActivity.getActIcon().size(); i++) {
                ImageView icon = new ImageView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
                Glide.with(this).load(mfarmActivity.getActIcon().get(i)).into(icon);
                icon.setScaleType(ImageView.ScaleType.FIT_XY);
                icon.setPadding(0, 10, 0, 10);
                icon.setLayoutParams(layoutParams);
                actIconContainer.addView(icon);
            }
        }


    }

    @Override
    protected void initEvent() {
        //监听事件跳转到订单填写页面
        mOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetailActivity.this, ActivityOrderActivity.class);
                intent.putStringArrayListExtra("actDate", (ArrayList<String>) mfarmActivity.getActDate());
                intent.putExtra("actPrice", mfarmActivity.getActPrice());
                startActivity(intent);
            }
        });

    }
}
