package com.aecpple.aecpple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private EditText USER_ID;
    private EditText PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        USER_ID = findViewById(R.id.et_id);
        PASSWORD = findViewById(R.id.et_pw);

        Button registerButton= (Button) findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button findaccountButton = (Button) findViewById(R.id.btn_findaccount);
        findaccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFindAccount= new Intent(MainActivity.this,FoundAccountActivity.class);
                startActivity(intentFindAccount);
            }
        });

        Button LoginButton = findViewById(R.id.login_btn);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = USER_ID.getText().toString();
                String pass = PASSWORD.getText().toString();
                SendLoginDataToServer(userid, pass);
            }
        });
    }

    private void SendLoginDataToServer(String USER_ID, String PASSWORD) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/login/") // Node.js 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Login loginparse = new Login(USER_ID, PASSWORD);

        Call<ResponseData> call = apiService.login(loginparse);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                Log.d("성공:" , response.toString());
                String data = response.body().getMessage();
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "Data sent successfully!: " + data);
                    if(data.equals("로그인 성공.") ) {
                        Intent intent= new Intent(MainActivity.this, FitnessInFo.class);
                        startActivity(intent);
                    } else if(data.equals("로그인 실패.")) {
                        Toast.makeText(MainActivity.this, "아이디, 비밀번호를 확인하세요", 3).show();
                    }
                } else {
                    Log.d("Retrofit", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage());
            }
        });
    }
}