package com.example.liapplication_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityOrderAdapter extends RecyclerView.Adapter<ActivityOrderAdapter.InnerHolder> {

    ArrayList<String> mActDate = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
    private OnClickDateListListener mDateItemListener = null;
    private int mPosition;
    double mActPrice;


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_activity_date, parent, false);
        final InnerHolder holder = new InnerHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        String data = mActDate.get(position);
        holder.setData(data);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDateItemListener != null) {
                    mDateItemListener.OnClickItemListener(data);

                    if (!mViewList.contains(v)) {
                        if (mViewList.size() == 0) {//第一次点击
                            mViewList.add(v);
                            v.findViewById(R.id.item_frame).setBackground(v.getResources().getDrawable(R.drawable.shape_rounded_corners_with_line_miancolor));

                        }else{//已经选中了一个了
                            mViewList.get(0).findViewById(R.id.item_frame).setBackground(v.getResources().getDrawable(R.drawable.shape_rounded_corners_with_line));
                            mViewList.remove(0);
                            mViewList.add(v);
                            v.findViewById(R.id.item_frame).setBackground(v.getResources().getDrawable(R.drawable.shape_rounded_corners_with_line_miancolor));
                        }
                    }
                }
            }
        });

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
        @BindView(R.id.item_frame)
        public LinearLayout mItem;

        View mView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setData(String date) {
            mDate.setText(date);
            mPrice.setText("￥" + mActPrice);
        }


    }

    public void setOnClickDateListListener(OnClickDateListListener listener){
        this.mDateItemListener = listener;
    }

    public interface OnClickDateListListener{
        void OnClickItemListener(String item);
    }
}
