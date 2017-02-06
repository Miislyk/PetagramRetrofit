package com.androidcertificacion.petagramretrofit.vista.layout;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.vista.adaptador.PageAdapter;
import com.androidcertificacion.petagramretrofit.vista.fragments.MiMascota;
import com.androidcertificacion.petagramretrofit.vista.fragments.mascotas;

import java.util.ArrayList;

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

        }

        return super.onOptionsItemSelected(item);

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
