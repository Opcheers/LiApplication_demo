package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Calligraphy;
import com.example.liapplication_demo.presenter.impl.CalligraphyPresenterImpl;
import com.example.liapplication_demo.ui.adapter.CalligraphyAdapter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.view.ICalligraphyCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CalligraphyAllActivity extends BaseActivity implements ICalligraphyCallback, CalligraphyAdapter.OnListItemClickListener {

   @BindView(R.id.calligraphy_list)
   public RecyclerView  mCalligraphyList;

   private List<Calligraphy.DataBean> mDataList = new ArrayList<>();
   private CalligraphyAdapter mAdapter;
   private CalligraphyPresenterImpl mPresenter;
   private List<String> mPics = new ArrayList<>();;


   @Override
   protected void initView() {
      initPresenter();
      initAdapter();
      loadData();
   }

   private void loadData() {
      //加载数据
      mPresenter.getCalligraphy();
   }

   private void initAdapter() {
      //设置布局管理器
      LinearLayoutManager layoutManager = new LinearLayoutManager(this);
      layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      mCalligraphyList.setLayoutManager(layoutManager);

      //创建适配器
      mAdapter = new CalligraphyAdapter();
      mCalligraphyList.setAdapter(mAdapter);

   }

   private void initPresenter() {
      //设置presenter
      mPresenter = new CalligraphyPresenterImpl();
      mPresenter.registerCallback(this);
   }


   @Override
   protected void initEvent() {
      //给适配器设置监听
      mAdapter.setOnListItemClickListener(this);
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
   public void onCalligraphyLoaded(List<Calligraphy.DataBean> calligraphyList) {
      //数据从这里回来
      LogUtils.d(CalligraphyAllActivity.this, " calligraphy --> " + calligraphyList.toString());
      mDataList.clear();
      mDataList = calligraphyList;

      if (mAdapter != null) {
         mAdapter.setData(mDataList);
      }
   }


   @Override
   protected int getLayoutResId() {
      return R.layout.activity_calligraphy_all;
   }


   @Override
   public void onItemClickListener(Calligraphy.DataBean item) {
      //点击时跳转到字画详情
      LogUtils.d(this, "点击跳转到字画详情：" + item.toString());
      Intent intent = new Intent(CalligraphyAllActivity.this, CalligraphyDetailActivity.class);
      Bundle bundle = new Bundle();
      bundle.putSerializable("calligraphy", item);
      intent.putExtras(bundle);
      startActivity(intent);
   }
}
