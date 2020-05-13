package com.example.covid19india;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Covid19API {

    @GET("data.json")
    Call<AllData> getAllData();
}
