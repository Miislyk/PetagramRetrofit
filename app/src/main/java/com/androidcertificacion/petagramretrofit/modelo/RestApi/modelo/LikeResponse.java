package com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo;

/**
 * Created by lhrat on 15/02/2017.
 */

public class LikeResponse {

    private Meta meta;

    public boolean isSuccessfull() {
        return meta.code.equals(200);
    }

    private static class Meta {

        private Integer code;

    }

}
