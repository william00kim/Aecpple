package com.aecpple.aecpple;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FitnessInFo extends AppCompatActivity {

    MapView mapView;
    KakaoMap kakaoMap;

    private double latitude;
    private double longitude;

    private int HandiNum;
    public boolean Handistatus;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent name = getIntent();
        String ID = name.getStringExtra("ID");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/findHandi/") // Node.js 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        FindHandi findHandi = new FindHandi(ID);

        Call<ResponseHandi> call = apiService.findHandi(findHandi);

        call.enqueue(new Callback<ResponseHandi>() {

            @Override
            public void onResponse(Call<ResponseHandi> call, Response<ResponseHandi> response) {
                int Handi = response.body().getHandi();
                HandiNum = Handi;
                if(Handi == 0) {
                    Handistatus = false;
                }
                if(Handi == 1) {
                    Handistatus = true;
                }

                Log.w("핸디캡", "핸디켑 받아온거: " + response.body().getHandi());

                if(ActivityCompat.checkSelfPermission(FitnessInFo.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission( FitnessInFo.this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(FitnessInFo.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            100);

                }

                fusedLocationClient.getLastLocation().addOnCompleteListener(FitnessInFo.this, new OnCompleteListener<Location>() {

                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        sendFacilitesDataToServer(latitude, longitude, Handistatus);

                    }
                });

                Log.w("위도", "latitude: " + latitude);
                Log.w("경도", "longitude: " + longitude);

            }

            @Override
            public void onFailure(Call<ResponseHandi> call, Throwable t) {
                Log.e("핸디켑" , "실패");
            }
        });


        KakaoMapSdk.init(this, "a58dd3023cc21b45db6f002be2e5ab01");
        Log.e("넘어온 아이디", ID);

        Log.e("핸디켑 숫자", "" + HandiNum);
        Log.e("핸디켑 상태" ,"" + Handistatus);

        super.onCreate(savedInstanceState);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        setContentView(R.layout.activity_fitnessinfo);

        BottomNavigationView navView = findViewById(R.id.bottomNav);

        mapView = findViewById(R.id.map_view);

//        mapView.pause();


        navView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.Menu_Home) {
                return true;
            } else if(item.getItemId() == R.id.Menu_UserInfo) {
                Intent intent = new Intent(this, MyAccountActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
                return true;
            } else if(item.getItemId() == R.id.Menu_List) {

                Intent intent = new Intent(this, FitnessInFoList.class);
                intent.putExtra("Handistatus", HandiNum);
                intent.putExtra("ID", ID);
                startActivity(intent);
                return true;
            }
            return false;
        });


    }

    private void getMyHandicapStatus(String ID) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3030/findHandi/") // Node.js 서버 주소
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        FindHandi findHandi = new FindHandi(ID);
//
//        Call<ResponseHandi> call = apiService.findHandi(findHandi);
//
//        call.enqueue(new Callback<ResponseHandi>() {
//
//            @Override
//            public void onResponse(Call<ResponseHandi> call, Response<ResponseHandi> response) {
//                int Handi = response.body().getHandi();
//                if(Handi == 0) {
//                    Handistatus = true;
//                }
//                if(Handi == 1) {
//                    Handistatus = false;
//                }
//
//                Log.w("핸디캡", "핸디켑 받아온거: " + response.body().getHandi());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseHandi> call, Throwable t) {
//                Log.e("핸디켑" , "실패");
//            }
//        });

        Log.e("핸디켑 상태", "" + Handistatus);

        Log.e("핸디켑 상태", "" + Handistatus);

    }

    private void sendFacilitesDataToServer(double Lat, double Lng, boolean handicap) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3050/nearby-facilities/") // Node.js 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        FindFacilities fitnessInFo = new FindFacilities(Lat, Lng, handicap);

        Call<ResponceFacilities> call = apiService.nearby_facilities(fitnessInFo);

        call.enqueue(new Callback<ResponceFacilities>() {
            @Override
            public void onResponse(Call<ResponceFacilities> call, Response<ResponceFacilities> response) {
                Log.d("성공:" , response.toString());
                if (response.isSuccessful()) {

                    mapView.start(new MapLifeCycleCallback() {
                        @Override
                        public void onMapDestroy() {
                            // 지도 API가 정상적으로 종료될 때 호출
                            Log.d("KakaoMap", "onMapDestroy: ");
                        }


                        @Override
                        public void onMapError(Exception error) {
                            // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출
                            Log.e("KakaoMap", "onMapError: ", error);
                        }


                    }, new KakaoMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull KakaoMap map) {
                            // 정상적으로 인증이 완료되었을 때 호출
                            // KakaoMap 객체를 얻어 옵니다.

                            kakaoMap = map;

                            LabelStyles fitLatLng = kakaoMap.getLabelManager()
                                    .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.maps_and_flags)));

                            LabelStyles myLatLng = kakaoMap.getLabelManager()
                                    .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.maps_and_flags__2_)));

                            LabelOptions options = LabelOptions.from(LatLng.from(latitude,longitude))
                                    .setStyles(myLatLng);

                            List<DataFacilites> facilitesList = new ArrayList<>();

                            for(DataFacilites facility : response.body().getData()) {
                                Log.w("lat", "lat" + facility.getLATITUDE());
                                Log.w("lon", "lon" + facility.getLONGITUDE());
                                facilitesList.add(facility);
                                LabelOptions options2 = LabelOptions.from(LatLng.from(Double.parseDouble(facility.getLATITUDE()),Double.parseDouble(facility.getLONGITUDE())))
                                        .setStyles(fitLatLng);
                                kakaoMap.getLabelManager().getLayer().addLabel(options2);
                            }


                            kakaoMap.getLabelManager().getLayer().addLabel(options);

                        }

                        @Override
                        public LatLng getPosition() {
                            // 지도 시작 시 위치 좌표를 설정
                            Log.w("asdfgh", "latitude: " + latitude);
                            Log.w("asdfgh", "longitude: " + longitude);
                            return LatLng.from(latitude, longitude);
                        }

                    });

                } else {
                    Log.d("Retrofit", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponceFacilities> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage());
            }


        });
    }
}