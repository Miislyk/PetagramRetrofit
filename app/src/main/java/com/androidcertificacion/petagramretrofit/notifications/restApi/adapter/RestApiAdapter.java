package com.androidcertificacion.petagramretrofit.notifications.restApi.adapter;

import com.androidcertificacion.petagramretrofit.notifications.restApi.ConstantesRestApi;
import com.androidcertificacion.petagramretrofit.notifications.restApi.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lhrat on 07/02/2017.
 */

public class RestApiAdapter {

    public Endpoints establecerConexionRestApi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Endpoints.class);


    }

}
