package com.example.liapplication_demo.ui.fragment;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.Station;
import com.example.liapplication_demo.presenter.IHomePresenter;
import com.example.liapplication_demo.presenter.impl.HomePresenterImpl;
import com.example.liapplication_demo.ui.adapter.HomePagerAdapter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    @BindView(R.id.home_activity_indicator)
    public TabLayout mTabLayout;

    @BindView(R.id.home_activity_viewpager)
    public ViewPager homePager;

    private IHomePresenter mHomePresenter;
    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        setUpStates(State.SUCCESS);
        mTabLayout.setupWithViewPager(homePager);
        //给viewpager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePager.setAdapter(mHomePagerAdapter);
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
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(Station station) {
        LogUtils.d(this, "onCategoriesLoaded...");
        //加载的数据从这里回来
        if (mHomePagerAdapter!=null) {
            mHomePagerAdapter.setStation(station);
        }


    }


    @Override
    protected void release() {
        //取消回调注册
        if (mHomePresenter != null){
            mHomePresenter.unregisterCallback(this);
        }

    }
}
