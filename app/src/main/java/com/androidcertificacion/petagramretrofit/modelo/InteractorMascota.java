package com.androidcertificacion.petagramretrofit.modelo;

import android.content.ContentValues;
import android.content.Context;

import com.androidcertificacion.petagramretrofit.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by lhrat on 20/12/2016.
 */

public class InteractorMascota {

    private Context context;
    private int like = 0;

    public InteractorMascota (Context context){ this.context = context;}

    public void darLike(Mascota mascota) {

        BaseDatos baseDatos = new BaseDatos(context);
        if ( baseDatos.verificarRegistro(mascota)) {
            baseDatos.actualizarLikes(mascota);
        }else {
            insertarLike(mascota);
        }

        baseDatos.close();

    }

    public void guardarUsuario(String user){

        BaseDatos baseDatos = new BaseDatos(context);
        if (baseDatos.verificarUsuario()) {
            baseDatos.actualizarUsuario(user);
        }else {
            insertarUsuario(user);
        }

        baseDatos.close();

    }

    private void insertarUsuario(String user) {

        BaseDatos baseDatos = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.CAMPO_USER_NOMBRE, user);

        baseDatos.insertarUsuario(contentValues);
        baseDatos.close();

    }

    private void insertarLike(Mascota mascota) {

        BaseDatos baseDatos = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.CAMPO_MASCOTAS_ID, mascota.getId());
        contentValues.put(ConstantesBaseDatos.CAMPO_MASCOTAS_NOMBRE, mascota.getNombre());
        contentValues.put(ConstantesBaseDatos.CAMPO_MASCOTAS_FOTO, mascota.getFoto());
        contentValues.put(ConstantesBaseDatos.CAMPO_MASCOTAS_LIKES, like + 1);

        baseDatos.insertarLikes(contentValues);
        baseDatos.insertarUltimoLike(mascota);

        baseDatos.close();

    }

    public int obtenerLikes(Mascota mascota) {

        BaseDatos baseDatos = new BaseDatos(context);
        baseDatos.close();
        return baseDatos.obtenerLikes(mascota);


    }

    public ArrayList<Mascota> obtenerMascotasFavoritas () {

        ArrayList<Mascota> mascotasFavoritas = new ArrayList<>();
        BaseDatos baseDatos = new BaseDatos(context);

        mascotasFavoritas = baseDatos.obtenerMascotas();

        baseDatos.close();

        return mascotasFavoritas;

    }

}
