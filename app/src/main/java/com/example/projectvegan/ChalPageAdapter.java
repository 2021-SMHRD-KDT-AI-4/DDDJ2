package com.example.projectvegan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectvegan.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.projectvegan.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ChalPageAdapter extends FragmentStateAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ChalPageAdapter(FragmentActivity fragmentActivity){
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