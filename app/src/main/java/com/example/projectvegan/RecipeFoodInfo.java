package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.projectvegan.DTO.SNSDTO;

import java.util.ArrayList;

public class RecipeFoodInfo extends AppCompatActivity {
    private TextView recipe_title,recipe_rc,recipe_ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_food_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("레시피 정보");

        actionBar.setDisplayHomeAsUpEnabled(true);

        recipe_title = findViewById(R.id.recipe_title);
        recipe_rc = findViewById(R.id.recipe_rc);
        recipe_ingredient = findViewById(R.id.recipe_ingredient);

        Intent intent = getIntent();

        recipe_title.setText(intent.getStringExtra("rName"));
        recipe_rc.setText(intent.getStringExtra("rCon"));
        recipe_ingredient.setText(intent.getStringExtra("rIn"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}