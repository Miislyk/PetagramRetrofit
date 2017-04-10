package com.androidcertificacion.petagramretrofit.vista.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lhrat on 07/04/2017.
 */

public class UsuarioAdaptador extends RecyclerView.Adapter<UsuarioAdaptador.PerfilMascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public UsuarioAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usuario,parent,false);
        return new UsuarioAdaptador.PerfilMascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerfilMascotaViewHolder holder, int position) {

        final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.importance)
                .into(holder.imgUserFoto);
        holder.tvUserLikes.setText(mascota.getLikes());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUserFoto;
        private TextView tvUserLikes;


        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);
            imgUserFoto = (ImageView) itemView.findViewById(R.id.cvUserImage);
            tvUserLikes = (TextView) itemView.findViewById(R.id.cvUserTvLikes);
        }
    }


}
