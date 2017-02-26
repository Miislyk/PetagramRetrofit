package com.androidcertificacion.petagramretrofit.pojo;

/**
 * Created by lhrat on 20/12/2016.
 */

public class Mascota {

    private String id;
    private String fotoPerfil;
    private String nombre;
    private int likes;
    private int foto;
    private String urlFoto;
    private String idMedia;


    public Mascota(String id, int foto, String nombre) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
    }

    public Mascota(String id, String fotoPerfil, String nombre, int likes, int foto, String urlFoto) {
        this.id = id;
        this.fotoPerfil = fotoPerfil;
        this.nombre = nombre;
        this.likes = likes;
        this.foto = foto;
        this.urlFoto = urlFoto;
    }

    public String getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Mascota(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String foto) {
        this.fotoPerfil = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
