package com.example.projectvegan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectvegan.databinding.ActivityRankBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectvegan.databinding.ActivityMainBinding;

import java.util.Random;

public class Main extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private int[] imgs = {R.drawable.tree1,R.drawable.tree2,R.drawable.tree3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View nav_header_view = navigationView.getHeaderView(0);




        Button btn_nav_logout = (Button)nav_header_view.findViewById(R.id.btn_nav_logout);
        Button btn_nav_mypage = (Button)nav_header_view.findViewById(R.id.btn_nav_mypage);

        TextView tv_nav_subtitle = (TextView)nav_header_view.findViewById(R.id.tv_nav_subtitle);
        TextView tv_poing = (TextView)nav_header_view.findViewById(R.id.tv_point);
        ImageView img_tree = (ImageView)nav_header_view.findViewById(R.id.img_tree);

        String id = PreferenceManager.getString(getApplicationContext(), "id");
        String pw = PreferenceManager.getString(getApplicationContext(), "pw");
        String name = PreferenceManager.getString(getApplicationContext(), "name");
        String category = PreferenceManager.getString(getApplicationContext(), "category");
        int age = PreferenceManager.getInt(getApplicationContext(), "age");
        String gender = PreferenceManager.getString(getApplicationContext(), "gender");
        String tel = PreferenceManager.getString(getApplicationContext(),"tel");
        int point =PreferenceManager.getInt(getApplicationContext(),"point");

        TextView tv_nav_login = (TextView) nav_header_view.findViewById(R.id.tv_nav_login);
        if(id.equals("데이터가 없습니다.")) {
            tv_nav_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
            });
        }
        if(id.equals(PreferenceManager.DEFAULT_STRING)){
            btn_nav_logout.setVisibility(View.INVISIBLE);
            btn_nav_mypage.setVisibility(View.INVISIBLE);
            tv_poing.setVisibility(View.INVISIBLE);
            img_tree.setVisibility(View.INVISIBLE);
        }else{
            if(point >= 1000){
                img_tree.setImageResource(imgs[2]);
            }else if(point >=500){
                img_tree.setImageResource(imgs[1]);
            }else if(point >=0){
                img_tree.setImageResource(imgs[0]);
            }
            btn_nav_logout.setVisibility(View.VISIBLE);
            btn_nav_mypage.setVisibility(View.VISIBLE);
            tv_poing.setVisibility(View.VISIBLE);
            img_tree.setVisibility(View.VISIBLE);
            tv_nav_login.setText(name);
            tv_nav_subtitle.setText(id);
            tv_poing.setText("포인트 : " + point);
        }


        btn_nav_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DiaryCalendar.class);
                intent.putExtra("user_id",id);
                intent.putExtra("user_pw",pw);
                intent.putExtra("user_name",name);
                intent.putExtra("user_category",category);
                intent.putExtra("user_age",age);
                intent.putExtra("user_gender",gender);
                intent.putExtra("user_tel",tel);
                intent.putExtra("user_point",point);
                startActivity(intent);
            }
        });

        btn_nav_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.remove(getApplicationContext(), "id");
                PreferenceManager.remove(getApplicationContext(), "pw");
                PreferenceManager.remove(getApplicationContext(), "name");
                PreferenceManager.remove(getApplicationContext(), "category");
                PreferenceManager.remove(getApplicationContext(), "age");
                PreferenceManager.remove(getApplicationContext(), "gender");
                PreferenceManager.remove(getApplicationContext(), "tel");
                PreferenceManager.remove(getApplicationContext(), "point");

                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                finish();
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}