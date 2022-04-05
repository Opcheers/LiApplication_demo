package com.example.liapplication_demo.ui.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.ui.fragment.ActivityFragment;
import com.example.liapplication_demo.ui.fragment.HomeFragment;
import com.example.liapplication_demo.ui.fragment.ShopFragment;
import com.example.liapplication_demo.ui.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.home_navigation_bar)
    public BottomNavigationView mNavigationView;

    private HomeFragment mHomeFragment;
    private ActivityFragment mActivityFragment;
    private ShopFragment mShopFragment;
    private UserFragment mUserFragment;
    private FragmentManager mFragmentManager;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mHomeFragment = new HomeFragment();
        mActivityFragment = new ActivityFragment();
        mShopFragment = new ShopFragment();
        mUserFragment = new UserFragment();

        mFragmentManager = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    @Override
    protected void initEvent() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    //LogUtils.d(this, "切换到首页");
                    switchFragment(mHomeFragment);
                }else if(item.getItemId() == R.id.shop){
                    //LogUtils.d(this, "切换到商城");
                    switchFragment(mShopFragment);
                }else if(item.getItemId() == R.id.activity){
                    //LogUtils.d(this, "切换到活动");
                    switchFragment(mActivityFragment);
                }else if(item.getItemId() == R.id.user_center){
                    //LogUtils.d(this, "切换到用户中心");
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
