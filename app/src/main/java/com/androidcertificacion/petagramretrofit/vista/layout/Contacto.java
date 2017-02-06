package com.androidcertificacion.petagramretrofit.vista.layout;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.pojo.SendMail;

public class Contacto extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textInputEditTextNombre;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextMensaje;
    private Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        textInputEditTextNombre = (TextInputEditText) findViewById(R.id.contactoTextInputNombre);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.contactoTextInputCorreo);
        textInputEditTextMensaje = (TextInputEditText) findViewById(R.id.contactoTextInputMensaje);
        buttonEnviar = (Button) findViewById(R.id.contactoButtonEnviar);

        buttonEnviar.setOnClickListener(this);


        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
    }

    @Override
    public void onClick(View v) {
        sendMail();
    }

    private void sendMail() {

        //Getting content for email
        String email = textInputEditTextEmail.getText().toString().trim();
        String subject = "PETAGRAM Inc";
        String message = "Estimado " + textInputEditTextNombre.getText().toString().trim() + ":\n" +  textInputEditTextMensaje.getText().toString().trim();

        //Creating SendMail object
        SendMail sendMail = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sendMail.execute();

    }
}
