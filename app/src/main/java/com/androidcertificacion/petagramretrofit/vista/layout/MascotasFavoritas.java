package com.androidcertificacion.petagramretrofit.vista.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.InteractorMascota;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.presentador.IPresentador;
import com.androidcertificacion.petagramretrofit.presentador.MascotasFavoritasPresentador;
import com.androidcertificacion.petagramretrofit.vista.IFragments;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaAdaptador;
import com.androidcertificacion.petagramretrofit.vista.adaptador.MascotaTimelineAdaptador;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IFragments {

    ArrayList<Mascota> mascotas;
    private RecyclerView mascotasFavoritas;
    private IPresentador presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);

        mascotasFavoritas = (RecyclerView) findViewById(R.id.mascotasfavoritas);
        presentador = new MascotasFavoritasPresentador(this, this);



    }


    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {

        MascotaAdaptador mascotaAdaptador = new MascotaAdaptador(mascotas, this);
        return mascotaAdaptador;
    }

    @Override
    public MascotaTimelineAdaptador crearAdaptadorTimeLine(ArrayList<Mascota> mascotas) {
        return null;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador adaptador) {

        mascotasFavoritas.setAdapter(adaptador);

    }

    @Override
    public void inicializarAdaptadorTimeline(MascotaTimelineAdaptador adaptador) {

    }

    @Override
    public void generarLayout() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mascotasFavoritas.setLayoutManager(linearLayoutManager);


    }
}

