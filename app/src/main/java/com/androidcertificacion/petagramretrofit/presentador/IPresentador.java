package com.androidcertificacion.petagramretrofit.presentador;

import com.androidcertificacion.petagramretrofit.pojo.Mascota;

/**
 * Created by lhrat on 20/12/2016.
 */

public interface IPresentador {

    public void obtenerMascotasFav();

    public void mostrarContactos();

    public void obtenerMediosRecientes(String id);

    public void obtenerIdUser();

}
