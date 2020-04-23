package com.example.pantaucovid19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantaucovid19.MainActivity;
import com.example.pantaucovid19.Model.ModelAttributes;
import com.example.pantaucovid19.Model.ModelObjectProvinsi;
import com.example.pantaucovid19.R;

import java.util.ArrayList;
import java.util.List;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ViewHolder> {

    Context context;
    ArrayList<ModelObjectProvinsi> modelObjectProvinsis;

    public ProvinsiAdapter(Context context, ArrayList<ModelObjectProvinsi> modelObjectProvinsis) {
        this.context = context;
        this.modelObjectProvinsis = modelObjectProvinsis;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provinsi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelAttributes attribute = modelObjectProvinsis.get(position).getAttributes();

        holder.tvProv.setText("Provinsi : " + attribute.getProvinsi());
        holder.tvPositif.setText("Kasus Positif : " + attribute.getKasus_Posi());
        holder.tvSembuh.setText("Kasus Sembuh : " + attribute.getKasus_Semb());
        holder.tvMeninggal.setText("Kasus Meninggal : " + attribute.getKasus_Meni());
    }

    @Override
    public int getItemCount() {
        return modelObjectProvinsis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProv, tvPositif, tvSembuh, tvMeninggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProv = itemView.findViewById(R.id.txtItemProvinsi);
            tvPositif = itemView.findViewById(R.id.txtItemPositif);
            tvSembuh = itemView.findViewById(R.id.txtItemSembuh);
            tvMeninggal = itemView.findViewById(R.id.txtItemMeninggal);
        }
    }
}
