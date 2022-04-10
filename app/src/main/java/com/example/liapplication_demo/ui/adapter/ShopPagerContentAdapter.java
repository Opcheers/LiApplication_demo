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
import com.example.liapplication_demo.model.domain.Commodities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopPagerContentAdapter extends RecyclerView.Adapter<ShopPagerContentAdapter.InnerHolder> {

    List<Commodities.DataBean> mData = new ArrayList<>();
    private OnStaggerItemClickListener mItemClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stagger_shop_commodity, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Commodities.DataBean dataBean = mData.get(position);
        holder.setData(dataBean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClickListener(dataBean);
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

    public void setData(List<Commodities.DataBean> commodities) {
        mData.clear();
        mData.addAll(commodities);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comName)
        public TextView comName;
        @BindView(R.id.comPrice)
        public TextView comPrice;
        @BindView(R.id.paidNum)
        public TextView paidNum;
        @BindView(R.id.comPreview)
        public ImageView comPreview;

        //找到条目控件
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Commodities.DataBean dataBean) {
            comName.setText(dataBean.getComName());
            comPrice.setText("￥"+dataBean.getComPrice());
            paidNum.setText(dataBean.getPaidNum()+"人付款");
            Glide.with(itemView.getContext()).load(dataBean.getComPreview()).into(comPreview);
        }
    }
    public void setOnStaggerItemClickListener(OnStaggerItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface OnStaggerItemClickListener{
        void onItemClickListener(Commodities.DataBean item);
    }
}
