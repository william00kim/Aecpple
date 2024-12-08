package com.aecpple.aecpple;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FitnessInFoList extends AppCompatActivity {

    private ArrayList<FitnessInfodata> arrayList;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FusedLocationProviderClient fusedLocationClient2;

    private double latitude;
    private double longitude;

    public boolean Handistatus;
    public int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Intent name = getIntent();
        String ID = name.getStringExtra("ID");
        int HandiNum = name.getIntExtra("HandiNum", a);

        if(HandiNum == 0) {
            Handistatus = false;
        } else if(HandiNum == 1) {
            Handistatus = true;
        }

        Log.e("핸디켑 상태 리스트1" , "" + Handistatus);

        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission( this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    100);

        }


        fusedLocationClient2 = LocationServices.getFusedLocationProviderClient(this);

        setContentView(R.layout.activity_fitnessinfolist);

        recyclerView = findViewById(R.id.name);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();
        fusedLocationClient2.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {


            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-3-38-63-26.ap-northeast-2.compute.amazonaws.com:3050/nearby-facilities/") // Node.js 서버 주소
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);


                Log.e("핸디켑 상태 리스트2" , "" + Handistatus);

                FindFacilities fitnessInFo = new FindFacilities(latitude, longitude, Handistatus);

                Call<ResponceFacilities> call = apiService.nearby_facilities(fitnessInFo);

                call.enqueue(new Callback<ResponceFacilities>() {
                    @Override
                    public void onResponse(Call<ResponceFacilities> call, Response<ResponceFacilities> response) {
                        Log.d("성공:" , response.toString());
                        if (response.isSuccessful()) {

                            for(DataFacilites facility : response.body().getData()) {
                                Log.w("lat", "lat" + facility.getLATITUDE());
                                Log.w("lon", "lon" + facility.getLONGITUDE());
                                // 전화번호가 null이거나 비어 있는지 확인
                                String telNo = (facility.getTEL_NO() == null || facility.getTEL_NO().isEmpty()) ? "정보없음" : facility.getTEL_NO();
                                String facilityName = (facility.getFCLTY_NM() == null || facility.getFCLTY_NM().isEmpty()) ? "이름 없음" : facility.getFCLTY_NM();
                                String address = (facility.getFCLTY_ADDR() == null || facility.getFCLTY_ADDR().isEmpty()) ? "주소 정보 없음" : facility.getFCLTY_ADDR();
                                String itemName = (facility.getITEM_NM() == null || facility.getITEM_NM().isEmpty()) ? "항목 없음" : facility.getITEM_NM();
                                arrayList.add(new FitnessInfodata(R.drawable.home, facilityName, telNo, address, itemName));

                            }

                            Log.w("arrayList", "arrayList" + arrayList);
                            Log.w("arrayList 사이즈", "arrayList 사이즈: " + arrayList.size());

                            myAdapter = new MyAdapter(arrayList);

                            recyclerView.setAdapter(myAdapter);
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

        });



        Log.w("위도", "latitude: " + latitude);
        Log.w("경도", "longitude: " + longitude);


        BottomNavigationView navView = findViewById(R.id.bottomNav);

        navView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.Menu_Home) {
                Intent intent = new Intent(this, FitnessInFo.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
                finish();
                return true;
            } else if(item.getItemId() == R.id.Menu_UserInfo) {
                Intent intent = new Intent(this, MyAccountActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
                return true;
            } else if(item.getItemId() == R.id.Menu_List) {
                return true;
            }
            return false;
        });

    }
}