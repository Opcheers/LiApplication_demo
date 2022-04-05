package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Visitor;
import com.example.liapplication_demo.utils.Constants;

import butterknife.BindView;

public class ActivityAddVistorActivity extends BaseActivity {

    @BindView(R.id.next_btn)
    public TextView mNextBtn;
    @BindView(R.id.visitor_info_name)
    public EditText mName;
    @BindView(R.id.visitor_info_id)
    public EditText mId;
    @BindView(R.id.visitor_info_phone)
    public EditText mPhone;

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        //返回数据
        onDataBackListener();
    }


    private void onDataBackListener() {

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerVisitorInfo();
            }
        });
    }

    private void handlerVisitorInfo() {
        String visitorName = mName.getText().toString().trim();
        String visitorId = mId.getText().toString().trim();
        String visitorPhone = mPhone.getText().toString().trim();

        /**
         * 检查合法性
         * 1.姓名检查是否为空
         * 2.证件号码只包含数字且18位
         * 3.手机号码只包含数字且11位
         */

        if (TextUtils.isEmpty(visitorName) || TextUtils.isEmpty(visitorId) || TextUtils.isEmpty(visitorPhone)) {
            //身份信息不完整
            Toast.makeText(this, "请完整填写身份信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isDigitsOnly(visitorId) || visitorId.length()!= Constants.ID_SIZE) {
            //证件号码不是纯数字或不是18位
            Toast.makeText(this, "身份证号码错误！请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isDigitsOnly(visitorPhone) || visitorPhone.length()!= Constants.PHONE_SIZE) {
            //手机号码不是纯数字或者不是11位
            Toast.makeText(this, "手机号码填写错误！请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }

        Visitor visitor = new Visitor(visitorName, visitorId, visitorPhone);

        //界面置空
        mName.setText("");
        mId.setText("");
        mPhone.setText("");

        //返还数据给订单页面
        Intent intent = new Intent(ActivityAddVistorActivity.this, ActivityOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("visitor", visitor);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_add_visitor;
    }
}
