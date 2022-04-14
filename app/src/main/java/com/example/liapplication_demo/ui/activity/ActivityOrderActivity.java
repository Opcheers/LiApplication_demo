package com.example.liapplication_demo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseActivity;
import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.PostActivityOrder;
import com.example.liapplication_demo.model.domain.PostResult;
import com.example.liapplication_demo.model.domain.User;
import com.example.liapplication_demo.model.domain.identityInfoVOList;
import com.example.liapplication_demo.ui.adapter.ActivityOrderAdapter;
import com.example.liapplication_demo.ui.adapter.ActivityOrderVisitorAdapter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityOrderActivity extends BaseActivity implements ActivityOrderAdapter.OnClickDateListListener {

    private static final int ADD_REQUEST_CODE = 1;//转到添加界面
    private static final int PAY_REQUEST_CODE = 2;//转到充值界面
    @BindView(R.id.date_list_view)
    public RecyclerView mDateList;
    @BindView(R.id.add_visitor)
    public ImageButton mAddVisitorBtn;
    @BindView(R.id.visitor_info_list_view)
    public RecyclerView mVisitorList;
    @BindView(R.id.order_pay_btn)
    public Button mPayBtn;
    @BindView(R.id.total_price)
    public TextView mTotalPrice;
    @BindView(R.id.actOrderSite)
    public EditText mActSiteEt;
    @BindView(R.id.actOrderTel)
    public EditText mActTelEt;

    private ActivityOrderAdapter mOrderDateAdapter;
    private ActivityOrderVisitorAdapter mVisitorAdapter;

    private ArrayList<String> mActDateList;//订单日期
    private double mActPrice;//价格
    private String mActId;
    private List<identityInfoVOList> mIdentityInfoVOListData = new ArrayList<>();//游客
    private double mTotalPriceNum;
    private String mActDate = null;//选中的日期
    private PostActivityOrder mActivityOrder;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_activity_order;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mActId = intent.getStringExtra("actId");;
        mActDateList = intent.getStringArrayListExtra("actDate");
        mActPrice = intent.getDoubleExtra("actPrice", -1);

        if (mActDateList == null || mActDateList.size() == 0){
            mActDateList.add("该活动已过期");
        }

        //加载dateListView
        loadDateListView();

        //加载visitorListView
        loadVisitorListView();
    }


    @Override
    protected void initEvent() {
        onPayBtnListener();
        onAddBtnListener();

        //给日期适配器设置监听
        mOrderDateAdapter.setOnClickDateListListener(this);
    }

    /**
     * 添加游客
     */
    private void onAddBtnListener() {
        mAddVisitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到添加界面
                Intent intent = new Intent(ActivityOrderActivity.this, ActivityAddVistorActivity.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });
    }

    /**
     * 去支付
     */
    private void onPayBtnListener() {
        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //信息校验+跳转到支付界面
                handlerOrderInfo();
            }

        });
    }

    private void handlerOrderInfo() {
        String actSite = mActSiteEt.getText().toString().trim();
        String actPhone = mActTelEt.getText().toString().trim();
        if (TextUtils.isEmpty(mActDate)) {
            Toast.makeText(this, "请选择预定日期", Toast.LENGTH_SHORT).show();
            return ;
        }
        if (TextUtils.isEmpty(actSite)){
            Toast.makeText(this, "请填写乘车地点", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(actPhone)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isDigitsOnly(actPhone) || actPhone.length()!=11){
            Toast.makeText(this, "手机号为11位数字，不能包含其他字符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mIdentityInfoVOListData == null || mIdentityInfoVOListData.size() == 0){
            Toast.makeText(this, "请添加乘客信息", Toast.LENGTH_SHORT).show();
            return;
        }

        mActivityOrder = new PostActivityOrder(mActId, mIdentityInfoVOListData, mActDate, mIdentityInfoVOListData.size(), actSite, User.userId, actPhone);

        LogUtils.d(this, "handlerOrderInfo --> " + mActivityOrder.toString());

        //转到收银台：传价格
        Intent intent = new Intent(ActivityOrderActivity.this, PayOrderActivity.class);

        intent.putExtra("actPrice", mActPrice);
        startActivityForResult(intent, PAY_REQUEST_CODE);


//        Bundle bundle = new Bundle();
//        bundle.putParcelable("activityOrder", activityOrder);
//        intent.putExtras(bundle);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    identityInfoVOList identityInfoVOList = data.getParcelableExtra("identityInfoVOList");
                    mIdentityInfoVOListData.add(identityInfoVOList);
                    loadVisitorListView();
                }
                break;
            case PAY_REQUEST_CODE:
                if (resultCode == 21) {
                    Toast.makeText(this, "支付成功！", Toast.LENGTH_SHORT).show();
                    createActivityOrder();
                } else if (resultCode == 22){
                    Toast.makeText(this, "支付失败！", Toast.LENGTH_SHORT).show();
                }
        }
    }

    /**
     * 创建订单提交给后台
     */
    private void createActivityOrder() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<PostResult> task  = api.createActOrder(mActivityOrder);
        LogUtils.d(this, "activity order --> " + mActivityOrder.toString());
        task.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                int code = response.code();
                LogUtils.d(this, "code --> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    //成功
                    LogUtils.d(this, "订单创建成功：" + response.body().getMsg());
                } else {
                    //失败
                    LogUtils.d(this, "订单创建失败：" + response.message());

                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                LogUtils.d(this, "订单创建失败：" + t);

            }
        });
    }

    /**
     * 加载dateListView
     */
    private void loadDateListView() {
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDateList.setLayoutManager(layoutManager);

        //创建日期适配器
        mOrderDateAdapter = new ActivityOrderAdapter();
        mDateList.setAdapter(mOrderDateAdapter);
        mOrderDateAdapter.setData(mActDateList, mActPrice);
    }


    /**
     * 加载VistorListView
     */
    private void loadVisitorListView() {
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVisitorList.setLayoutManager(layoutManager);

        mVisitorAdapter = new ActivityOrderVisitorAdapter(this);
        mVisitorList.setAdapter(mVisitorAdapter);
        mVisitorAdapter.setData(mIdentityInfoVOListData);

        setPrice();
    }


    public void setPrice() {
        mTotalPriceNum = mActPrice * mIdentityInfoVOListData.size();
        mTotalPrice.setText("总额：￥" + String.format("%.2f", mTotalPriceNum));
    }


    @Override
    public void OnClickItemListener(String item) {
        /**
         * 监听到点击事件：
         * 1.框框变色
         * 2.获取orderdate
         */
        mActDate = item;
        //LogUtils.d(this, "date item click --> date: " + item);

    }


}
