package com.example.liapplication_demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.Categories;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.presenter.ICategoryPagerPresenter;
import com.example.liapplication_demo.presenter.impl.CategoryPagerPresenterImpl;
import com.example.liapplication_demo.ui.activity.ShopCommodityDetailActivity;
import com.example.liapplication_demo.ui.adapter.ShopPagerContentAdapter;
import com.example.liapplication_demo.utils.Constants;
import com.example.liapplication_demo.view.ICategoryPagerCallback;

import java.util.List;

import butterknife.BindView;

public class ShopPagerFragment extends BaseFragment implements ICategoryPagerCallback, ShopPagerContentAdapter.OnStaggerItemClickListener {

    @BindView(R.id.ShopList)
    public RecyclerView mShopList;

    private ICategoryPagerPresenter mCategoryPagerPresenter;
    private int mMaterialId;
    private ShopPagerContentAdapter mAdapter;

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
        //设置布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mShopList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new ShopPagerContentAdapter();
        mShopList.setAdapter(mAdapter);

    }

    @Override
    protected void initEvent() {
        //给适配器设置监听
        mAdapter.setOnStaggerItemClickListener(this);
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
        mMaterialId = arguments.getInt(Constants.KEY_SHOP_PAGER_MATERIAL_ID);

        //加载数据
        if (mCategoryPagerPresenter != null) {
            mCategoryPagerPresenter.getContentByCategory(title, mMaterialId);
        }
    }

    @Override
    public void onContentLoaded(List<Commodities.DataBean> commodities) {
        // 数据列表加载
        mAdapter.setData(commodities);
        setUpStates(State.SUCCESS);
    }

    /**
     * 网络错误
     */
    @Override
    public void onError() {
        setUpStates(State.ERROR);
    }

    /**
     * 加载界面
     */
    @Override
    public void onLoading() {
        setUpStates(State.LOADING);
    }

    /**
     * 空界面
     */
    @Override
    public void onEmpty() {
        setUpStates(State.EMPTY);
    }

    @Override
    public int getCategoryId() {
        return mMaterialId;
    }


    @Override
    protected void release() {
        if (mCategoryPagerPresenter != null) {
            mCategoryPagerPresenter.unregisterCallback(this);
        }
    }

    @Override
    public void onItemClickListener(Commodities.DataBean item) {
        //点击时跳转到商品详情

        Intent intent = new Intent(getActivity(), ShopCommodityDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("commodity", item);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

