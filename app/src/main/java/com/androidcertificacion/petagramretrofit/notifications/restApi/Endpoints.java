package com.androidcertificacion.petagramretrofit.notifications.restApi;

import com.androidcertificacion.petagramretrofit.notifications.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lhrat on 07/02/2017.
 */

public interface Endpoints {

    @FormUrlEncoded
    @POST(ConstantesRestApi.ENDPOINT)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String idDispositivo,
                                           @Field("id_usuario_instagram") String idUsuarioInstagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_POST_LIKE)
    Call<UsuarioResponse> registrarLike(@Field("id_dispositivo") String idDispositivo,
                                        @Field("id_media_instagram") String idMedia,
                                        @Field("id_usuario_instagram") String idUsuarioInstagram);

    @GET(ConstantesRestApi.URL_GET_LIKE)
    Call<UsuarioResponse> notificacionLike(@Path("idFirebase") String id,
                                           @Path("idDispositivo") String dispositivo,
                                           @Path("usuario") String nombreUsuario);

}
