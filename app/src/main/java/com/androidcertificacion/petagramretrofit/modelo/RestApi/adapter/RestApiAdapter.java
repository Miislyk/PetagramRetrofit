package com.androidcertificacion.petagramretrofit.modelo.RestApi.adapter;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.ConstantesRestApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.EndPointApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.deserializador.MascotaDeserializador;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.deserializador.MascotaIdDeserializador;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.deserializador.RelationshipDeserializador;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.MascotaResponse;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.RelationshipResponse;
import com.androidcertificacion.petagramretrofit.pojo.Relationship;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lhrat on 21/01/2017.
 */

public class RestApiAdapter {

    public EndPointApi establecerConexionApiInstagram(Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPointApi.class);

    }

    public EndPointApi establecerConexionLike() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndPointApi.class);

    }

    public Gson obtieneGsonIdDeserializador() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaIdDeserializador());

        return gsonBuilder.create();

    }

    public Gson obtieneGsonMediosRecientesDeserializador() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();

    }

    public Gson obtieneGsonRelationshipDeserializador() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RelationshipResponse.class, new RelationshipDeserializador());

        return gsonBuilder.create();

    }



}
