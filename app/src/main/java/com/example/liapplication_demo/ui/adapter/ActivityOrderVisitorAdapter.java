package com.example.liapplication_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.model.domain.Visitor;
import com.example.liapplication_demo.ui.activity.ActivityOrderActivity;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityOrderVisitorAdapter extends RecyclerView.Adapter<ActivityOrderVisitorAdapter.InnerHolder> {

    private ArrayList<Visitor> mVisitors = new ArrayList<>();
    private ActivityOrderActivity mContext;

    public ActivityOrderVisitorAdapter(ActivityOrderActivity context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ActivityOrderVisitorAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_activity_visitor, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityOrderVisitorAdapter.InnerHolder holder, int position) {
        Visitor visitor = mVisitors.get(position);
        holder.setData(visitor);
    }

    @Override
    public int getItemCount() {
        if (mVisitors != null) {
            return mVisitors.size();
        }
        return 0;
    }

    public void setData(List<Visitor> visitorData) {
//        mVisitors.clear();
//        mVisitors.addAll(visitorData);
        mVisitors = (ArrayList)visitorData;
        notifyDataSetChanged();
    }


    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.visitor_info_name)
        public TextView visitorName;
        @BindView(R.id.visitor_info_id)
        public TextView visitorId;
        @BindView(R.id.delete)
        public ImageButton mDeleteBtn;

        public int pos;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos = getAdapterPosition();
                    LogUtils.d(ActivityOrderVisitorAdapter.this, "delete listener --> " + pos);

                    mVisitors.remove(pos);
                    LogUtils.d(ActivityOrderVisitorAdapter.this, "after delete  mVisitors.remove(pos) --> " + mVisitors.toString());

                    // 删除数据
                    notifyItemRemoved(pos);

                    mContext.getPrice();
                }
            });


        }

        public void setData(Visitor visitor) {
            visitorName.setText(visitor.getName());
            visitorId.setText(visitor.getId() + "");
        }

    }
}
