package com.aecpple.aecpple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoundAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_found_account);

        Button findidButton= (Button) findViewById(R.id.btn_findid);
        findidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(FoundAccountActivity.this,FindIDActivity.class);
                startActivity(intent);
            }
        });

        Button findpwButton= (Button) findViewById(R.id.btn_findpw);
        findpwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(FoundAccountActivity.this,FindPWActivity.class);
                startActivity(intent);
            }
        });
    }
}