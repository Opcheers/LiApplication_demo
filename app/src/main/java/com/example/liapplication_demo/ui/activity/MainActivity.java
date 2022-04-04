package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends BaseActivity {

    private EditText mAccount;
    private EditText mVerification;
    private Button mGetCode;
    private Button mLogin;

    private static final String TAG = "MainActivity";

    @Override
    protected void initView() {
        mAccount = findViewById(R.id.main_et_telnumber);
        mVerification = findViewById(R.id.main_et_pwdcode);
        mGetCode = findViewById(R.id.main_btn_getcode);
        mLogin = findViewById(R.id.main_btn_login);

    }

    @Override
    protected void initEvent() {
        getCodeListener();
        loginListener();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void getCodeListener() {
        mGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "点击获取验证码 ...");
                handlerGetCode();
            }
        });
    }
    private void loginListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "点击登录 ...");
                handlerLogin();
            }
        });
    }

    //第三步：拿到界面内容
    private void handlerGetCode() {
        //获取验证码按钮-需要获取手机号
        String telText = mAccount.getText().toString().trim();
        if (TextUtils.isEmpty(telText)) {
            Toast.makeText(this, "输入手机号为空，请输入手机号！", Toast.LENGTH_SHORT).show();
            return;
        }

        //获取到手机号，就要创建一个意图对象，然后传给短信验证那边？

    }

    private void handlerLogin() {
        String telText = mAccount.getText().toString().trim();
        String pwdcodeText = mVerification.getText().toString().trim();

        //账号和验证码合法性检查——判空
        if (TextUtils.isEmpty(telText)) {
            Toast.makeText(this, "输入手机号为空，请输入手机号！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwdcodeText)){
            Toast.makeText(this, "输入验证码为空，请输入验证码！", Toast.LENGTH_SHORT).show();
            return;
        }

        //界面置空
        mAccount.setText("");
        mGetCode.setText("");

        //存储手机号和验证码到本机
        saveUserInfo(telText, pwdcodeText);

        //手机号和验证码输入完成，创建一个意图对象，然后传给首页那边
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void saveUserInfo(String telText, String pwdcodeText) {
        Log.d(TAG, "保存用户信息...");

        //获取保存路径
        File filesDir = this.getFilesDir();///data/user/0/com.example.farm_of_li/files
        Log.d(TAG, "saveUserInfo: fileDir = "+filesDir.toString());
        File saveFile = new File(filesDir, "userinfo.text");
        try {
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(saveFile);
            //以特定格式存储:账号：***，密码：***
            fos.write((telText+"***"+pwdcodeText).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
