package com.example.liapplication_demo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.ui.activity.FarmlandMineAcitivty;
import com.example.liapplication_demo.ui.activity.MainActivity;
import com.example.liapplication_demo.ui.activity.MyOrderActivity;
import com.example.liapplication_demo.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;

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
    @BindView(R.id.logout)
    public Button exitButton;

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

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogout();
            }
        });
    }

    private void handleLogout() {

            LogUtils.d(this, "清空用户信息...");

            //获取保存路径
            File filesDir = this.getActivity().getFilesDir();//  路径/data/user/0/com.example.farm_of_li/files
            LogUtils.d(this, "saveUserInfo: fileDir = "+filesDir.toString());
            File saveFile = new File(filesDir, "userinfo.text");
            try {
                if (!saveFile.exists()){
                    saveFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(saveFile);
                //以特定格式存储:账号：***，密码：***
                fos.write(("").getBytes());
                fos.close();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
