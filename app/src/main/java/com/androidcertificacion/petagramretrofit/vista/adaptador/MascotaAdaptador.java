package com.androidcertificacion.petagramretrofit.vista.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.InteractorMascota;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by lhrat on 20/12/2016.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {


    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {

        this.mascotas = mascotas;
        this.activity = activity;

    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolderholder, int position) {

        final Mascota mascota = mascotas.get(position);
        mascotaViewHolderholder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolderholder.textViewNombre.setText(mascota.getNombre());
        mascotaViewHolderholder.textViewContador.setText(String.valueOf(mascota.getLikes()) + " " + activity.getString(R.string.cardview_texto_likes));
        mascotaViewHolderholder.imageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();

                InteractorMascota interactorMascota = new InteractorMascota(activity);
                interactorMascota.darLike(mascota);

                mascotaViewHolderholder.textViewContador.setText(interactorMascota.obtenerLikes(mascota) + " " + "Likes");

            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView textViewNombre;
        private ImageButton imageButtonLike;
        private TextView textViewContador;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.cardviewImage);
            textViewNombre = (TextView) itemView.findViewById(R.id.cardview_nombreMascota);
            imageButtonLike = (ImageButton) itemView.findViewById(R.id.cardview_ButtonLike);
            textViewContador = (TextView) itemView.findViewById(R.id.cardview_contadorLikes);

        }
    }
}
