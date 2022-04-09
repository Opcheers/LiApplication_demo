package com.example.liapplication_demo.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.CommodityOrder;
import com.example.liapplication_demo.presenter.impl.MyCommodityOrderPresenterImpl;
import com.example.liapplication_demo.ui.adapter.CommodityOrderListAdapter;
import com.example.liapplication_demo.view.IMyCommodityOrderCallback;

import java.util.List;

import butterknife.BindView;

/**
 * 我的商品订单fragment
 */
public class MyComOrderFragment extends BaseFragment implements IMyCommodityOrderCallback {


    @BindView(R.id.ordList)
    public RecyclerView orderListView;

    private MyCommodityOrderPresenterImpl mPresenter;
    private CommodityOrderListAdapter mAdapter;

    public static MyComOrderFragment newInstance() {
        Bundle args = new Bundle();
        MyComOrderFragment fragment = new MyComOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View rootView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        orderListView.setLayoutManager(linearLayoutManager);
        orderListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new CommodityOrderListAdapter(this.getContext());
        orderListView.setAdapter(mAdapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MyCommodityOrderPresenterImpl();
        mPresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getCommodityOrder();
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_my_order;
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
    public void onCommodityOrderLoaded(List<CommodityOrder.DataBean> commodityOrders) {
        mAdapter.setData(commodityOrders);
        setUpStates(State.SUCCESS);
    }
}