package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.utils.LogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_et_telnumber)
    public EditText mAccount;
    @BindView(R.id.main_et_pwdcode)
    public EditText mVerification;
    @BindView(R.id.main_btn_getcode)
    public Button mGetCode;
    @BindView(R.id.main_btn_login)
    public Button mLogin;


    @Override
    protected void initView() {

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
                LogUtils.d(this, "点击获取验证码...");
                handlerGetCode();
            }
        });
    }
    private void loginListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d(this, "点击登录...");
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
        if (telText.length()!=11 || !TextUtils.isDigitsOnly(telText)) {
            Toast.makeText(this, "请正确填写手机号码！", Toast.LENGTH_SHORT).show();
            mAccount.setText("");
            return;
        }

        //获取到手机号，就要创建一个意图对象，然后传给短信验证那边
        mVerification.setText("111111");

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
        LogUtils.d(this, "保存用户信息...");

        //获取保存路径
        File filesDir = this.getFilesDir();//  路径/data/user/0/com.example.farm_of_li/files
        LogUtils.d(this, "saveUserInfo: fileDir = "+filesDir.toString());
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

    @Override
    protected void onResume() {
        super.onResume();


        try {
            FileInputStream fileInputStream = this.openFileInput("userinfo.text");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String info = bufferedReader.readLine();
            //保存格式:账号：***，密码：***
            String[] splits = info.split("\\*\\*\\*");
            String account = splits[0];
            String pwd = splits[1];
            //回显数据
            mAccount.setText(account);
            mVerification.setText(pwd);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
