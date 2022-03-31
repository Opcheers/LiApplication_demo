package com.example.liapplication_demo.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.liapplication_demo.model.domain.Station;
import com.example.liapplication_demo.ui.fragment.HomePagerFragment;
import com.example.liapplication_demo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<Station.DataBean> stationList = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stationList.get(position).getStationName();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        return homePagerFragment;
    }

    @Override
    public int getCount() {
        return stationList.size();
    }

    public void setStation(Station station) {
        stationList.clear();
        List<Station.DataBean> data = station.getData();
        this.stationList.addAll(data);
        LogUtils.d(this, "setStation --> station.size:" + this.stationList.size());
        notifyDataSetChanged();
    }
}
