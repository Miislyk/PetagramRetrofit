package com.androidcertificacion.petagramretrofit.modelo.RestApi;

/**
 * Created by lhrat on 21/01/2017.
 */

public class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4236031574.65e3ca8.d107fd91aa80413eae1b577af82f6c46";
    public static final String TEXT_ACCESS_TOKEN_SEARCH = "&access_token=";
    public static final String TEXT_USERS_SEARCH = "users/search";
    public static final String TEXT_ACCESS_TOKEN_USERS = "?access_token=";
    public static final String URL_MEDIA_RECENT_USERS = "users/{usuario}/media/recent/";
    public static final String TEXT_USERS_MEDIA_RECENT = "";

    //https://api.instagram.com/v1/users/search?q=user&access_token=ACCESS_TOKEN
    //https://api.instagram.com/v1/users/idUser/media/recent/?access_token=ACCESS_TOKEN

}
