package com.androidcertificacion.petagramretrofit.vista.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.BaseDatos;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.ConstantesRestApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.EndPointApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.adapter.RestApiAdapter;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.LikeResponse;
import com.androidcertificacion.petagramretrofit.notifications.restApi.Endpoints;
import com.androidcertificacion.petagramretrofit.notifications.restApi.model.UsuarioResponse;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        mascotaTimelineViewHolder.imageButtonCvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeInstagram(mascota.getIdMedia());
                registrarLikeFirebase(mascota.getIdMedia());

            }
        });
          mascotaTimelineViewHolder.textViewLikesCvt.setText(String.valueOf(mascota.getLikes()));

    }

    public void likeInstagram (String id){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointApi endpoints = restApiAdapter.establecerConexionLike();
        Call<LikeResponse> responseCall = endpoints.postLike(id, ConstantesRestApi.ACCESS_TOKEN);
        responseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                Toast.makeText(activity, "LIKE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Toast.makeText(activity, "Fallo al dar like", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void registrarLikeFirebase(String idMediaInstagram) {

        Log.d("INSERCIONFIREBASE", "True");
        String idDispositivo = //"fJOSOvvD0xs:APA91bHNLswlZ0SfqhPiedAo_76OXCn6mAvItzxG8gsaWO4DHMv5vm2cW7YbJgyVEUoCD43eB83RU7b4z3llN1vaA2ANnrjc9C2uxADlkHi1noinTzG9Pj5eCibFAm6rqEREa23GbYwg";
                FirebaseInstanceId.getInstance().getId();
        BaseDatos id = new BaseDatos(activity);
        String idUsuarioInstagram = id.obtenerIdInstagram();
        UsuarioResponse usuarioResponse = new UsuarioResponse(idDispositivo, idMediaInstagram, idUsuarioInstagram);

        com.androidcertificacion.petagramretrofit.notifications.restApi.adapter.RestApiAdapter restApiAdapter = new com.androidcertificacion.petagramretrofit.notifications.restApi.adapter.RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionRestApi();

        Call<UsuarioResponse> responseCall = endpoints.registrarLike(usuarioResponse.getIdDispositivo(), usuarioResponse.getIdMedia(), usuarioResponse.getIdUsuarioInstagram());
        responseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse insercionLikeFirebase = response.body();
                Toast.makeText(activity, "Insercion exitosa", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(activity, "Error en la insercion Firebase", Toast.LENGTH_LONG).show();
            }
        });

        String idFirebase = "";
        UsuarioResponse usuarioNotificacion = new UsuarioResponse(idFirebase ,idDispositivo, idMediaInstagram, idUsuarioInstagram);

        Call<UsuarioResponse> responseNotificacion = endpoints.notificacionLike(usuarioNotificacion.getId(), usuarioNotificacion.getIdDispositivo(), usuarioNotificacion.getIdUsuarioInstagram());
        responseNotificacion.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse respuestaNotificacion = response.body();
                Log.d("ID_FIREBASE", respuestaNotificacion.getId());
                Log.d("TOKEN_FIREBASE", respuestaNotificacion.getIdDispositivo());
                Log.d("ANIMAL_FIREBASE", respuestaNotificacion.getIdUsuarioInstagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.e("NF", "Fallo en Notificar!");
            }
        });


    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaTimelineViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewFotoCvt;
        private ImageButton imageButtonCvt;
        private TextView textViewLikesCvt;

        public MascotaTimelineViewHolder(View itemView) {
            super(itemView);
            imageViewFotoCvt = (ImageView) itemView.findViewById(R.id.cvtImage);
            imageButtonCvt = (ImageButton) itemView.findViewById(R.id.cvtButtonLike);
            textViewLikesCvt = (TextView) itemView.findViewById(R.id.cvtTvLikes);
        }
    }

}
