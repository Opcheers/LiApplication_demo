package com.example.liapplication_demo.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class HomeLooperPagerAdapter extends PagerAdapter {
    private List<Integer> mPics = null;
    private OnLooperPagerItemClickListener mLooperItemListener = null;

    @Override
    public int getCount() {
        if (mPics != null) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position % mPics.size();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(mPics.get(realPosition));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//图片填充
        imageView.setPadding(10, 10, 10, 0);//图片边距
        //添加到容器中
        container.addView(imageView);

        //设置监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLooperItemListener.onLooperItemClick(realPosition);
            }
        });
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

    public void setOnLooperPagerItemClickListener(OnLooperPagerItemClickListener listener){
        this.mLooperItemListener = listener;
    }

    public interface OnLooperPagerItemClickListener{
        void onLooperItemClick(Integer loopId);
    }
}
