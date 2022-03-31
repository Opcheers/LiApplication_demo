package com.example.liapplication_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.model.domain.FarmActivities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPageAdapter extends RecyclerView.Adapter<ActivityPageAdapter.InnerHoder> {

    List<FarmActivities.DataBean> mData = new ArrayList<>();

    /**
     * 找到控件，创建条目
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public InnerHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_farm_activity, parent, false);
        return new InnerHoder(itemView);
    }

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull InnerHoder holder, int position) {
        FarmActivities.DataBean dataBean = mData.get(position);
        holder.setData(dataBean);
    }

    /**
     * 返回条目个数
     * @return
     */
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setFarmActivities(List<FarmActivities.DataBean> farmActivities) {
        mData.clear();
        mData.addAll(farmActivities);
        notifyDataSetChanged();
    }


    public class InnerHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.actName)
        public TextView actName;
        @BindView(R.id.actPrice)
        public TextView actPrice;
        @BindView(R.id.actPreview)
        public ImageView actPreview;
        @BindView(R.id.actSite)
        public TextView actSite;

        //找到条目控件
        public InnerHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 设置条目数据
         * @param dataBean
         */
        public void setData(FarmActivities.DataBean dataBean) {
            actName.setText(""+dataBean.getActName());
            actPrice.setText(dataBean.getActPrice()+"元/人");
            actSite.setText(""+dataBean.getActSite());
            Glide.with(itemView.getContext()).load(dataBean.getActPreview()).into(actPreview);
        }
    }
}
