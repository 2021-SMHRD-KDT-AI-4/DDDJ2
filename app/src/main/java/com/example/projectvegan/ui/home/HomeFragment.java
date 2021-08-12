package com.example.projectvegan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectvegan.Challenge;
import com.example.projectvegan.Rank;
import com.example.projectvegan.Quiz;
import com.example.projectvegan.Recipe;
import com.example.projectvegan.Scanner;
import com.example.projectvegan.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button btn_chal = binding.btnChal;
        final Button btn_scanner = binding.btnScanner;
        final Button btn_rank = binding.btnRank;
        final TextView tv_main_quiz =  binding.tvMainQuiz;
        final TextView tv_main_recipe = binding.tvMainRecipe;
        final ImageView img_main_recipe = binding.imgMainRecipe;
        tv_main_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Recipe.class);
                startActivity(intent);
            }
        });

        img_main_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Recipe.class);
                startActivity(intent);
            }
        });

        tv_main_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Quiz.class);
                startActivity(intent);
            }
        });

        btn_chal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Challenge.class);
                startActivity(intent);
            }
        });
        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Scanner.class);
                startActivity(intent);
            }
        });
        btn_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Rank.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}