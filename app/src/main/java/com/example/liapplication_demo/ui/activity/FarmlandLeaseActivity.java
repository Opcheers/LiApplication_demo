package com.example.liapplication_demo.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Farmland;
import com.example.liapplication_demo.presenter.impl.FarmlandIdPresenterImpl;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.view.IFarmLandIdCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FarmlandLeaseActivity extends BaseActivity implements IFarmLandIdCallback{

    @BindView(R.id.group_id)
    public Spinner mGroupSpinner;
    @BindView(R.id.farmland_id)
    public Spinner mFarmlandSpinner;
    @BindView(R.id.lease_mode_id)
    public Spinner mLeaseModeSpinner;
    @BindView(R.id.farmland_lease_next_btn)
    public Button mNext;

    private FarmlandIdPresenterImpl mFarmlandIdPresenter;
    private ArrayAdapter mGroupIdAdapter;
    private ArrayAdapter mFarmlandIdAdapter;
    private ArrayAdapter mLeaseModeAdapter;
    private List<String> mGroupIdList = new ArrayList<>();
    private List<Farmland> mFarmlandList = new ArrayList<>();
    private Farmland mFarmland;
    private String mGroupId = "001";
    private String mFarmlandId = "001001";
    private int mLeaseMode;//租赁模式
    private double mFarmlandPrice;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_farmland_lease;
    }

    @Override
    protected void initView() {

        initPresenter();
        loadData();

        //加载租赁模式
        onLeaseModeLoad();
    }



    @Override
    protected void initEvent() {
        // 生产组spinner监听
        onGroupSpinnerListener();

        // 生产组spinner监听
        onFarmlandSpinnerListener();

        //租赁模式监听
        onLeaseModeSpinnerListener();

        //支付按钮监听
        onClickNextBtnListener();
    }


    private void onClickNextBtnListener() {
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmlandLeaseActivity.this, FarmLandPayActivity.class );
                //把地传过去
                mFarmland.setLeaseMode(mLeaseMode);
                Bundle bundle = new Bundle();
                bundle.putSerializable("farmland", mFarmland);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 生产组spinner监听：
     *  1.拿到生产组id
     *  2.根据选项加载土地编号
     */
    private void onGroupSpinnerListener() {
        mGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.d(this, "点击的是：" + mGroupIdList.get(position));
                mGroupId = mGroupIdList.get(position);
                //根据生产组编号获取土地编号
                mFarmlandIdPresenter.getFarmlandId(mGroupId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 土地spinner监听：
     * 1.拿到土地
     */
    private void onFarmlandSpinnerListener() {
        mFarmlandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mFarmland = mFarmlandList.get(i);
                mFarmlandId = mFarmlandList.get(i).getFmId();
                mFarmlandPrice = mFarmlandList.get(i).getFmPrice();
                //LogUtils.d(this, "mFarmlandId --> " + mFarmlandId + ", mFarmlandPrice --> " + mFarmlandPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 租赁模式监听：
     * 1.无托管-1
     * 2.全托管-2
     */
    private void onLeaseModeSpinnerListener() {
        mLeaseModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mLeaseMode = i;
                switch (i){
                    case 0:
                        LogUtils.d(this, "lease mode --> 无托管, mLeaseMode --> " + i);
                        break;
                    case 1:
                        LogUtils.d(this, "lease mode --> 全托管, mLeaseMode -> " + i);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 设置presenter
     */
    private void initPresenter() {
        mFarmlandIdPresenter = new FarmlandIdPresenterImpl();
        mFarmlandIdPresenter.registerCallback(this);
    }

    /**
     * 请求数据
     */
    private void loadData() {
        //生产组编号
        mFarmlandIdPresenter.getGroupId();

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

    /**
     * 生产组编号数据加载
     * @param groupIdList
     */
    @Override
    public void onGroupIdLoaded(List<String> groupIdList) {
        //数据从这里回来
        LogUtils.d(this, "groupIdList --> " + groupIdList);
        mGroupIdList = groupIdList;
        //给到适配器
        mGroupIdAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, groupIdList);
        mGroupIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGroupSpinner.setAdapter(mGroupIdAdapter);

    }

    /**
     * 土地编号动态加载
     * @param farmlandList
     */
    @Override
    public void onFarmlandIdLoaded(List<Farmland> farmlandList) {
        mFarmlandList = farmlandList;
        //拿到farmlandIdList
        List<String> farmlandIdList = new ArrayList<>();
        for (Farmland item : farmlandList) {
            farmlandIdList.add(item.getFmId());
        }
        mFarmlandIdAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, farmlandIdList);
        mFarmlandIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFarmlandSpinner.setAdapter(mFarmlandIdAdapter);
    }

    private void onLeaseModeLoad() {
        mLeaseModeAdapter = ArrayAdapter.createFromResource(this, R.array.lease_mode, android.R.layout.simple_list_item_activated_1);
        mLeaseModeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLeaseModeSpinner.setAdapter(mLeaseModeAdapter);
    }

}
