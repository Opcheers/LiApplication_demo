package com.example.liapplication_demo.ui.fragment;

import android.view.View;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;

public class HomePagerFragment extends BaseFragment {

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootView) {
        setUpStates(State.SUCCESS);
    }
}
