package com.androidcertificacion.petagramretrofit.presentador;

import android.content.Context;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.ConstantesRestApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.EndPointApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.adapter.RestApiAdapter;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.MascotaResponse;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lhrat on 07/04/2017.
 */

public class VerUsuarioPresentador implements IPresenterVerUsuario {

    private IVerUsuario iVerUsuario;
    private Context context;
    private ArrayList<Mascota> mascotasFotos = new ArrayList<>();
    private String user="";

    public VerUsuarioPresentador(IVerUsuario iVerUsuario, Context context, String user) {
        this.iVerUsuario = iVerUsuario;
        this.context = context;
        this.user = user;
    }

    @Override
    public void obtenerMascotas() {

    }

    @Override
    public void obtenerMediosRecientes() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediosRecientes = restApiAdapter.obtieneGsonMediosRecientesDeserializador();
        EndPointApi endPointApi = restApiAdapter.establecerConexionApiInstagram(gsonMediosRecientes);

        Call<MascotaResponse> mascotaResponseCall = endPointApi.getUsersRecentMedia(user, ConstantesRestApi.ACCESS_TOKEN);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasFotos.addAll(mascotaResponse.getMascotas());
                mostrarMascotas();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void mostrarMascotas() {

        iVerUsuario.inicializarAdaptador(iVerUsuario.crearAdaptador(mascotasFotos));
        iVerUsuario.generarGridLayout();

    }
}
