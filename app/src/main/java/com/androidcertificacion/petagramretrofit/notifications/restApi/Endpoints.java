package com.androidcertificacion.petagramretrofit.notifications.restApi;

import com.androidcertificacion.petagramretrofit.notifications.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lhrat on 07/02/2017.
 */

public interface Endpoints {

    @FormUrlEncoded
    @POST(ConstantesRestApi.ENDPOINT)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String idDispositivo,
                                           @Field("id_usuario_instagram") String idUsuarioInstagram);

}
