package com.example.liapplication_demo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.data.CommodityOrderDataBean;
import com.example.liapplication_demo.ui.adapter.CommodityOrderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的商品订单fragment
 */
public class MyCommodityOrderFragment extends Fragment {
    private View root;

    private RecyclerView orderListView;
    private List<CommodityOrderDataBean> orderList;

    public static MyCommodityOrderFragment newInstance() {
        Bundle args = new Bundle();
        MyCommodityOrderFragment fragment = new MyCommodityOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root == null)
            root = inflater.inflate(R.layout.fragment_my_order, container, false);

        orderList = new ArrayList<>();
        orderList.add(new CommodityOrderDataBean("111", "阿里支付", 100.0, 1));
        orderList.add(new CommodityOrderDataBean("111", "阿里健康", 76.4, 1));
        orderList.add(new CommodityOrderDataBean("111", "阿里支付", 123.01, 2));

        orderListView = root.findViewById(R.id.ordList);
        orderListView.setAdapter(new CommodityOrderListAdapter(orderList, getActivity()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        orderListView.setLayoutManager(linearLayoutManager);
        orderListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return root;
    }
}