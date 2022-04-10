package com.example.liapplication_demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.utils.NetImageView;

import java.util.ArrayList;
import java.util.List;

public class ComImageAdapter extends PagerAdapter {
    private Context context;
    private List<String> ImageList;
    private List<ImageView> iconImageList = new ArrayList<>();

    public ComImageAdapter(List<String> mComImageList, Context context) {
        this.context = context;
        this.ImageList = mComImageList;

        for(String url : mComImageList) {
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(url).into(imageView);
            iconImageList.add(imageView);
        }
    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(iconImageList.get(position));
        return iconImageList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(iconImageList.get(position));
    }
}
