package com.androidcertificacion.petagramretrofit.modelo.RestApi.deserializador;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.JsonKeys;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.RelationshipResponse;
import com.androidcertificacion.petagramretrofit.pojo.Relationship;
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
 * Created by lhrat on 17/03/2017.
 */

public class RelationshipDeserializador implements JsonDeserializer<RelationshipResponse> {


    @Override
    public RelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        RelationshipResponse relationshipResponse = gson.fromJson(json, RelationshipResponse.class);
        JsonArray relationshipResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY);
        relationshipResponse.setRelationships(deserializarRespuestaRelationship(relationshipResponseData));

        return relationshipResponse;
    }

    private ArrayList<Relationship> deserializarRespuestaRelationship(JsonArray relationshipResponseData){

        ArrayList<Relationship> relationships = new ArrayList<>();

        JsonObject responseDataObject = relationshipResponseData.get(0).getAsJsonObject();
        String statusSalida = responseDataObject.get(JsonKeys.RELATIONSHIP_OUTGOING_STATUS).getAsString();

        Relationship relationship = new Relationship();
        relationship.setOutgoing_status(statusSalida);

        relationships.add(relationship);

        return relationships;

    }
}
