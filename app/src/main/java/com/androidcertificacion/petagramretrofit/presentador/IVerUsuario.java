package com.androidcertificacion.petagramretrofit.presentador;

import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.vista.adaptador.UsuarioAdaptador;

import java.util.ArrayList;

/**
 * Created by lhrat on 07/04/2017.
 */

public interface IVerUsuario {

    public void generarLinearLayout();

    public void generarGridLayout();

    public UsuarioAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptador(UsuarioAdaptador adaptador);


}
