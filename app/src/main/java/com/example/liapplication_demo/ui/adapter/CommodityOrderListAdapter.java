package com.example.liapplication_demo.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.data.CommodityOrderDataBean;

import java.util.List;

/**
 * 我的商品订单列表适配器
 */
public class CommodityOrderListAdapter extends RecyclerView.Adapter<CommodityOrderListAdapter.InnerHolder> {

    private List<CommodityOrderDataBean> orderList;
    private Context context;

    public CommodityOrderListAdapter(List<CommodityOrderDataBean> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 过滤layout布局放置在view中
        View view = View.inflate(context, R.layout.orderitem, null);
        return new InnerHolder(view);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.imgIcon.setImageResource(R.color.blue_40A9FF);
        holder.comNum.setText("共" + orderList.get(position).getComQuantity() + "件");
        holder.comName.setText(orderList.get(position).getComName());
        holder.comPrice.setText("￥" + orderList.get(position).getComPrice());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView comName;
        private TextView comNum;
        private TextView comPrice;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.comIcon);
            comName = itemView.findViewById(R.id.comName);
            comNum = itemView.findViewById(R.id.comNum);
            comPrice = itemView.findViewById(R.id.comPrice);
        }
    }
}
