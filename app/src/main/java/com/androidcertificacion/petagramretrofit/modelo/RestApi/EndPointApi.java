package com.androidcertificacion.petagramretrofit.modelo.RestApi;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.LikeResponse;
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

    // https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN Get a list of users matching the query.
    @GET(ConstantesRestApi.TEXT_USERS_SEARCH /*+ "{user}"
            + ConstantesRestApi.TEXT_ACCESS_TOKEN_SEARCH + ConstantesRestApi.ACCESS_TOKEN*/)
    Call<MascotaResponse> getUsersId(@Query("q") String query,
                                     @Query("access_token") String accessToken);

    // https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN Get the most recent media published by a user.
    @GET(ConstantesRestApi.URL_MEDIA_RECENT_USERS)
    Call<MascotaResponse> getUsersRecentMedia(@Path("usuario") String userId,
                                              @Query("access_token") String accessToken);

    // https://api.instagram.com/v1/media/{media-id}/likes Set a like on this media by the currently authenticated user.
    @POST(ConstantesRestApi.URL_MEDIA_LIKESRESPONSE)
    Call<LikeResponse> postLike(@Path("media-id") String mediaId,
                                @Query("access_token") String accessToken);



}
