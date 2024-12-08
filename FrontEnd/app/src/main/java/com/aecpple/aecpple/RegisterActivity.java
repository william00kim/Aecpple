//package com.aecpple.aecpple;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.json.JSONObject;
//
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Calendar;
//import java.util.Locale;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private EditText dateText;
//    private DatePickerDialog dPD;
//    private View overlap;
//    private EditText editTextUsername, editTextPassword, editTextName, editTextBirthDate;
//    private Button buttonRegister, buttonCheckId;
//    private EditText etInputBirth;
//    private RadioGroup radioGroup;
//    private RadioButton selectedButton;
//
//    private boolean isIdChecked = false;// 아이디 중복 확인 여부
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        // 레이아웃 요소 초기화
//        editTextUsername = findViewById(R.id.et_inputid);
//        editTextPassword = findViewById(R.id.et_inputpw);
//        editTextName = findViewById(R.id.et_inputname);
//        editTextBirthDate = findViewById(R.id.et_inputbirth);
//        buttonRegister = findViewById(R.id.btn_regfinish);
//        radioGroup = findViewById(R.id.rg_hadicap);
//        buttonCheckId = findViewById(R.id.btn_check);
//
//        radioGroup.setOnCheckedChangeListener((group, check) -> {
//            selectedButton = findViewById(check);
//        });
//
//        // 아이디 중복 확인 버튼
//        buttonCheckId.setOnClickListener(view -> checkIdAvailability());
//
//
//        // 회원가입 버튼
//        buttonRegister.setOnClickListener(view -> handleRegistration());
//
//        //생년월일 입력칸 달력 띄우기
//        etInputBirth = findViewById(R.id.et_inputbirth);
//        //달력 클릭 이벤트 설정(맨 아래)
//        etInputBirth.setOnClickListener(view -> showDatePickerDialog());
//    }
//
//
//    private void handleRegistration() {
//        String userid = editTextUsername.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//        String name = editTextName.getText().toString().trim();
//        String birthDate = editTextBirthDate.getText().toString().trim();
//
//        if (userid.isEmpty() || password.isEmpty() || name.isEmpty() || birthDate.isEmpty()) {
//            Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (!isIdChecked) {
//            Toast.makeText(this, "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (!validatePassword(password)) {
//            Toast.makeText(this, "비밀번호는 영어, 숫자, 기호가 포함된 10자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        boolean handicapStatus = false; // 기본값
//        if (selectedButton != null) {
//            String handicapString = selectedButton.getText().toString();
//            handicapStatus = "장애".equals(handicapString);
//        } else {
//            Toast.makeText(this, "장애 여부를 선택해주세요.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        sendRegistrationDataToServer(userid, password, name, birthDate, handicapStatus);
//    }
//
//
//    private void sendRegistrationDataToServer(String USER_ID, String PASSWORD, String USERNAME, String USERBIRTH, boolean handicapStatus) {
//
//    }
//
//
//
//    //아이디 중복체크 데이터 전송
//    private void checkIdAvailability() {
//        String username = editTextUsername.getText().toString();
//
//
//        if (username.isEmpty()) {
//            Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/checkId/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            ApiService apiService = retrofit.create(ApiService.class);
//
//            CheckId checkId = new CheckId(username);
//
//            Call<ResponseCheckId> call = apiService.checkId(checkId);
//
//            call.enqueue(new Callback<ResponseCheckId>() {
//                @Override
//                public void onResponse(Call<ResponseCheckId> call, Response<ResponseCheckId> response) {
//                    Log.d("성공:", "" + response.body().getMessage());
//                    int data = response.body().getMessage();
//                    if (data == 0) {
//                        Toast.makeText(RegisterActivity.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
//                    } else if (data == 1){
//                        Toast.makeText(RegisterActivity.this, "사용가능합니다.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseCheckId> call, Throwable t) {
//                    Log.e("Retrofit", "Error: " + t.getMessage());
//                }
//            });
//        }
//    }
//
//
//
//    //비밀번호 조건 체크
//    private boolean validatePassword(String password) {
//        // 비밀번호 조건: 영어, 숫자, 기호 포함 + 10자 이상
//        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$";
//        return password.matches(passwordPattern);
//    }
//
//
//    //달력 클릭 이벤트
//    private void showDatePickerDialog() {
//
//        // 현재 날짜 가져오기
//        final Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        // DatePickerDialog 생성
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                R.style.CustomDatePickerTheme, // 커스텀 테마
//                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
//                    selectedMonth++; // 월은 0부터 시작하므로 +1 필요
//                    // 날짜 포맷 지정
//                    String formattedDate = String.format(Locale.getDefault(), "%04d/%02d/%02d", selectedYear, selectedMonth, selectedDay);
//                    etInputBirth.setText(formattedDate);
//                },
//                year, month, day
//        );
//
//        Button finishButton= (Button) findViewById(R.id.btn_regfinish);
////        finishButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
////                startActivity(intent);
////            }
////        });
//
//        // 최소 날짜 설정 (예: 1900년 1월 1일)
//        Calendar minDate = Calendar.getInstance();
//        minDate.set(1900, 0, 1);
//        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
//        // 최대 날짜 설정 (오늘 날짜)
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//        // 다이얼로그 표시
//        datePickerDialog.show();
//    }
//}

