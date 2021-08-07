package com.example.projectvegan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.ArrayList;
import java.util.List;

public class TabPageAdapter extends FragmentStateAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public TabPageAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    public void addFrag(Fragment fm){
        mFragmentList.add(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Fragment 교체를 보여주는 처리 구현
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        // View의 전체 개수
        return mFragmentList.size();
    }
}
