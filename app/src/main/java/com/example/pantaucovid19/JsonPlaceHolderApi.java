package com.example.pantaucovid19;

import com.example.pantaucovid19.Model.ModelIndonesia;
import com.example.pantaucovid19.Model.ModelObjectProvinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("indonesia")
    Call<List<ModelIndonesia>> getIndonesia();

    @GET("indonesia/provinsi")
    Call<List<ModelObjectProvinsi>> getProv();
}
