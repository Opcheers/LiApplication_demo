package com.example.liapplication_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityOrderAdapter extends RecyclerView.Adapter<ActivityOrderAdapter.InnerHolder> {

    ArrayList<String> mActDate = new ArrayList<>();
    double mActPrice;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_activity_date, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        String data = mActDate.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        if (mActDate != null) {
            return mActDate.size();
        }
        return 0;
    }

    public void setData(ArrayList<String> actDate, double actPrice) {
        mActDate.clear();
        mActDate.addAll(actDate);
        mActPrice = actPrice;
        notifyDataSetChanged();
    }



    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        public TextView mDate;
        @BindView(R.id.price)
        public TextView mPrice;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(String date) {
            mDate.setText(date);
            mPrice.setText("￥" + mActPrice);
        }
    }
}
