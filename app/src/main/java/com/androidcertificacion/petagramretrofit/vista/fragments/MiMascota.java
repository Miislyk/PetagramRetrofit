package com.androidcertificacion.petagramretrofit.vista.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.BaseDatos;
import com.androidcertificacion.petagramretrofit.modelo.InteractorMascota;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.presentador.IPresentador;
import com.androidcertificacion.petagramretrofit.presentador.PerfilMascotasPresentador;
import com.androidcertificacion.petagramretrofit.vista.IFragments;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaAdaptador;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaTimelineAdaptador;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MiMascota extends Fragment implements IFragments {

    ArrayList<Mascota> miMascota;
    private RecyclerView fotosMiMascota;
    private IPresentador presentador;
    private PerfilMascotasPresentador datosPerfil;
    String user;
    private TextView textViewNombre;
    private CircularImageView fotoPerfil;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mi_mascota, container, false);

        fotosMiMascota = (RecyclerView) view.findViewById(R.id.mimascota);

        fotoPerfil = (CircularImageView) view.findViewById(R.id.circularImagePerfil);
        textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);

        BaseDatos consultaUsuario = new BaseDatos(getContext());

        if (consultaUsuario.verificarUsuario()) {
            user = consultaUsuario.obtenerUsuario();
        }else {
            user = "kiracatblackwhite";
            InteractorMascota insertarPrimerUsuario = new InteractorMascota(getContext());
            insertarPrimerUsuario.guardarUsuario(user);
            //getActivity().findViewById(R.id.textInputConfigurarCuenta).toString();
        }

        consultaUsuario.close();

        fotoPerfil.setImageResource(R.drawable.importance);
        textViewNombre.setText("---");

        presentador = new PerfilMascotasPresentador( this , getContext(), user );
        return view;

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        return null;
    }

    @Override
    public MascotaTimelineAdaptador crearAdaptadorTimeLine(ArrayList<Mascota> mascotas) {

        MascotaTimelineAdaptador mascotaTimelineAdaptador = new MascotaTimelineAdaptador(mascotas, getActivity());

        String nombre = mascotas.get(0).getNombre().toString();
        String urlFotoPerfil = mascotas.get(0).getFotoPerfil();

        Picasso.with(getContext())
                .load(urlFotoPerfil)
                .placeholder(R.drawable.importance)
                .into(fotoPerfil);

        textViewNombre.setText(nombre);

        return mascotaTimelineAdaptador;

    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador adaptador) {

    }

    @Override
    public void inicializarAdaptadorTimeline(MascotaTimelineAdaptador adaptador) {
        fotosMiMascota.setAdapter(adaptador);
    }

    @Override
    public void generarLayout() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        fotosMiMascota.setLayoutManager(gridLayoutManager);

    }

}