package com.aecpple.aecpple;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextName, editTextBirthDate;
    private Button buttonRegister, buttonCheckId;
    private RadioGroup radioGroup;
    private RadioButton selectedButton;

    private boolean isIdChecked = false; // 아이디 중복 확인 여부

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
        buttonCheckId = findViewById(R.id.btn_check);
        radioGroup = findViewById(R.id.rg_hadicap);

        // 라디오 버튼 리스너 설정
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            selectedButton = findViewById(checkedId);
        });

        // 아이디 중복 확인 버튼
        buttonCheckId.setOnClickListener(view -> checkIdAvailability());

        // 회원가입 버튼
        buttonRegister.setOnClickListener(view -> handleRegistration());

        // 생년월일 입력창 클릭 시 달력 표시
        editTextBirthDate.setOnClickListener(view -> showDatePickerDialog());
    }

    // 회원가입 처리
    private void handleRegistration() {
        String userid = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String birthDate = editTextBirthDate.getText().toString().trim();

        if (userid.isEmpty() || password.isEmpty() || name.isEmpty() || birthDate.isEmpty()) {
            Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isIdChecked) {
            Toast.makeText(this, "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validatePassword(password)) {
            Toast.makeText(this, "비밀번호는 영어, 숫자, 기호가 포함된 10자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean handicapStatus = false; // 기본값
        if (selectedButton != null) {
            String handicapString = selectedButton.getText().toString();
            handicapStatus = "장애".equals(handicapString);
        } else {
            Toast.makeText(this, "장애 여부를 선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        sendRegistrationDataToServer(userid, password, name, birthDate, handicapStatus);
    }

    // 서버에 회원가입 데이터 전송
    private void sendRegistrationDataToServer(String USER_ID, String PASSWORD, String USERNAME, String USERBIRTH, boolean handicapStatus) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/addUser/") // Node.js 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        User USERPARSE = new User(USER_ID, PASSWORD, USERNAME, USERBIRTH, handicapStatus);

        Call<ResponseRsgister> call = apiService.addUser(USERPARSE);

        call.enqueue(new Callback<ResponseRsgister>() {
            @Override
            public void onResponse(Call<ResponseRsgister> call, Response<ResponseRsgister> response) {
                Log.d("성공:" , response.toString());
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "Data sent successfully!");
                    Intent intent = new Intent(RegisterActivity.this, FitnessInFo.class);
                    intent.putExtra("ID", USER_ID);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("Retrofit", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseRsgister> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage());
            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/addUser/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//        User user = new User(USER_ID, PASSWORD, USERNAME, USERBIRTH, handicapStatus);
//
//        Call<ResponseBody> call = apiService.addUser(user);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(RegisterActivity.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(RegisterActivity.this, "회원가입 실패: " + response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("Retrofit", "Error: " + t.getMessage());
//                Toast.makeText(RegisterActivity.this, "서버와 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    // 아이디 중복 확인
    private void checkIdAvailability() {
        String username = editTextUsername.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/checkId/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        CheckId checkId = new CheckId(username);

        Call<ResponseCheckId> call = apiService.checkId(checkId);
        call.enqueue(new Callback<ResponseCheckId>() {
            @Override
            public void onResponse(Call<ResponseCheckId> call, Response<ResponseCheckId> response) {
                if (response.body() != null && response.body().getMessage() == 1) {
                    isIdChecked = true;
                    Toast.makeText(RegisterActivity.this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckId> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "서버와 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 비밀번호 유효성 검사
    private boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$";
        return password.matches(passwordPattern);
    }

    // 생년월일 선택
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    selectedMonth++; // 월은 0부터 시작
                    String formattedDate = String.format(Locale.getDefault(), "%04d/%02d/%02d", selectedYear, selectedMonth, selectedDay);
                    editTextBirthDate.setText(formattedDate);
                },
                year, month, day
        );

        Calendar minDate = Calendar.getInstance();
        minDate.set(1900, 0, 1);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
