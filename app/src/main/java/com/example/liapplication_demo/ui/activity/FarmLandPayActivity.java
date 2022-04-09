package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Farmland;
import com.example.liapplication_demo.utils.LogUtils;

import butterknife.BindView;

public class FarmLandPayActivity extends BaseActivity {
    
    @BindView(R.id.name)
    public EditText mNameEt;
    @BindView(R.id.phone)
    public EditText mPhoneEt;
    @BindView(R.id.email)
    public EditText mEmailEt;
    @BindView(R.id.pay_btn)
    public Button mPayBtn;
    @BindView(R.id.price)
    public TextView mPriceTv;
    private String mName;
    private String mPhone;
    private String mEmail;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_farmland_pay;
    }
    
    @Override
    protected void initView() {

        Intent intent = getIntent();
        Farmland farmland = (Farmland) intent.getSerializableExtra("farmland");
        LogUtils.d(this, "farmland --> " + farmland.toString());
        
        mPriceTv.setText("总额：" + farmland.getFmPrice() + "￥");
        
        //去支付设置监听

    }

    @Override
    protected void initEvent() {

        //监听去支付
        onClickPayListener();
    }


    private void onClickPayListener() {




        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mName = mNameEt.getText().toString().trim();
                mPhone = mPhoneEt.getText().toString().trim();
                mEmail = mEmailEt.getText().toString().trim();
                LogUtils.d(this, "farmland order info: name --> " + mName + ", phone --> " + mPhone + ", email --> " + mEmail);

                //判断信息是否填写正确
                if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mPhone) || TextUtils.isEmpty(mEmail)) {

                    Toast.makeText(FarmLandPayActivity.this, "请完整填写信息！", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (!TextUtils.isDigitsOnly(mPhone) || mPhone.length()!=11){
                        Toast.makeText(FarmLandPayActivity.this, "手机号填写错误！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                Intent intent = new Intent(FarmLandPayActivity.this, PayOrderActivity.class);
                startActivity(intent);
            }
        });

        mNameEt.setText("");
        mPhoneEt.setText("");
        mEmailEt.setText("");
    }

}
