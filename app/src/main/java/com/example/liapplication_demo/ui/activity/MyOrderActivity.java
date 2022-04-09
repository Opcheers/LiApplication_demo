package com.example.liapplication_demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.ui.adapter.OrderFragmentAdapter;
import com.example.liapplication_demo.ui.fragment.MyActOrderFragment;
import com.example.liapplication_demo.ui.fragment.MyCommodityOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager2 viewPager2;
    private List<Fragment> fragmentList;

    private TextView comButton;
    private TextView actButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        initPager();

        initEvent();
    }

    private void initEvent() {
        comButton = findViewById(R.id.com);
        actButton = findViewById(R.id.act);

        comButton.setOnClickListener(this);
        actButton.setOnClickListener(this);
    }

    private void initPager() {
        viewPager2 = findViewById(R.id.vp);

        fragmentList = new ArrayList<>();
        fragmentList.add(MyCommodityOrderFragment.newInstance());
        fragmentList.add(MyActOrderFragment.newInstance());

        OrderFragmentAdapter orderFragmentAdapter = new OrderFragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        viewPager2.setAdapter(orderFragmentAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }


        });
    }

    private void changeTab(int position) {
        switch (position) {
            case R.id.com:
                viewPager2.setCurrentItem(0);
            case 0:
                comButton.setTextColor(Color.rgb(255, 169, 64));
                actButton.setTextColor(Color.rgb(0, 0, 0));
                break;
            case R.id.act:
                viewPager2.setCurrentItem(1);
            case 1:
                actButton.setTextColor(Color.rgb(255, 169, 64));
                comButton.setTextColor(Color.rgb(0, 0, 0));
                break;
        }
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}