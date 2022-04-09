package com.example.liapplication_demo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liapplication_demo.R;
import com.example.liapplication_demo.model.domain.ActivityOrder;
import com.example.liapplication_demo.ui.activity.QrcodeActivity;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.List;

public class ActOrderListAdapter extends RecyclerView.Adapter<ActOrderListAdapter.InnerHolder> {
    private List<ActivityOrder.DataBean> actOrderList;
    private Context context;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_order_item, parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(actOrderList.get(position));
    }

    @Override
    public int getItemCount() {
        return actOrderList.size();
    }

    public void setData(List<ActivityOrder.DataBean> activityOrders) {
        LogUtils.d(ActOrderListAdapter.this, "setdata...");
        actOrderList = activityOrders;
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView actImage;
        private TextView actName;
        private TextView actNum;
        private TextView actPrice;
        private TextView intentButton;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            actImage = itemView.findViewById(R.id.actIcon);
            actName = itemView.findViewById(R.id.actName);
            actNum = itemView.findViewById(R.id.actNum);
            actPrice = itemView.findViewById(R.id.actPrice);
            intentButton = itemView.findViewById(R.id.showButton);

            intentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, QrcodeActivity.class));
                }
            });
        }

        public void setData(ActivityOrder.DataBean dataBean) {
            Glide.with(itemView.getContext()).load(dataBean.getActPreview()).into(actImage);
            actName.setText(dataBean.getActName());
            actNum.setText("共"+dataBean.getOrderQuantity()+"件");
            actPrice.setText("￥"+dataBean.getOrderAmount());
        }
    }
}
