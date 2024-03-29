package com.example.liapplication_demo.presenter.impl;

import com.example.liapplication_demo.model.Api;
import com.example.liapplication_demo.model.domain.Commodities;
import com.example.liapplication_demo.presenter.ICategoryPagerPresenter;
import com.example.liapplication_demo.utils.LogUtils;
import com.example.liapplication_demo.utils.RetrofitManager;
import com.example.liapplication_demo.utils.UrlUtils;
import com.example.liapplication_demo.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryPagerPresenterImpl implements ICategoryPagerPresenter {

    ICategoryPagerCallback mCallback = null;
    private Map<String, Integer> pageInfo = new HashMap<>();
    private static final int DEFAULT_PAGE = 1;

    private CategoryPagerPresenterImpl(){}

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getInstance(){
        if (sInstance == null) {
            sInstance = new CategoryPagerPresenterImpl();
        }
        return sInstance;
    }

    @Override
    public void getContentByCategory(String categoryTitle, int categoryId) {

        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryId() == categoryId) {
                callback.onLoading();
            }
        }
        //根据分类加载内容
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Integer targetPage = pageInfo.get(categoryTitle);
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pageInfo.put(categoryTitle, targetPage);
        }
        String shopPagerUrl = UrlUtils.createShopPagerUrl(categoryTitle);
        LogUtils.d(CategoryPagerPresenterImpl.this, "shop pager url --> " + shopPagerUrl);

        Call<Commodities> task = api.getCommoditiesByCategory(shopPagerUrl);

        task.enqueue(new Callback<Commodities>() {
            @Override
            public void onResponse(Call<Commodities> call, Response<Commodities> response) {
                int code = response.code();
                LogUtils.d(CategoryPagerPresenterImpl.this, "code --> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    Commodities commodities = response.body();
                    LogUtils.d(CategoryPagerPresenterImpl.this, "commodities --> " + commodities.toString());
                    //更新UI
                    handleShopPagerContentResult(commodities, categoryId);
                } else {
                    //请求失败
                    handlerNetworkError(categoryId);
                }
            }
            @Override
            public void onFailure(Call<Commodities> call, Throwable t) {
                //请求错误
                LogUtils.d(CategoryPagerPresenterImpl.this, "onFailure --> " + t.toString());
                handlerNetworkError(categoryId);
            }
        });
    }

    private void handlerNetworkError(int categoryId) {
        for (ICategoryPagerCallback callback : callbacks){
            if (callback.getCategoryId() == categoryId) {
                callback.onError();

            }
        }
    }

    /**
     * 通知UI更新
     * @param commodities
     * @param categoryId
     */
    private void handleShopPagerContentResult(Commodities commodities, int categoryId) {
        for (ICategoryPagerCallback callback : callbacks){
            if (callback.getCategoryId() == categoryId) {
                if (commodities == null || commodities.getData().size() == 0) {
                    callback.onEmpty();
                } else {
                    callback.onContentLoaded(commodities.getData());
                }
            }
        }
    }

    @Override
    public void loadMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }

    private List<ICategoryPagerCallback> callbacks = new ArrayList<>();
    @Override
    public void registerCallback(ICategoryPagerCallback callback) {
        if (!callbacks.contains(callback)) {
            callbacks.add(callback);
        }

    }

    @Override
    public void unregisterCallback(ICategoryPagerCallback callback) {
        callbacks.remove(callback);
    }
}
