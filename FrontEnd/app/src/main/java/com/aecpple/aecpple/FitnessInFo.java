package com.aecpple.aecpple;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FitnessInFo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fitnessinfo);

        BottomNavigationView navView = findViewById(R.id.bottomNav);

        navView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.Menu_Home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if(item.getItemId() == R.id.Menu_UserInfo) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if(item.getItemId() == R.id.Menu_List) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }
            return false;
        });

    }
}