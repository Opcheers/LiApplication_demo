package com.example.liapplication_demo.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.ActivityOrder;
import com.example.liapplication_demo.presenter.impl.MyActivityOrderPresenterImpl;
import com.example.liapplication_demo.ui.adapter.ActOrderListAdapter;
import com.example.liapplication_demo.view.IMyActivityOrderCallback;

import java.util.List;

import butterknife.BindView;

/**
 * 我的订单中活动订单的Fragment
 */
public class MyActOrderFragment extends BaseFragment implements IMyActivityOrderCallback {

    @BindView(R.id.activity_order_list_view)
    public RecyclerView mActOrderListView;

    private MyActivityOrderPresenterImpl mPresenter;
    private ActOrderListAdapter mAdapter;

    public static MyActOrderFragment newInstance() {
        Bundle args = new Bundle();
        MyActOrderFragment fragment = new MyActOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View rootView) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActOrderListView.setLayoutManager(layoutManager);
        mAdapter = new ActOrderListAdapter();
        mActOrderListView.setAdapter(mAdapter);
    }

    @Override
    protected void initPresenter() {
        //初始化presenter
        mPresenter = new MyActivityOrderPresenterImpl();
        mPresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据后设置适配器
        mPresenter.getActivityOrder();
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_my_act_order;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onActivityOrderLoaded(List<ActivityOrder.DataBean> activityOrders) {
        //数据从这里回来
        mAdapter.setData(activityOrders);
        setUpStates(State.SUCCESS);
    }
}