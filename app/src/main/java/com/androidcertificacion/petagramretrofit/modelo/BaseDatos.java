package com.androidcertificacion.petagramretrofit.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidcertificacion.petagramretrofit.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by lhrat on 20/12/2016.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTabla = "CREATE TABLE " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS
                + "(" + ConstantesBaseDatos.CAMPO_MASCOTAS_ID + " TEXT PRIMARY KEY, "
                + ConstantesBaseDatos.CAMPO_MASCOTAS_NOMBRE + " TEXT NOT NULL, "
                + ConstantesBaseDatos.CAMPO_MASCOTAS_FOTO + " INTEGER, "
                + ConstantesBaseDatos.CAMPO_MASCOTAS_LIKES + " INTEGER)";

        String queryCrearTablaUl = "CREATE TABLE " + ConstantesBaseDatos.TABLA_ULTIMOS_LIKES
                + "(" + ConstantesBaseDatos.CAMPO_UL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + ConstantesBaseDatos.CAMPO_UL_IDMASCOTA + " TEXT NOT NULL, "
                + ConstantesBaseDatos.CAMPO_MASCOTAS_NOMBRE + " TEXT NOT NULL) ";

        String queryCrearTablaUser = "CREATE TABLE " + ConstantesBaseDatos.TABLA_USER
                + "(" + ConstantesBaseDatos.CAMPO_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + ConstantesBaseDatos.CAMPO_USER_NOMBRE + " TEXT NOT NULL)";

        db.execSQL(queryCrearTabla);
        db.execSQL(queryCrearTablaUl);
        db.execSQL(queryCrearTablaUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLA_ULTIMOS_LIKES);
        onCreate(db);

    }

    public void actualizarLikes(Mascota mascota) {

        String query = "UPDATE " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS + " SET "
                + ConstantesBaseDatos.CAMPO_MASCOTAS_LIKES + " = " + ConstantesBaseDatos.CAMPO_MASCOTAS_LIKES
                + " + 1 WHERE " + ConstantesBaseDatos.CAMPO_MASCOTAS_ID + " = " + mascota.getId();

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);

        database.close();

        insertarUltimoLike(mascota);

    }

    public void actualizarUsuario(String user) {

        String query = "UPDATE " + ConstantesBaseDatos.TABLA_USER + " SET "
                + ConstantesBaseDatos.CAMPO_USER_NOMBRE + " = '" +  user
                + "' WHERE " + ConstantesBaseDatos.CAMPO_USER_ID + " = 1";

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);

        database.close();

    }

    public void insertarLikes(ContentValues contentValues) {

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS, null, contentValues);
        database.close();

    }

    public void insertarUsuario (ContentValues contentValues) {

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(ConstantesBaseDatos.TABLA_USER, null, contentValues);
        database.close();

    }

    public boolean verificarRegistro(Mascota mascota){

        String query = "SELECT " + ConstantesBaseDatos.CAMPO_MASCOTAS_ID +
                " FROM " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS +
                " WHERE " + ConstantesBaseDatos.CAMPO_MASCOTAS_ID + " = " + mascota.getId();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor registros = database.rawQuery(query, null);

        if (registros.moveToNext()) {
            return true;
        }else {
            return false;
        }

    }

    public boolean verificarUsuario() {

        String query = "SELECT " + ConstantesBaseDatos.CAMPO_USER_NOMBRE +
                " FROM " + ConstantesBaseDatos.TABLA_USER;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor registro = database.rawQuery(query, null);

        if (registro.moveToNext()) {
            return true;
        }else {
            return false;
        }

    }

    public String obtenerUsuario() {

        String user = "" ;

        String query = "SELECT " + ConstantesBaseDatos.CAMPO_USER_NOMBRE +
                " FROM " + ConstantesBaseDatos.TABLA_USER +
                " WHERE " + ConstantesBaseDatos.CAMPO_USER_ID + " = 1";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor  registros = database.rawQuery(query, null);

        if (registros.moveToNext()) {
            user = registros.getString(0);
        }

        return user;
    }



    public int obtenerLikes(Mascota mascota) {

        int likes = 0;

        String query = "SELECT " + ConstantesBaseDatos.CAMPO_MASCOTAS_LIKES +
                " FROM " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS +
                " WHERE " + ConstantesBaseDatos.CAMPO_MASCOTAS_ID + " = " + mascota.getId();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor registros = database.rawQuery(query, null);

        if (registros.moveToNext()) {
            likes = registros.getInt(0);
        }

        database.close();

        return likes;

    }

    public ArrayList<Mascota> obtenerMascotas() {

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT DISTINCT nombre FROM " + ConstantesBaseDatos.TABLA_ULTIMOS_LIKES
                + " ORDER BY " + ConstantesBaseDatos.CAMPO_UL_ID + " DESC LIMIT 5";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor registros = database.rawQuery(query, null);

        while (registros.moveToNext()) {

            Mascota mascotaActual = new Mascota();
            mascotaActual.setNombre(registros.getString(0));

            String queryUltimos = "SELECT * FROM " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS
                    + " WHERE " + ConstantesBaseDatos.CAMPO_MASCOTAS_NOMBRE + " = "
                    + "'" + mascotaActual.getNombre() + "'";

            Cursor mascotaFavorita = database.rawQuery(queryUltimos, null);

            if (mascotaFavorita.moveToNext()) {
                mascotaActual.setId(mascotaFavorita.getString(0));
                mascotaActual.setFoto(mascotaFavorita.getInt(2));
                mascotaActual.setLikes(mascotaFavorita.getInt(3));
            }else {
                mascotaActual.setId("");
                mascotaActual.setFoto(0);
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);

        }

        database.close();

        return mascotas;

    }

    public void insertarUltimoLike(Mascota mascota) {

        String query = "INSERT INTO " + ConstantesBaseDatos.TABLA_ULTIMOS_LIKES
                + "(" + ConstantesBaseDatos.CAMPO_UL_IDMASCOTA + ", " + ConstantesBaseDatos.CAMPO_UL_NOMBRE + ")"
                + " VALUES ('" + mascota.getId() + "', '" + mascota.getNombre() + "')";

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);

        database.close();

    }


}
