package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.PostResult;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.utils.UrlUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_et_telnumber)
    public EditText mAccountEt;
    @BindView(R.id.main_et_pwdcode)
    public EditText mVerificationEt;
    @BindView(R.id.main_btn_getcode)
    public Button mGetCodeBtn;
    @BindView(R.id.main_btn_login)
    public Button mLoginBtn;
    private String mTelText;
    private String mPwdcodeText;


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
        mGetCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d(this, "点击获取验证码...");
                handlerGetCode();
            }
        });
    }
    private void loginListener() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
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
        mTelText = mAccountEt.getText().toString().trim();
        if (TextUtils.isEmpty(mTelText)) {
            Toast.makeText(this, "输入手机号为空，请输入手机号！", Toast.LENGTH_SHORT).show();

            return;
        }
        if (mTelText.length()!=11 || !TextUtils.isDigitsOnly(mTelText)) {
            Toast.makeText(this, "请正确填写手机号码！", Toast.LENGTH_SHORT).show();
            mAccountEt.setText("");
            return;
        }

        //获取到手机号，就要创建一个意图对象，然后传给短信验证那边
        getVerifyCodeSms();

    }

    /**
     * 获取手机验证码
     */
    private void getVerifyCodeSms() {
        Api api = RetrofitManager.getInstance().getRetrofit().create(Api.class);
        String url = UrlUtils.createGetVerifyCodeUrl(mTelText);
        LogUtils.d(this, "url --> " + url);
        Call<PostResult> task = api.sendVerifyCodeSms(url);
        task.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //POST成功
                    LogUtils.d(this, "获取验证码..." + response.body().getData());
                }else{
                    //POST失败
                    LogUtils.d(this, "获取验证码失败:" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                LogUtils.d(this, "获取验证码失败："+t);

            }
        });
    }

    private void handlerLogin() {
        mTelText = mAccountEt.getText().toString().trim();
        mPwdcodeText = mVerificationEt.getText().toString().trim();

        //账号和验证码合法性检查——判空
        if (TextUtils.isEmpty(mTelText)) {
            Toast.makeText(this, "输入手机号为空，请输入手机号！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mPwdcodeText)){
            Toast.makeText(this, "输入验证码为空，请输入验证码！", Toast.LENGTH_SHORT).show();
            return;
        }

        login();
    }

    private void login() {
        Api api = RetrofitManager.getInstance().getRetrofit().create(Api.class);
        String url = UrlUtils.createLoginUrl(mTelText, mPwdcodeText);
        Call<PostResult> task = api.login(url);
        task.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //POST成功

                    //存储手机号和验证码到本机
                    saveUserInfo(mTelText, mPwdcodeText);

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("userId", mTelText);
                    startActivity(intent);
                }else{
                    //POST失败
                    LogUtils.d(this, "获取验证码失败");
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                LogUtils.d(this, "获取验证码失败："+t);

            }
        });

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
            //如果账号密码为空，就重新填写
            if (info == null || info.length() == 0) {
                //什么都不做
            }else {
                //保存格式:账号：***，密码：***
                String[] splits = info.split("\\*\\*\\*");
                String account = splits[0];
                String pwd = splits[1];
                //回显数据
                mAccountEt.setText(account);
                mVerificationEt.setText(pwd);

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("userId", mTelText);
                startActivity(intent);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
