package com.androidcertificacion.petagramretrofit.vista.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.pojo.Mascota;
import com.androidcertificacion.petagramretrofit.presentador.IPresentador;
import com.androidcertificacion.petagramretrofit.presentador.IPresenterVerUsuario;
import com.androidcertificacion.petagramretrofit.presentador.IVerUsuario;
import com.androidcertificacion.petagramretrofit.presentador.VerUsuarioPresentador;
import com.androidcertificacion.petagramretrofit.vista.adaptador.UsuarioAdaptador;

import java.util.ArrayList;

public class verUsuario extends AppCompatActivity implements IVerUsuario {

    ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IPresenterVerUsuario presentador;
    String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);
        rvMascotas = (RecyclerView) findViewById(R.id.rvVerUsuario);
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        if (actionbar!=null){
            setSupportActionBar(actionbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Bundle b = getIntent().getExtras();

        if (b!=null){
            user = b.getString("user-id");
            Log.i("varuserid",""+user);
        }
        presentador = new VerUsuarioPresentador(this, this, user);
    }

    @Override
    public void generarLinearLayout() {

    }

    @Override
    public void generarGridLayout() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMascotas.setLayoutManager(gridLayoutManager);

    }

    @Override
    public UsuarioAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {

        UsuarioAdaptador adaptador = new UsuarioAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(UsuarioAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}
