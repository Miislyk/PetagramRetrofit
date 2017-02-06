package com.androidcertificacion.petagramretrofit.vista.layout;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.InteractorMascota;
import com.androidcertificacion.petagramretrofit.vista.fragments.MiMascota;

public class ConfigurarCuenta extends AppCompatActivity {

    private TextInputEditText textInputEditTextUser;
    private Button buttonConfigurar;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        textInputEditTextUser = (TextInputEditText) findViewById(R.id.textInputConfigurarCuenta);
        buttonConfigurar = (Button) findViewById(R.id.btnConfigurarCuenta);

        final InteractorMascota interactorMascota = new InteractorMascota(this);

        buttonConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = textInputEditTextUser.getText().toString();

                if (user == "" ) {
                    return;
                }

                interactorMascota.guardarUsuario(user);
                Toast.makeText(ConfigurarCuenta.this, "Se ha actualizado el usuario", Toast.LENGTH_LONG).show();
                textInputEditTextUser.setText("");

                /*Fragment fragment =  getSupportFragmentManager().findFragmentByTag("mimascota_fragment_tag");
                final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(fragment);
                fragmentTransaction.attach(fragment);
                fragmentTransaction.commit();*/

                Intent intent = new Intent(ConfigurarCuenta.this, Inicio.class);
                ConfigurarCuenta.this.startActivity(intent);

                finish();

            }
        });


    }

}
