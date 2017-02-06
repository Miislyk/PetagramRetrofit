package com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo;

import com.androidcertificacion.petagramretrofit.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by lhrat on 21/01/2017.
 */

public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
