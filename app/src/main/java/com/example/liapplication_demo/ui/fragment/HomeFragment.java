package com.example.liapplication_demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.presenter.IHomePresenter;
import com.example.liapplication_demo.presenter.impl.HomePresenterImpl;
import com.example.liapplication_demo.ui.activity.ActivityDetailActivity;
import com.example.liapplication_demo.ui.activity.FarmlandActivity;
import com.example.liapplication_demo.ui.adapter.HomeLooperPagerAdapter;
import com.example.liapplication_demo.view.IHomeCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback, ViewPager.OnPageChangeListener, HomeLooperPagerAdapter.OnLooperPagerItemClickListener {


    @BindView(R.id.looper_pager)
    public ViewPager mLoopPager;
    @BindView(R.id.point_container)
    public LinearLayout mPointContainer;//轮播图指示点
    @BindView(R.id.actName)
    public TextView mActName;
    @BindView(R.id.actPrice)
    public TextView mActPrice;
    @BindView(R.id.actSite)
    public TextView mActSite;
    @BindView(R.id.actPreview)
    public ImageView mActPreview;
    @BindView(R.id.home_top_activity)
    public CardView topActivity;
    @BindView(R.id.farmland)
    public LinearLayout mFarmland;

    private IHomePresenter mHomePresenter;
    private HomeLooperPagerAdapter mLooperPagerAdapter;
    private static List<Integer> sPics = new ArrayList<>();

    static {
        sPics.add(R.mipmap.looper_1);
        sPics.add(R.mipmap.looper_2);
    }

    private String mActId;
    private FarmActivities.DataBean mTopActivity;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        setUpStates(State.SUCCESS);

        //设置适配器，给适配器设置数据
        mLooperPagerAdapter = new HomeLooperPagerAdapter();
        mLooperPagerAdapter.setPics(sPics);
        mLoopPager.addOnPageChangeListener(this);
        mLoopPager.setAdapter(mLooperPagerAdapter);

        //根据图片个数，加载指示点
        insertPoint();
        mLoopPager.setCurrentItem(sPics.size()*100, false);


    }

    @Override
    protected void initEvent() {
        //给topActivity cardview设置监听
        onTopActivityListener();

        //给开心农场设置监听
        onFarmlandListener();

        //给轮播图设置监听
        onLooperPagerListener();

    }

    private void onLooperPagerListener() {
        mLooperPagerAdapter.setOnLooperPagerItemClickListener(this);
    }

    private void onFarmlandListener() {
        mFarmland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FarmlandActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onTopActivityListener() {

        topActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("activity", mTopActivity);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPresenter() {
        //创建presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据
        mHomePresenter.getTopActivity();
    }

    private void insertPoint() {
        for (int i = 0; i < sPics.size(); i++) {
            View point = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            point.setBackground(getResources().getDrawable(R.drawable.shape_point_normal));
            layoutParams.leftMargin = 10;
            point.setLayoutParams(layoutParams);
            mPointContainer.addView(point);
        }
    }

    @Override
    protected void release() {
        //取消回调注册
        if (mHomePresenter != null){
            mHomePresenter.unregisterCallback(this);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    /**
     * 这个方法的调用其实是ViewPager停下以后选中的位置
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        int realPosition = 0;
        if (sPics.size() != 0) 
            realPosition = position % sPics.size();

        setSelectPoint(realPosition);
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < mPointContainer.getChildCount(); i++) {
            View point = mPointContainer.getChildAt(i);
            if (i != realPosition) {
                //未选中是灰色
                point.setBackgroundResource(R.drawable.shape_point_normal);
            }else {
                //选中是白色
                point.setBackgroundResource(R.drawable.shape_point_selected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTopActivityLoaded(List<FarmActivities.DataBean> farmActivities) {
        setUpStates(State.SUCCESS);
        mTopActivity = farmActivities.get(0);
        //加载数据从这里回来,直接设置
        mActId = farmActivities.get(0).getActId();
        mActName.setText(farmActivities.get(0).getActName());
        mActPrice.setText("￥" + farmActivities.get(0).getActPrice() + "元/人");
        mActSite.setText(farmActivities.get(0).getActSite());
        Glide.with(this).load(farmActivities.get(0).getActPreview()).into(mActPreview);
    }


    @Override
    public void onLooperItemClick(Integer loopId) {
        //轮播图被点击了，这里能拿到ID
        switch (loopId){
            case 0:
                //跳转到开心农场
                Intent intent = new Intent(getActivity(), FarmlandActivity.class);
                startActivity(intent);
                break;
            case 1:
                //跳转到字画列表
                Toast.makeText(getContext(), "跳转到字画", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError() {
        setUpStates(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpStates(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpStates(State.EMPTY);
    }
}
