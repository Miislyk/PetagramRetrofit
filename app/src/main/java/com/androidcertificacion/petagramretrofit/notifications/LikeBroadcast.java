package com.androidcertificacion.petagramretrofit.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.androidcertificacion.petagramretrofit.modelo.RestApi.EndPointApi;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.adapter.RestApiAdapter;
import com.androidcertificacion.petagramretrofit.modelo.RestApi.modelo.RelationshipResponse;
import com.androidcertificacion.petagramretrofit.pojo.Relationship;
import com.androidcertificacion.petagramretrofit.vista.layout.Inicio;
import com.androidcertificacion.petagramretrofit.vista.layout.verUsuario;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lhrat on 17/03/2017.
 */

public class LikeBroadcast extends BroadcastReceiver {
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        final String ACCION1 = "ver_mi_perfil";
        final String ACCION2 = "FOLLOW";
        final String ACCION3 = "UNFOLLOW";
        final String ACCION4 = "ver_usuario";
        String accion = intent.getAction();
        String userId = intent.getStringExtra("user-id");
        this.context = context;

        switch (accion) {

            case ACCION1:
                Intent perfil = new Intent(context, Inicio.class);
                perfil.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(perfil);
                break;
            case ACCION2:
                followUnfollowUser(userId, accion);
                break;
            case ACCION3:
                followUnfollowUser(userId, accion);
                break;
            case ACCION4:
                Intent activityUsuario = new Intent(context, verUsuario.class);
                activityUsuario.putExtra("user-id", userId);
                activityUsuario.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activityUsuario);
                Toast.makeText(context, "ver_usuario", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void followUnfollowUser(String userId, String accion) {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonRelationShip = restApiAdapter.obtieneGsonRelationshipDeserializador();
        EndPointApi endPointApi = restApiAdapter.establecerConexionApiInstagram(gsonRelationShip);

        Call<RelationshipResponse> relationshipResponseCall = endPointApi.setRelationship(userId, accion);
        relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
            @Override
            public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                RelationshipResponse relationshipResponse = response.body();
                ArrayList<Relationship> relationships = relationshipResponse.getRelationships();
                Toast.makeText(context, "Follow/Unfollow" + relationships.get(0).getOutgoing_status(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RelationshipResponse> call, Throwable t) {

                Toast.makeText(context, "Error en la conexion. Intenta de nuevo", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
