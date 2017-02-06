package com.androidcertificacion.petagramretrofit.vista.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.vista.IFragments;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaAdaptador;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaTimelineAdaptador;

import java.util.ArrayList;

public class mascotas extends Fragment implements IFragments {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public mascotas() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // infla el layout para este fragment

        View view = inflater.inflate(R.layout.fragment_mascotas, container, false);

        listaMascotas = (RecyclerView) view.findViewById(R.id.mascotas);

        //generarLinearLayout();
        inicializarListaMascotas();
        inicializarAdaptador(crearAdaptador(mascotas));
        generarLayout();

        return view;
    }


    public void inicializarListaMascotas() {

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("1", R.drawable.fox, "Fox"));
        mascotas.add(new Mascota("2", R.drawable.elephant, "Dumbo"));
        mascotas.add(new Mascota("3", R.drawable.monkey, "Mono"));
        mascotas.add(new Mascota("4", R.drawable.sheep, "Sleepy"));
        mascotas.add(new Mascota("5", R.drawable.bear, "Baloo"));
        mascotas.add(new Mascota("6", R.drawable.pinguino, "Skipper"));

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public MascotaTimelineAdaptador crearAdaptadorTimeLine(ArrayList<Mascota> mascotas) {
        return null;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador adaptador) {

        listaMascotas.setAdapter(adaptador);

    }

    @Override
    public void inicializarAdaptadorTimeline(MascotaTimelineAdaptador adaptador) {

    }

    @Override
    public void generarLayout() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(linearLayoutManager);

    }
}
