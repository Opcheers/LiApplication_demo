package com.example.liapplication_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.model.domain.Calligraphy;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalligraphyAdapter extends RecyclerView.Adapter<CalligraphyAdapter.InnerHolder> {

    List<Calligraphy.DataBean> mData = new ArrayList<>();
    private OnListItemClickListener mItemListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_calligraphy, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Calligraphy.DataBean dataBean = mData.get(position);
        holder.setData(dataBean);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemListener != null) {
                    mItemListener.onItemClickListener(dataBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setData(List<Calligraphy.DataBean> calligraphyList) {
        LogUtils.d(CalligraphyAdapter.this, "setData --> " + calligraphyList.toString());
        mData.clear();
        mData = calligraphyList;
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.calligraphyPreview)
        public ImageView previewIv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Calligraphy.DataBean item) {
            Glide.with(itemView.getContext()).load(item.getCalPreview()).into(previewIv);
        }
    }

    public void setOnListItemClickListener(OnListItemClickListener listener){
        mItemListener = listener;
    }

    public interface OnListItemClickListener{
        void onItemClickListener(Calligraphy.DataBean item);
    }
}
