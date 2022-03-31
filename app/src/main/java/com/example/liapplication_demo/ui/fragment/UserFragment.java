package com.example.liapplication_demo.ui.fragment;

import android.view.View;

import com.example.liapplication_demo.R;
import com.example.liapplication_demo.base.BaseFragment;

public class UserFragment extends BaseFragment {
    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View rootView) {
        setUpStates(State.SUCCESS);

    }
}
