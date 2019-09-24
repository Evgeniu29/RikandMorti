package com.paad.rikandmorti.Retrofit;

import com.paad.rikandmorti.API.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

        @GET("api/character/")

        Call<Feed> getData();
    }

