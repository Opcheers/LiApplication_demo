package com.example.liapplication_demo.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class HomeLooperPagerAdapter extends PagerAdapter {
    private List<Integer> mPics = null;

    @Override
    public int getCount() {
        if (mPics != null) {
            return mPics.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(mPics.get(position));
        //添加到容器中
        container.addView(imageView);
        return imageView;
    }

    /**
     * 销毁looper
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 判断这个view是不是当前这个view
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setPics(List<Integer> pics) {
        this.mPics = pics;
    }
}
