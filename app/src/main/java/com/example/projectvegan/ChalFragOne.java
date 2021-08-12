package com.example.projectvegan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectvegan.databinding.FragmentChalFragOneBinding;
import com.example.projectvegan.databinding.FragmentHomeBinding;


public class ChalFragOne extends Fragment {

    private FragmentChalFragOneBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChalFragOneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button btn_notice = binding.btnNotice;
        final Button btn_ex = binding.btnEx;
        final Button btn_chal = binding.btnChal;

        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Notice.class));

            }
        });

        btn_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_chal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return root;
    }
}