package com.example.liapplication_demo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.data.ActOrderDataBean;
import com.example.liapplication_demo.ui.activity.QrcodeActivity;

import java.util.List;

public class ActOrderListAdapter extends RecyclerView.Adapter<ActOrderListAdapter.InnerHolder> {
    private List<ActOrderDataBean> actOrderList;
    private Context context;

    public ActOrderListAdapter(List<ActOrderDataBean> actOrderList, Context context) {
        this.actOrderList = actOrderList;
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.act_order_item, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.actImage.setImageResource(R.color.red);
        holder.actName.setText(actOrderList.get(position).getActName());
        holder.actNum.setText("共" + actOrderList.get(position).getOrderNum() + "件");
        holder.actPrice.setText("￥" + actOrderList.get(position).getActTotalPay());
    }

    @Override
    public int getItemCount() {
        return actOrderList.size();
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
    }
}
