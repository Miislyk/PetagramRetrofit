package com.androidcertificacion.petagramretrofit.modelo.RestApi;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lhrat on 21/01/2017.
 */

public interface EndPointApi {

    @GET(ConstantesRestApi.TEXT_USERS_SEARCH /*+ "{user}"
            + ConstantesRestApi.TEXT_ACCESS_TOKEN_SEARCH + ConstantesRestApi.ACCESS_TOKEN*/)
    Call<MascotaResponse> getUsersId(@Query("q") String query,
                                     @Query("access_token") String accessToken);

    @GET(ConstantesRestApi.URL_MEDIA_RECENT_USERS)
    Call<MascotaResponse> getUsersRecentMedia(@Path("usuario") String userId,
                                              @Query("access_token") String accessToken);

}
