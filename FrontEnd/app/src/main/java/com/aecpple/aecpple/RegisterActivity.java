package com.aecpple.aecpple;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {

    EditText joinID;
    private EditText dateText;
    private DatePickerDialog dPD;
    private View overlap;
    private EditText editTextUsername, editTextPassword, editTextName, editTextBirthDate;
    private Button buttonRegister, buttonCheckId;
    private EditText etInputBirth;
    private RadioGroup radioGroup;
    private RadioButton selectedButton;

    private boolean isIdChecked = false;// 아이디 중복 확인 여부



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        // 레이아웃 요소 초기화
        editTextUsername = findViewById(R.id.et_inputid);
        editTextPassword = findViewById(R.id.et_inputpw);
        editTextName = findViewById(R.id.et_inputname);
        editTextBirthDate = findViewById(R.id.et_inputbirth);
        buttonRegister = findViewById(R.id.btn_regfinish);
        radioGroup = findViewById(R.id.rg_hadicap);
        buttonCheckId = findViewById(R.id.btn_check);



        // 아이디 중복 확인 버튼
        buttonCheckId.setOnClickListener(view -> checkIdAvailability());


        // 회원가입 버튼
        buttonRegister.setOnClickListener(view -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String name = editTextName.getText().toString();
            String birthDate = editTextBirthDate.getText().toString();

            Toast.makeText(this, birthDate, Toast.LENGTH_SHORT).show();
            boolean handicapStatus;


            // RadioButton 선택 값 가져오기
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "장애 여부를 선택해주세요.", Toast.LENGTH_SHORT).show();
            }
            selectedButton = findViewById(selectedId);
            String handicapString = selectedButton.getText().toString();
            if(handicapString == "장애") {
                handicapStatus = true;
            } else {
                handicapStatus = false;
            }


            // 아이디 중복확인
            if (!isIdChecked) {
                Toast.makeText(this, "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
            }


            //비밀번호 확인
            if (!validatePassword(password)) {
                Toast.makeText(this, "비밀번호는 영어, 숫자, 기호가 포함된 10자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
            }

            // 입력 필드 확인
            if (!username.isEmpty() && !password.isEmpty() && !name.isEmpty() && !birthDate.isEmpty()) {
                sendRegistrationDataToServer(username, password, name, birthDate, handicapStatus);
            } else {
                Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            }


        });


        //생년월일 입력칸 달력 띄우기
        etInputBirth = findViewById(R.id.et_inputbirth);
        //달력 클릭 이벤트 설정(맨 아래)
        etInputBirth.setOnClickListener(view -> showDatePickerDialog());
    }



    private void sendRegistrationDataToServer(String USER_ID, String PASSWORD, String USERNAME, String USERBIRTH, boolean handicapStatus) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/addUser/") // Node.js 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        User USERPARSE = new User(USER_ID, PASSWORD, USERNAME, USERBIRTH, handicapStatus);

        Call<ResponseBody> call = apiService.addUser(USERPARSE);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("성공:" , response.toString());
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "Data sent successfully!");
                } else {
                    Log.d("Retrofit", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage());
            }
        });
    }



    //아이디 중복체크 데이터 전송
    private void checkIdAvailability() {
        String username = editTextUsername.getText().toString();


        if (username.isEmpty()) {
            Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(() -> {
            try {


                // 서버 URL (아이디 중복 확인 API)
                URL url = new URL("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/addUser"); // 실제 서버 URL로 변경
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                // JSON 데이터 생성
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", username);

                // JSON 데이터 전송
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonObject.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // 서버 응답 처리
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    runOnUiThread(() -> {
                        Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        isIdChecked = true; // 중복 확인 성공
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "이미 사용 중인 아이디입니다.", Toast.LENGTH_SHORT).show());
                }
                connection.disconnect();
            } catch (Exception e) {
                Log.e("RegisterActivity", "Error:", e);
                runOnUiThread(() -> Toast.makeText(this, "오류 발생: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }).start();
    }




    //비밀번호 조건 체크
    private boolean validatePassword(String password) {
        // 비밀번호 조건: 영어, 숫자, 기호 포함 + 10자 이상
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$";
        return password.matches(passwordPattern);
    }





    //회원가입 버튼 누른후 데이터 전송
//    private void sendRegistrationDataToServer(String username, String password, String name, String birthDate, String handicapStatus) {
//        new Thread(() -> {
//            try {
//                // 서버 URL (예: "https://your-server.com/api/register")
//                URL url = new URL("https://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:80/createUser/register");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json; utf-8");
//                connection.setRequestProperty("Accept", "application/json");
//                connection.setDoOutput(true);
//
//                // JSON 데이터 생성
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("username", username);
//                jsonObject.put("password", password);
//                jsonObject.put("name", name);
//                jsonObject.put("birthDate", birthDate);
//                jsonObject.put("handicapStatus", handicapStatus);
//
//                // JSON 데이터 전송
//                try (OutputStream os = connection.getOutputStream()) {
//                    byte[] input = jsonObject.toString().getBytes("utf-8");
//                    os.write(input, 0, input.length);
//                }
//
//                // 서버 응답 확인
//                int responseCode = connection.getResponseCode();
//                String responseMessage = connection.getResponseMessage();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    runOnUiThread(() -> Toast.makeText(this, "회원 가입 성공", Toast.LENGTH_LONG).show());
//                } else {
//                    runOnUiThread(() -> Toast.makeText(this, "회원 가입 실패: " + responseCode + " - " + responseMessage, Toast.LENGTH_LONG).show());
//                }
//
//                connection.disconnect();
//            } catch (Exception e) {
//                Log.e("RegisterActivity", "Error:", e);
//                runOnUiThread(() -> Toast.makeText(this, "오류 발생: " + e.getMessage(), Toast.LENGTH_LONG).show());
//            }
//
//        }).start();
//    }
//





    //달력 클릭 이벤트
    private void showDatePickerDialog() {

        // 현재 날짜 가져오기
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // DatePickerDialog 생성
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.CustomDatePickerTheme, // 커스텀 테마
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    selectedMonth++; // 월은 0부터 시작하므로 +1 필요
                    // 날짜 포맷 지정
                    String formattedDate = String.format(Locale.getDefault(), "%04d/%02d/%02d", selectedYear, selectedMonth, selectedDay);
                    etInputBirth.setText(formattedDate);
                },
                year, month, day
        );

        Button finishButton= (Button) findViewById(R.id.btn_regfinish);
//        finishButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        // 최소 날짜 설정 (예: 1900년 1월 1일)
        Calendar minDate = Calendar.getInstance();
        minDate.set(1900, 0, 1);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        // 최대 날짜 설정 (오늘 날짜)
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        // 다이얼로그 표시
        datePickerDialog.show();
    }
}