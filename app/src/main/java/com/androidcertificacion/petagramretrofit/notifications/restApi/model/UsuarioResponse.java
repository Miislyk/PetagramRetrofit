package com.androidcertificacion.petagramretrofit.notifications.restApi.model;

/**
 * Created by lhrat on 07/02/2017.
 */

public class UsuarioResponse {

    private String id;
    private String idDispositivo;
    private String idUsuarioInstagram;
    private String idMedia;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String id, String idDispositivo, String idUsuarioInstagram) {
        this.id = id;
        this.idDispositivo = idDispositivo;
        this.idUsuarioInstagram = idUsuarioInstagram;
    }

    public UsuarioResponse(String id, String idDispositivo, String idUsuarioInstagram, String idMedia) {
        this.id = id;
        this.idDispositivo = idDispositivo;
        this.idUsuarioInstagram = idUsuarioInstagram;
        this.idMedia = idMedia;
    }

    public String getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getIdUsuarioInstagram() {
        return idUsuarioInstagram;
    }

    public void setIdUsuarioInstagram(String idUsuarioInstagram) {
        this.idUsuarioInstagram = idUsuarioInstagram;
    }
}
