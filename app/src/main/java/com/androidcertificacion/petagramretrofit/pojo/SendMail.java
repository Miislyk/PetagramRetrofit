package com.androidcertificacion.petagramretrofit.pojo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by lhrat on 20/12/2016.
 */

public class SendMail extends AsyncTask<Void, Void, Void>{

    //Declaring Variables
    private Context context;
    private Session session;

    //Information to send email
    private String email;
    private String subject;
    private String message;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    public SendMail(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Sending message","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Email Enviado",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties properties = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        //creando session
        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            //autenticar password
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(MailConfig.EMAIL, MailConfig.PASS);
            }
        });

        try {
            //Creating MimeMessage object
            MimeMessage mimeMessage = new MimeMessage(session);

            //Setting sender address
            mimeMessage.setFrom(new InternetAddress(MailConfig.EMAIL));
            //Adding receiver
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mimeMessage.setSubject(subject);
            //Adding message
            mimeMessage.setText(message);

            //Sending email
            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
