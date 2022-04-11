package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.ui.adapter.ComImageAdapter;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import butterknife.BindView;

public class ShopCommodityDetailActivity extends BaseActivity {

    @BindView(R.id.subButton)
    public TextView subButton;
    @BindView(R.id.addButton)
    public TextView addButton;
    @BindView(R.id.commodityNum)
    public TextView comNum;
    @BindView(R.id.payButton)
    public Button payButton;

    @BindView(R.id.price)
    public TextView mPriceTv;
    @BindView(R.id.name)
    public TextView mNameTv;
    @BindView(R.id.detail)
    public TextView mDetailTv;

    @BindView(R.id.commodity_image_container)
    public ViewPager commodityImage;

    private Integer num = new Integer(1);
    private Commodities.DataBean mCommodity;
    private CityPickerView mPicker=new CityPickerView();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_commodity_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();

        mCommodity = (Commodities.DataBean) intent.getSerializableExtra("commodity");
        mPicker.init(this);

        //加载到页面上
        setData();
    }

    private void setData() {
        mPriceTv.setText("￥" + mCommodity.getComPrice());
        mNameTv.setText(mCommodity.getComName());
        mDetailTv.setText(mCommodity.getComDescription());

        ComImageAdapter comImageAdapter = new ComImageAdapter(mCommodity.getComIconList(), this);
        commodityImage.setAdapter(comImageAdapter);
    }

    @Override
    protected void initEvent() {
        onSubBtnListener();
        onAddBtnListener();
        onPayButtonListener();
        onCitySelectorButtonListener();
    }

    private void onCitySelectorButtonListener() {
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);

        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                // 填入处理逻辑

            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(ShopCommodityDetailActivity.this, "已取消");
            }
        });
    }

    private void onPayButtonListener() {
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopCommodityDetailActivity.this, PayOrderActivity.class));
            }
        });
    }

    private void onAddBtnListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                comNum.setText(num.toString());
            }
        });
    }

    private void onSubBtnListener() {
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num > 1) {
                    num--;
                    comNum.setText(num.toString());
                }
            }
        });
    }
}