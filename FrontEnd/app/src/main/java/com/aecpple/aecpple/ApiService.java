package com.aecpple.aecpple;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ApiService {
    @POST("/addUser")
    Call<ResponseRsgister> addUser(@Body User user);

    @POST("/login")
    Call<ResponseData> login(@Body Login login);

    @POST("/nearby-facilities")
    Call<ResponceFacilities> nearby_facilities(@Body FindFacilities findFacilities);

    @POST("/findHandi")
    Call<ResponseHandi> findHandi(@Body FindHandi findHandi);

    @POST("/checkId")
    Call<ResponseCheckId> checkId(@Body CheckId checkId);

    @POST("/myInfo")
    Call<ResponseMyInfo> myInfo(@Body MyInfo myInfo);
}
