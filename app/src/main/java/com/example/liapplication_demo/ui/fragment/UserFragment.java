package com.example.liapplication_demo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.ui.activity.FarmlandMineAcitivty;
import com.example.liapplication_demo.ui.activity.MyOrderActivity;

import butterknife.BindView;

public class UserFragment extends BaseFragment {
    @BindView(R.id.userName)
    public TextView userName;
    @BindView(R.id.userIcon)
    public ImageView userIcon;

    @BindView(R.id.orderNum)
    public TextView userOrderNum;
    @BindView(R.id.landNum)
    public TextView userLandNum;
    @BindView(R.id.integralNum)
    public TextView userIntegerNum;

    @BindView(R.id.orderButton)
    public RelativeLayout orderButton;
    @BindView(R.id.landButton)
    public RelativeLayout landButton;
    @BindView(R.id.integerButton)
    public RelativeLayout integerButton;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View rootView) {
        setUpStates(State.SUCCESS);
    }

    @Override
    protected void initEvent() {

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
            }
        });

        landButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FarmlandMineAcitivty.class));
            }
        });
    }
}
