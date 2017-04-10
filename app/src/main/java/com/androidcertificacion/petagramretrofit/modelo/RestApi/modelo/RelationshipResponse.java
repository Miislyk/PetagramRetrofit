package com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo;

import com.androidcertificacion.petagramretrofit.pojo.Relationship;

import java.util.ArrayList;

/**
 * Created by lhrat on 17/03/2017.
 */

public class RelationshipResponse {

    ArrayList<Relationship> relationships;

    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }
}
