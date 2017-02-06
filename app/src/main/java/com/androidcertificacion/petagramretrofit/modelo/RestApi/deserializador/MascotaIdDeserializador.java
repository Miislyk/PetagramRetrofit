package com.androidcertificacion.petagramretrofit.modelo.RestApi.deserializador;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.JsonKeys;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.MascotaResponse;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by lhrat on 21/01/2017.
 */

public class MascotaIdDeserializador implements JsonDeserializer<MascotaResponse>{


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        MascotaResponse idMascota = gson.fromJson(json, MascotaResponse.class);

        JsonArray responseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY);

        idMascota.setMascotas(deserializarIdMascota(responseData));

        return idMascota;

    }

    private ArrayList<Mascota> deserializarIdMascota(JsonArray responseData) {

        ArrayList<Mascota> mascotaPerfil = new ArrayList<>();

        JsonObject responseDataObject = responseData.get(0).getAsJsonObject();
        String mascotaIdJson = responseDataObject.get(JsonKeys.USER_ID).getAsString();

        Mascota mascotaUser = new Mascota();
        mascotaUser.setId(mascotaIdJson);

        mascotaPerfil.add(mascotaUser);

        return mascotaPerfil;

    }

}
