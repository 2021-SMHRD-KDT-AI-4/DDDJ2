package com.example.projectvegan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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


        TextView tv_nav_login = (TextView) nav_header_view.findViewById(R.id.tv_nav_login);
        tv_nav_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        Button btn_nav_logout = (Button)nav_header_view.findViewById(R.id.btn_nav_logout);
        Button btn_nav_mypage = (Button)nav_header_view.findViewById(R.id.btn_nav_mypage);

        TextView tv_nav_subtitle = (TextView)nav_header_view.findViewById(R.id.tv_nav_subtitle);
        TextView tv_poing = (TextView)nav_header_view.findViewById(R.id.tv_point);
        ImageView img_tree = (ImageView)nav_header_view.findViewById(R.id.img_tree);

        String id = PreferenceManager.getString(getApplicationContext(), "id");

        if(id.equals(PreferenceManager.DEFAULT_STRING)){
            btn_nav_logout.setVisibility(View.INVISIBLE);
            btn_nav_mypage.setVisibility(View.INVISIBLE);
            tv_poing.setVisibility(View.INVISIBLE);
            img_tree.setVisibility(View.INVISIBLE);
        }else{
            btn_nav_logout.setVisibility(View.VISIBLE);
            btn_nav_mypage.setVisibility(View.VISIBLE);
            tv_poing.setVisibility(View.VISIBLE);
            img_tree.setVisibility(View.VISIBLE);
            tv_nav_login.setText(PreferenceManager.getString(getApplicationContext(), "name"));
            tv_nav_subtitle.setText(PreferenceManager.getString(getApplicationContext(), "id"));
        }


        btn_nav_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DiaryCalendar.class);
                startActivity(intent);
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