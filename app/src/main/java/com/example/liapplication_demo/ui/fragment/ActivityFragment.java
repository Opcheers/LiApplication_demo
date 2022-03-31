package com.example.liapplication_demo.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;
import com.example.liapplication_demo.model.domain.FarmActivities;
import com.example.liapplication_demo.presenter.impl.ActivityPagePresenterImpl;
import com.example.liapplication_demo.ui.adapter.ActivityPageAdapter;
import com.example.liapplication_demo.view.IFarmActivitiesCallback;

import java.util.List;

import butterknife.BindView;

public class ActivityFragment extends BaseFragment implements IFarmActivitiesCallback {

    @BindView(R.id.farm_activity_list_view)
    public RecyclerView mList;

    private ActivityPagePresenterImpl mActivityPagePresenter;
    private ActivityPageAdapter mAdapter;


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initView(View rootView) {
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(false);
        mList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new ActivityPageAdapter();
        mList.setAdapter(mAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mActivityPagePresenter = new ActivityPagePresenterImpl();
        mActivityPagePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据
        mActivityPagePresenter.getFarmActivities();
    }

    @Override
    public void onFarmActivitiesLoaded(List<FarmActivities.DataBean> farmActivities) {
        //加载的数据从这里回来
        setUpStates(State.SUCCESS);
        if (mAdapter != null){
            mAdapter.setFarmActivities(farmActivities);
        }
    }

    @Override
    public void onNetworkError() {
        setUpStates(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpStates(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpStates(State.EMPTY);
    }

    @Override
    protected void release() {
        //取消回调注册
        if (mActivityPagePresenter != null) {
            mActivityPagePresenter.unregisterCallback(this);
        }
    }


}
