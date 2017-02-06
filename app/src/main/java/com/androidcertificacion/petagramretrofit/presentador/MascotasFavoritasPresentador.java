package com.androidcertificacion.petagramretrofit.presentador;

import android.content.Context;

import com.androidcertificacion.petagramretrofit.modelo.InteractorMascota;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.vista.IFragments;

import java.util.ArrayList;

/**
 * Created by lhrat on 22/01/2017.
 */

public class MascotasFavoritasPresentador implements IPresentador {

    private IFragments iFragments;
    private Context context;
    private InteractorMascota interactorMascota;
    private ArrayList<Mascota> mascotas;

    public MascotasFavoritasPresentador(Context context, IFragments iFragments) {
        this.context = context;
        this.iFragments = iFragments;
        obtenerMascotasFav();
    }

    @Override
    public void obtenerMascotasFav() {

        interactorMascota = new InteractorMascota(context);
        mascotas = interactorMascota.obtenerMascotasFavoritas();
        mostrarContactos();

    }

    @Override
    public void mostrarContactos() {

        iFragments.inicializarAdaptador(iFragments.crearAdaptador(mascotas));
        iFragments.generarLayout();

    }

    @Override
    public void obtenerMediosRecientes(String id) {

    }

    @Override
    public void obtenerIdUser() {

    }
}
