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
import com.example.liapplication_demo.data.ActOrderDataBean;
import com.example.liapplication_demo.ui.adapter.ActOrderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单中活动订单的Fragment
 */
public class MyActOrderFragment extends Fragment {

    private View root;
    private RecyclerView actOrderListView;
    private List<ActOrderDataBean> actOrderList;

    public static MyActOrderFragment newInstance() {
        Bundle args = new Bundle();
        MyActOrderFragment fragment = new MyActOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 解析xml
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root == null)
            root = inflater.inflate(R.layout.fragment_my_act_order, container, false);

        actOrderList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            actOrderList.add(new ActOrderDataBean("111101019191919", "测试" + i, "10001", "1", "11111"));
        }

        actOrderListView = root.findViewById(R.id.rv);
        actOrderListView.setAdapter(new ActOrderListAdapter(actOrderList, getActivity()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        actOrderListView.setLayoutManager(linearLayoutManager);
        actOrderListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        return root;
    }
}