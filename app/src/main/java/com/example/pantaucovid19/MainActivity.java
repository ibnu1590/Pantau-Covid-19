package com.example.pantaucovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pantaucovid19.Adapter.ProvinsiAdapter;
import com.example.pantaucovid19.Model.ModelIndonesia;
import com.example.pantaucovid19.Model.ModelObjectProvinsi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvSembuh, tvPositif, tvMeninggal, tvLihatSemua;
    ProgressDialog dialog;
    Button btnRujukan, btnCall, btnPanduan;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerProvinsi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvSembuh = findViewById(R.id.txtSembuh);
        tvPositif = findViewById(R.id.txtPositif);
        tvMeninggal = findViewById(R.id.txtMeninggal);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();

        tvLihatSemua = findViewById(R.id.txtLihatSemua);
        tvLihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaftarProvinsiActivity.class);
                startActivity(intent);
            }
        });

        btnPanduan = findViewById(R.id.btn_panduan);
        btnPanduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri web = Uri.parse("https://tirto.id/cara-cuci-tangan-dengan-7-langkah-menurut-who-untuk-cegah-corona-eLyQ");
                Intent intent = new Intent(Intent.ACTION_VIEW, web);
                startActivity(intent);
            }
        });

        btnRujukan = findViewById(R.id.btn_rujukan);
        btnRujukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri web = Uri.parse("https://www.kompas.com/tren/read/2020/03/03/183500265/infografik-daftar-100-rumah-sakit-rujukan-penanganan-virus-corona");
                Intent intent = new Intent(Intent.ACTION_VIEW, web);
                startActivity(intent);
            }
        });

        btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri phone = Uri.parse("tel:" + 117);
                Intent intent = new Intent(Intent.ACTION_DIAL, phone);
                startActivity(intent);
            }
        });

        getIndonesia();
        getProvinsi();


    }

    public void getIndonesia() {
        Call<List<ModelIndonesia>> call = Api.jsonPlaceHolderApi().getIndonesia();
        call.enqueue(new Callback<List<ModelIndonesia>>() {
            @Override
            public void onResponse(Call<List<ModelIndonesia>> call, Response<List<ModelIndonesia>> response) {
                tvSembuh.setText(response.body().get(0).getSembuh());
                tvPositif.setText(response.body().get(0).getPositif());
                tvMeninggal.setText(response.body().get(0).getMeninggal());

            }

            @Override
            public void onFailure(Call<List<ModelIndonesia>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getProvinsi() {
        dialog.show();
        Call<List<ModelObjectProvinsi>> call = Api.jsonPlaceHolderApi().getProv();
        call.enqueue(new Callback<List<ModelObjectProvinsi>>() {
            @Override
            public void onResponse(Call<List<ModelObjectProvinsi>> call, Response<List<ModelObjectProvinsi>> response) {
                recyclerView.setAdapter(new ProvinsiAdapter(MainActivity.this, (ArrayList<ModelObjectProvinsi>) response.body()));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelObjectProvinsi>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}
