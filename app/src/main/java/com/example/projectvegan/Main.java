package com.example.projectvegan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.projectvegan.databinding.NavHeaderMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectvegan.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class Main extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View nav_header_view = navigationView.getHeaderView(0);

        TextView tv_nav_login = (TextView) nav_header_view.findViewById(R.id.tv_nav_login);
        tv_nav_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        Button btn_nav_logout = (Button)nav_header_view.findViewById(R.id.btn_nav_logout);
        TextView tv_nav_subtitle = (TextView)nav_header_view.findViewById(R.id.tv_nav_subtitle);
        String info =PreferenceManager.getString(getApplicationContext(),"info");
        try {
            JSONObject jsonObject = new JSONObject(info);
            if(info == null){
                btn_nav_logout.setVisibility(View.INVISIBLE);
            }else{
                btn_nav_logout.setVisibility(View.VISIBLE);
                tv_nav_login.setText(jsonObject.getString("name"));
                tv_nav_subtitle.setText(jsonObject.getString("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button btn_nav_mypage = (Button)nav_header_view.findViewById(R.id.btn_nav_mypage);
        btn_nav_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyPage.class);
                startActivity(intent);
            }
        });


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
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