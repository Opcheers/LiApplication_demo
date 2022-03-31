package com.example.liapplication_demo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.liapplication_demo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private State currentState = State.NONE;
    private View mSuccessView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public enum State{
        NONE, LOADING, SUCCESS, ERROR, EMPTY
    }

    private Unbinder mBind;
    private FrameLayout mBaseContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.base_fragment_layout, container, false);
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater, container);
        mBind = ButterKnife.bind(this, rootView);
        initView(rootView);
        initPresenter();
        loadData();
        return rootView;

    }

    /**
     * 加载各种状态的View
     * @param inflater
     * @param container
     */
    private void loadStatesView(LayoutInflater inflater, ViewGroup container) {

        //成功的View
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);

        //LodingView
        mLoadingView = loadLodingView(inflater, container);
        mBaseContainer.addView(mLoadingView);

        //错误页面
        mErrorView = LoadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);

        //空页面
        mEmptyView = LoadEmptyView(inflater, container);
        mBaseContainer.addView(mEmptyView);

        //一开始默认为NONE
        setUpStates(State.NONE);
    }


    /**
     * 子类通过这个方法切换页面
     * @param state
     */
    public void setUpStates(State state){
        this.currentState = state;

        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE : View. GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE : View.GONE);
    }

    /**
     * 加载成功页面
     * @param inflater
     * @param container
     * @return
     */
    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container){
        int resid = getRootViewResId();
        return inflater.inflate(resid, container, false);
    }

    /**
     * 加载LodingView
     * @param inflater
     * @param container
     */
    protected View loadLodingView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    /**
     * 加载error页面
     * @param inflater
     * @param container
     * @return
     */
    protected View LoadErrorView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    /**
     * 加载空页面
     * @param inflater
     * @param container
     * @return
     */
    protected View LoadEmptyView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    protected void initView(View rootView){}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind!=null) {
            mBind.unbind();
        }
        release();
    }

    protected void release() {
        //释放资源
    }

    protected void initPresenter() {
        //创建presenter
    }


    protected void loadData(){
        //加载数据
    }



    protected abstract int getRootViewResId();
}
