package com.example.liapplication_demo.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.ui.fragment.ActivityFragment;
import com.example.liapplication_demo.ui.fragment.HomeFragment;
import com.example.liapplication_demo.ui.fragment.ShopFragment;
import com.example.liapplication_demo.ui.fragment.UserFragment;
import com.example.liapplication_demo.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.home_navigation_bar)
    public BottomNavigationView mNavigationView;

    private HomeFragment mHomeFragment;
    private ActivityFragment mActivityFragment;
    private ShopFragment mShopFragment;
    private UserFragment mUserFragment;
    private FragmentManager mFragmentManager;
    private MagicIndicator mMagicIndicator;
    private ViewPager mContenpager;
    private Unbinder mBind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBind = ButterKnife.bind(this);//绑定
        initFragment();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind!=null) {
            mBind.unbind();
        }
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mActivityFragment = new ActivityFragment();
        mShopFragment = new ShopFragment();
        mUserFragment = new UserFragment();

        mFragmentManager = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Log.d(TAG, "onNavigationItemSelected: title -- > " + item.getTitle() + " -- > " + item.getItemId());
                if(item.getItemId() == R.id.home){
                    LogUtils.d(this, "切换到首页");
                    switchFragment(mHomeFragment);
                }else if(item.getItemId() == R.id.shop){
                    LogUtils.d(this, "切换到商城");
                    switchFragment(mShopFragment);
                }else if(item.getItemId() == R.id.activity){
                    LogUtils.d(this, "切换到活动");
                    switchFragment(mActivityFragment);
                }else if(item.getItemId() == R.id.user_center){
                    LogUtils.d(this, "切换到用户中心");
                    switchFragment(mUserFragment);
                }

                return true;
            }
        });

    }

    private void switchFragment(BaseFragment targetFragment) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();//开启事务
        transaction.replace(R.id.home_page_container, targetFragment);//切换
        transaction.commit();//提交事务
    }


}
