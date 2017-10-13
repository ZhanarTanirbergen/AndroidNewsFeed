package com.example.zhanara.newsfeed.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("/v1/articles?source=cnn&sortBy=top")
    Call<Example> getPosts(@Query("apiKey") String apiKey);

}
