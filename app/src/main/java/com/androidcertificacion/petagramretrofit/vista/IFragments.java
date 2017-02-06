package com.androidcertificacion.petagramretrofit.vista;

import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaAdaptador;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaTimelineAdaptador;

import java.util.ArrayList;

/**
 * Created by lhrat on 20/12/2016.
 */

public interface IFragments {

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public MascotaTimelineAdaptador crearAdaptadorTimeLine(ArrayList<Mascota> mascotas);

    public void inicializarAdaptador(MascotaAdaptador adaptador);

    public void inicializarAdaptadorTimeline(MascotaTimelineAdaptador adaptador);

    public void generarLayout();


}
