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
 * Created by lhrat on 12/01/2017.
 */

public class MascotaTimelineAdaptador extends RecyclerView.Adapter<MascotaTimelineAdaptador.MascotaTimelineViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaTimelineAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaTimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_timeline, parent, false);
        return new MascotaTimelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MascotaTimelineViewHolder mascotaTimelineViewHolder, int position) {

        final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.importance)
                .into(mascotaTimelineViewHolder.imageViewFotoCvt);
        //mascotaTimelineViewHolder.textViewNombreCvt.setText(mascota.getNombre());
          mascotaTimelineViewHolder.textViewLikesCvt.setText(String.valueOf(mascota.getLikes()));

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaTimelineViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewFotoCvt;
        //private TextView textViewNombreCvt;
        private TextView textViewLikesCvt;

        public MascotaTimelineViewHolder(View itemView) {
            super(itemView);
            imageViewFotoCvt = (ImageView) itemView.findViewById(R.id.cvtImage);
            //textViewNombreCvt = (TextView) itemView.findViewById(R.id.cvtTvNombre);
            textViewLikesCvt = (TextView) itemView.findViewById(R.id.cvtTvLikes);
        }
    }

}
