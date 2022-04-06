package com.example.liapplication_demo.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.Categories;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.presenter.ICategoryPagerPresenter;
import com.example.liapplication_demo.presenter.impl.CategoryPagerPresenterImpl;
import com.example.liapplication_demo.utils.Constants;
import com.example.liapplication_demo.view.ICategoryPagerCallback;

import java.util.List;

public class ShopPagerFragment extends BaseFragment implements ICategoryPagerCallback {

    private ICategoryPagerPresenter mCategoryPagerPresenter;

    public static ShopPagerFragment newInstance(Categories categories){
        ShopPagerFragment shopPagerFragment = new ShopPagerFragment();
        Bundle bundle = new Bundle();

        bundle.putInt(Constants.KEY_SHOP_PAGER_MATERIAL_ID, categories.getId());
        bundle.putString(Constants.KEY_SHOP_PAGER_TITLE, categories.getTitle());
        shopPagerFragment.setArguments(bundle);

        return shopPagerFragment;
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_shop_pager;
    }

    @Override
    protected void initView(View rootView) {
        setUpStates(State.SUCCESS);

    }

    @Override
    protected void initPresenter() {
        mCategoryPagerPresenter = CategoryPagerPresenterImpl.getInstance();
        mCategoryPagerPresenter.registerCallback(this);

    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constants.KEY_SHOP_PAGER_TITLE);
        int materialId = arguments.getInt(Constants.KEY_SHOP_PAGER_MATERIAL_ID);

        // LogUtils.d(this, " title --> " + title);
        // LogUtils.d(this, " materialId --> " + materialId);

        //加载数据
        if (mCategoryPagerPresenter != null) {
            mCategoryPagerPresenter.getContentByCategory(title, materialId);
        }
    }

    @Override
    public void onContentLoaded(List<Commodities.DataBean> commodities) {
        // 数据列表加载
        // TODO：
        //数据列表加载
    }

    @Override
    public void onNetworkError(int categoryId) {
        setUpStates(State.ERROR);
    }

    @Override
    public void onLoading(int categoryId) {
        setUpStates(State.LOADING);
    }

    @Override
    public void onEmpty(int categoryId) {
        setUpStates(State.EMPTY);
    }

    @Override
    public void onLoadMoreError(int categoryId) {

    }

    @Override
    public void onLoadMoreEmpty(int categotyId) {

    }

    @Override
    public void onLoadMoreLoaded(List<Commodities.DataBean> commodities, int categoryId) {

    }

    @Override
    protected void release() {
        if (mCategoryPagerPresenter != null) {
            mCategoryPagerPresenter.unregisterCallback(this);
        }
    }
}

