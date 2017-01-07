package com.example.yen.ru.web.service;

import com.example.yen.ru.data.response.RandomUserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RUService {

    @GET("api/")
    Call<RandomUserResponse> getResults(@Query("results") String results,
                                        @Query("page") String page,
                                        @Query("gender") String gender);

}