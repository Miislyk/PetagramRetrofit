package com.androidcertificacion.petagramretrofit.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Log;
import android.view.Gravity;

import com.androidcertificacion.petagramretrofit.R;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.EndPointApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.adapter.RestApiAdapter;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.RelationshipResponse;
import com.androidcertificacion.petagramretrofit.pojo.Relationship;
import com.androidcertificacion.petagramretrofit.vista.fragments.MiMascota;
import com.androidcertificacion.petagramretrofit.vista.layout.Inicio;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lhrat on 06/02/2017.
 */

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int NOTIFICATION_ID = 001;
    private String actionRelationship = "";


    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getData().size() > 0) {

            Log.d(TAG, "Message data: " + remoteMessage.getData());

            //Consulta de relacion
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gsonRelationship = restApiAdapter.obtieneGsonRelationshipDeserializador();
            EndPointApi endPointApi = restApiAdapter.establecerConexionApiInstagram(gsonRelationship);

            Call<RelationshipResponse> relationshipResponseCall = endPointApi.getRelationship(remoteMessage.getData().get("user"));
            Log.i("Call",": " + relationshipResponseCall.request());

            relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
                @Override
                public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                    RelationshipResponse relationshipResponse = response.body();
                    ArrayList<Relationship> relationships = relationshipResponse.getRelationships();
                    Log.i("body", "responsebody: " + relationships.get(0).toString());
                    crearNotificacion(relationships.get(0), remoteMessage);
                }

                @Override
                public void onFailure(Call<RelationshipResponse> call, Throwable t) {
                    Log.i("getRelationshipToUser", "FALLO LA CONEXION: " + t.toString());
                }
            });

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    private void crearNotificacion(Relationship relationship, RemoteMessage remoteMessage){
        actionRelationship = (relationship.getOutgoing_status().equals("follows"))?"UNFOLLOW":"FOLLOW";


        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intentActionPerfil = new Intent();
        intentActionPerfil.setAction("ver_mi_perfil");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intentActionPerfil,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentFollowUnfollow = new Intent();
        intentFollowUnfollow.setAction(actionRelationship);
        intentFollowUnfollow.putExtra("user-id", remoteMessage.getData().get("user"));
        PendingIntent pendingIntentFollow = PendingIntent.getBroadcast(this,1,intentFollowUnfollow,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentVerUsuario = new Intent();
        intentVerUsuario.setAction("ver_usuario");
        intentVerUsuario.putExtra("user-id", remoteMessage.getData().get("user"));
        PendingIntent pendingIntentVer = PendingIntent.getBroadcast(this,2,intentVerUsuario,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.importance,getString(R.string.wear_texto_ver_perfil),pendingIntent).build();
        NotificationCompat.Action action1 = new NotificationCompat.Action.Builder(R.drawable.importance,actionRelationship,pendingIntentFollow).build();
        NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.drawable.importance,getString(R.string.wear_texto_ver_usuario),pendingIntentVer).build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.instagramold))
                .setGravity(Gravity.CENTER_VERTICAL);



        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.importance)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(action))
                .extend(wearableExtender.addAction(action1))
                .extend(wearableExtender.addAction(action2));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification.build());

    }
}
