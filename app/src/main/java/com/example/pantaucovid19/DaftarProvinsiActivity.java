package com.example.pantaucovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.pantaucovid19.Adapter.ProvinsiAdapter;
import com.example.pantaucovid19.Model.ModelObjectProvinsi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarProvinsiActivity extends AppCompatActivity {
    RecyclerView recyclerDaftarProvinsi;
    ImageView imgBack;
    ProgressDialog dialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_provinsi);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarProvinsiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerDaftarProvinsi = findViewById(R.id.recyclerDaftarProv);
        recyclerDaftarProvinsi.setLayoutManager(new LinearLayoutManager(this));

        getDaftar();
    }

    public void getDaftar() {
        dialog.show();
        Call<List<ModelObjectProvinsi>> call = Api.jsonPlaceHolderApi().getProv();
        call.enqueue(new Callback<List<ModelObjectProvinsi>>() {
            @Override
            public void onResponse(Call<List<ModelObjectProvinsi>> call, Response<List<ModelObjectProvinsi>> response) {
                recyclerDaftarProvinsi.setAdapter(new ProvinsiAdapter(DaftarProvinsiActivity.this, (ArrayList<ModelObjectProvinsi>) response.body()));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelObjectProvinsi>> call, Throwable t) {
                Toast.makeText(DaftarProvinsiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }

}
