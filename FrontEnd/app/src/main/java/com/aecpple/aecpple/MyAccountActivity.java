package com.aecpple.aecpple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAccountActivity extends AppCompatActivity {

    private String USER_ID;

    private TextView ET_USER_ID;
    private TextView ET_USERNAME;
    private TextView ET_USERBIRTH;
    private TextView ET_HANDICAP;
    private Button BTN_BACK;

    private String RES_USER_ID;
    private String RES_USERNAME;
    private String RES_USERBIRTH;
    private int RES_HANDICAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent id = getIntent();
        USER_ID = id.getStringExtra("ID");

        setContentView(R.layout.activity_my_account);

        ET_USER_ID = findViewById(R.id.tv_userid);
        ET_USERNAME = findViewById(R.id.tv_username);
        ET_USERBIRTH = findViewById(R.id.tv_userbirth);
        ET_HANDICAP = findViewById(R.id.tv_handicap);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/myInfo/") // Node.js 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        MyInfo myInfo = new MyInfo(USER_ID);

        Call<ResponseMyInfo> call = apiService.myInfo(myInfo);

        call.enqueue(new Callback<ResponseMyInfo>() {
            @Override
            public void onResponse(Call<ResponseMyInfo> call, Response<ResponseMyInfo> response) {
                Log.d("성공:", response.toString());

                RES_USER_ID = response.body().getUSER_ID();
                RES_USERNAME = response.body().getUSERNAME();
                RES_USERBIRTH = response.body().getUSERBIRTH();
                RES_HANDICAP = response.body().getHANDICAP();


                ET_USER_ID.setText(RES_USER_ID);
                ET_USERNAME.setText(RES_USERNAME);
                ET_USERBIRTH.setText(RES_USERBIRTH);
                if(RES_HANDICAP == 0) {
                    ET_HANDICAP.setText("비장애");
                } else {
                    ET_HANDICAP.setText("장애");
                }

                if (response.isSuccessful()) {
                    Log.d("Retrofit", "Data sent successfully!: ");

                } else {
                    Log.d("Retrofit", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseMyInfo> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage());
            }
        });

        BTN_BACK = findViewById(R.id.btn_goback);

        BTN_BACK.setOnClickListener(
                view -> {
                    finish();
                }
        );
    }
}
