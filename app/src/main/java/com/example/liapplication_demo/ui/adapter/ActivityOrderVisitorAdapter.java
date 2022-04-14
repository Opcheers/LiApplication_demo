package com.example.liapplication_demo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.model.domain.identityInfoVOList;
import com.example.liapplication_demo.ui.activity.ActivityOrderActivity;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityOrderVisitorAdapter extends RecyclerView.Adapter<ActivityOrderVisitorAdapter.InnerHolder> {

    private ArrayList<identityInfoVOList> mIdentityInfoVOLists = new ArrayList<>();
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
        identityInfoVOList identityInfoVOList = mIdentityInfoVOLists.get(position);
        holder.setData(identityInfoVOList);
    }

    @Override
    public int getItemCount() {
        if (mIdentityInfoVOLists != null) {
            return mIdentityInfoVOLists.size();
        }
        return 0;
    }

    public void setData(List<identityInfoVOList> identityInfoVOListData) {
        mIdentityInfoVOLists = (ArrayList) identityInfoVOListData;
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

                    mIdentityInfoVOLists.remove(pos);
                    LogUtils.d(ActivityOrderVisitorAdapter.this, "after delete  mIdentityInfoVOLists.remove(pos) --> " + mIdentityInfoVOLists.toString());

                    // 删除数据
                    notifyItemRemoved(pos);

                    mContext.setPrice();
                }
            });


        }

        public void setData(identityInfoVOList identityInfoVOList) {
            visitorName.setText(identityInfoVOList.getName());
            visitorId.setText(identityInfoVOList.getIdentity() + "");
        }

    }
}
