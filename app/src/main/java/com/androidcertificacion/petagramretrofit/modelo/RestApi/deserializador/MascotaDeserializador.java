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
 * Created by lhrat on 22/01/2017.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        MascotaResponse mediaRecentResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mediaRecentResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY);

        mediaRecentResponse.setMascotas(deserializarMediosRecientes(mediaRecentResponseData));

        return mediaRecentResponse;
    }

    private ArrayList<Mascota> deserializarMediosRecientes(JsonArray mediosRecientesResponse) {

        ArrayList<Mascota> mascotasRecientes = new ArrayList<>();

        for (int i = 0; i < mediosRecientesResponse.size(); i++) {

            JsonObject mascotasUserResponseData = mediosRecientesResponse.get(i).getAsJsonObject();

            JsonObject userDataPerfil = mascotasUserResponseData.getAsJsonObject(JsonKeys.USER);
            String name = userDataPerfil.get(JsonKeys.USERNAME).getAsString();
            String fotoPerfil = userDataPerfil.get(JsonKeys.FOTO_PERFIL).getAsString();

            JsonObject imagesUserResponseData = mascotasUserResponseData.getAsJsonObject(JsonKeys.MEDIA_IMAGES_USERS);
            JsonObject standarResolution = imagesUserResponseData.getAsJsonObject(JsonKeys.MEDIA_IMAGES_STANDARD_RESOLUTION);
            String urlMedia = standarResolution.get(JsonKeys.MEDIA_IMAGES_URL).getAsString();

            JsonObject likesUserResponseData = mascotasUserResponseData.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesUserResponseData.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota mascotaMediosRecientes = new Mascota();
            mascotaMediosRecientes.setNombre(name);
            mascotaMediosRecientes.setFotoPerfil(fotoPerfil);
            mascotaMediosRecientes.setUrlFoto(urlMedia);
            mascotaMediosRecientes.setLikes(likes);

            mascotasRecientes.add(mascotaMediosRecientes);

        }

        return mascotasRecientes;

    }
}
