package com.example.liapplication_demo.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.liapplication_demo.model.domain.Categories;
import com.example.liapplication_demo.ui.fragment.ShopPagerFragment;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopPagerAdapter extends FragmentPagerAdapter {

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
}
