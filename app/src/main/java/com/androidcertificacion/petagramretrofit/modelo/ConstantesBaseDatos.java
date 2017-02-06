package com.androidcertificacion.petagramretrofit.modelo;

/**
 * Created by lhrat on 20/12/2016.
 */

public class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLA_MASCOTAS_FAVORITAS = "mascotas_favoritas";
    public static final String CAMPO_MASCOTAS_ID = "id";
    public static final String CAMPO_MASCOTAS_NOMBRE = "nombre";
    public static final String CAMPO_MASCOTAS_FOTO = "foto";
    public static final String CAMPO_MASCOTAS_LIKES = "likes";

    public static final String TABLA_ULTIMOS_LIKES = "ultimos_likes";
    public static final String CAMPO_UL_ID = "id";
    public static final String CAMPO_UL_IDMASCOTA = "id_mascota";
    public static final String CAMPO_UL_NOMBRE = "nombre";

    public static final String TABLA_USER = "usuario";
    public static final String CAMPO_USER_ID = "id";
    public static final String CAMPO_USER_NOMBRE = "nombre";

}
