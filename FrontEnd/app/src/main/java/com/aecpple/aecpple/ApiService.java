package com.aecpple.aecpple;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/addUser")
    Call<ResponseBody> addUser(@Body User user);

    @POST("/login")
    Call<ResponseData> login(@Body Login login);
}
