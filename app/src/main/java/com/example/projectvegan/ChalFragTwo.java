package com.example.projectvegan;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectvegan.databinding.FragmentChalFragOneBinding;
import com.example.projectvegan.databinding.FragmentChalFragTwoBinding;
import com.example.projectvegan.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class ChalFragTwo extends Fragment {
    private FragmentChalFragTwoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChalFragTwoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }
}