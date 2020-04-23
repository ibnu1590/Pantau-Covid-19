package com.example.pantaucovid19;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static String url = "https://api.kawalcorona.com/";
    public static JsonPlaceHolderApi jsonPlaceHolderApi(){
        Retrofit r = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = r.create(JsonPlaceHolderApi.class);
        return jsonPlaceHolderApi;
    }

}
