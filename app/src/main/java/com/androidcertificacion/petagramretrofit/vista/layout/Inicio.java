package com.androidcertificacion.petagramretrofit.vista.layout;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.BaseDatos;
import com.androidcertificacion.petagramretrofit.notifications.restApi.Endpoints;
import com.androidcertificacion.petagramretrofit.notifications.restApi.adapter.RestApiAdapter;
import com.androidcertificacion.petagramretrofit.notifications.restApi.model.UsuarioResponse;
import com.androidcertificacion.petagramretrofit.vista.adaptador.PageAdapter;
import com.androidcertificacion.petagramretrofit.vista.fragments.MiMascota;
import com.androidcertificacion.petagramretrofit.vista.fragments.mascotas;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inicio extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        setUpViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionview, menu);
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuMascotasFavoritas:
                Intent favoritas = new Intent(this, MascotasFavoritas.class);
                startActivity(favoritas);
                break;
            case R.id.menuContacto:
                Intent contacto = new Intent(this, Contacto.class);
                startActivity(contacto);
                break;
            case R.id.menuAbout:
                Intent about = new Intent(this, About.class);
                startActivity(about);
                break;
            case R.id.menuConfigurar:
                Intent configurar = new Intent(this, ConfigurarCuenta.class);
                startActivity(configurar);
                break;
            case R.id.menuNotificaciones:
                enviarDatosRegistro();
                break;

        }

        return super.onOptionsItemSelected(item);

    }

    private void enviarDatosRegistro() {

        String token = FirebaseInstanceId.getInstance().getToken();
        BaseDatos id = new BaseDatos(this);
        String idInstagram = id.obtenerIdInstagram();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> response = endpoints.registrarUsuario(token, idInstagram);

        response.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID", usuarioResponse.getId());//ID de firebase que se genera en el servidor
                Log.d("TOKENFIREBASE", usuarioResponse.getIdDispositivo());
                Log.d("USUARIOINSTAGRAM", usuarioResponse.getIdUsuarioInstagram());

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(Inicio.this, "ALGO SALIO MAL!!", Toast.LENGTH_LONG).show();
                Log.e("ERROR", "Fallo en la conexión!!!!!!");
            }
        });



    }

    private void setUpViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.cat);

    }

    private ArrayList<Fragment> agregarFragments() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new mascotas());
        fragments.add(new MiMascota());

        return fragments;

    }
}
