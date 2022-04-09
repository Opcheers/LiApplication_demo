package com.example.liapplication_demo.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.utils.NetImageView;

import java.util.ArrayList;
import java.util.List;

public class ComImageAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> imgURL;

    private List<ImageView> mComImage;

    public ComImageAdapter(List<String> mComImageList, List<View> viewList) {
        this.imgURL = mComImageList;
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = viewList.get(position);
        NetImageView imageView = view.findViewById(R.id.ivNetImg);
        imageView.setImageURL(imgURL.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }
}
