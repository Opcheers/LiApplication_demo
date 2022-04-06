package com.example.liapplication_demo.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.liapplication_demo.model.domain.Categories;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.ui.fragment.ShopPagerFragment;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.view.ICategoryPagerCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class ShopPagerAdapter extends FragmentPagerAdapter implements ICategoryPagerCallback {

    private List<Categories> mCategoryList = new ArrayList<>();

    public ShopPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryList.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        LogUtils.d(this, "getItem --> "+position);
        Categories category = mCategoryList.get(position);
        ShopPagerFragment shopPagerFragment = ShopPagerFragment.newInstance(category);
        return shopPagerFragment;
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }


    public void setCommodityCategories(List<Categories> categories) {
        LogUtils.d(this, "setCommodityCategories...");
        mCategoryList.clear();
        mCategoryList.addAll(categories);
        notifyDataSetChanged();
    }

    @Override
    public void onContentLoaded(List<Commodities.DataBean> commodities) {
    }

    @Override
    public void onNetworkError(int categoryId) {

    }

    @Override
    public void onLoading(int categoryId) {

    }

    @Override
    public void onEmpty(int categoryId) {

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
}
