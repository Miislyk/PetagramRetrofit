package com.androidcertificacion.petagramretrofit.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.modelo.BaseDatos;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.ConstantesRestApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.EndPointApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.adapter.RestApiAdapter;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.MascotaResponse;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.vista.IFragments;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lhrat on 22/01/2017.
 */

public class PerfilMascotasPresentador implements IPresentador {

    private IFragments iFragments;
    private Context context;
    private ArrayList<Mascota> mascotasMediosRecientes;
    private String user;
    RestApiAdapter restApiAdapter = new RestApiAdapter();


    public PerfilMascotasPresentador(IFragments iFragments, Context context, String user) {
        this.iFragments = iFragments;
        this.context = context;
        this.user = user;
        obtenerIdUser();
    }

    @Override
    public void obtenerMascotasFav() {

    }

    @Override
    public void mostrarContactos() {

        iFragments.inicializarAdaptadorTimeline(iFragments.crearAdaptadorTimeLine(mascotasMediosRecientes));
        iFragments.generarLayout();

    }

    @Override
    public void obtenerMediosRecientes(String id) {

        Gson gsonMediaRecent = restApiAdapter.obtieneGsonMediosRecientesDeserializador();
        EndPointApi endPointApiMediaRecent = restApiAdapter.establecerConexionApiInstagram(gsonMediaRecent);

        Call<MascotaResponse> mascotaMediaRecentResponseCall = endPointApiMediaRecent.getUsersRecentMedia(id, ConstantesRestApi.ACCESS_TOKEN);

        mascotaMediaRecentResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mascotaResponseMediaRecent = response.body();
                mascotasMediosRecientes = mascotaResponseMediaRecent.getMascotas();
                mostrarContactos();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(context, "No se cargaron los medios", Toast.LENGTH_LONG).show();
                Log.e("FALLO MEDIOS RECENT", t.toString());

            }
        });

    }

    @Override
    public void obtenerIdUser() {

        Gson gsonUserId = restApiAdapter.obtieneGsonIdDeserializador();
        EndPointApi endPointApi = restApiAdapter.establecerConexionApiInstagram(gsonUserId);

        Call<MascotaResponse> mascotaIdResponseCall = endPointApi.getUsersId(user, ConstantesRestApi.ACCESS_TOKEN);

        mascotaIdResponseCall.enqueue(new Callback<MascotaResponse>() {

            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mascotaResponse = response.body();
                ArrayList<Mascota> mascotaPerfil = mascotaResponse.getMascotas();
                BaseDatos guardaId = new BaseDatos(context);
                guardaId.actualizarIdInstagram(user, mascotaPerfil.get(0).getId().toString());
                obtenerMediosRecientes(mascotaPerfil.get(0).getId().toString());

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(context, "Algo salio Mal", Toast.LENGTH_LONG).show();
                Log.e("FALLO EN LA CONEXION", t.toString());

            }
        });

    }

}
