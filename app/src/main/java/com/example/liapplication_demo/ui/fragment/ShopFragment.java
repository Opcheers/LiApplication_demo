package com.example.liapplication_demo.ui.fragment;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.Categories;
import com.example.liapplication_demo.presenter.impl.ShopPresenterImpl;
import com.example.liapplication_demo.ui.adapter.ShopPagerAdapter;
import com.example.liapplication_demo.view.IShopCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;

public class ShopFragment extends BaseFragment implements IShopCallback {

    @BindView(R.id.shop_indicator)
    public TabLayout mTabLayout;
    @BindView(R.id.shop_pager)
    public ViewPager mViewPager;

    private ShopPagerAdapter mShopPagerAdapter;
    private ShopPresenterImpl mShopPresenter;


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View rootView) {

        //TabLayout绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);

        //给ViewPage设置适配器
        mShopPagerAdapter = new ShopPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mShopPagerAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mShopPresenter = new ShopPresenterImpl();
        mShopPresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据
        mShopPresenter.getCommodityCategories();
    }

    @Override
    public void onCommodityCategoriesLoaded(List<Categories> categories) {

        setUpStates(State.SUCCESS);

        if (mShopPagerAdapter != null) {
            mShopPagerAdapter.setCommodityCategories(categories);
        }

    }

    @Override
    public void onNetworkError() {
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

    @Override
    protected void release() {
        //取消回调注册
        if (mShopPresenter != null) {
            mShopPresenter.unregisterCallback(this);
        }
    }
}
