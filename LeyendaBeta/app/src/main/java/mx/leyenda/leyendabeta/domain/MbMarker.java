package mx.leyenda.leyendabeta.domain;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Icon;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.views.MapView;

import mx.leyenda.leyendabeta.MapBoxView;

/**
 * Created by samo92 on 07/10/2015.
 */
public class MbMarker {
    //VARIABLES
    //private MapView myMapView;
    @SerializedName("id")
    private int idMarker;

    @SerializedName("titulo")
    private String titleMarker;

    @SerializedName("descripcion")
    private String descriptionMarker;

    @SerializedName("lon")                  //Coordenada signo negatico
    private double lonMarker;

    @SerializedName("lat")                  //Coordenada signo positivo
    private double langMarker;

    @SerializedName("genero")
    private int genreMarker;

    @SerializedName("imagen")
    private String imageMarker;

    @SerializedName("audio")
    private String recordMarker;

    //CONSTRUCTOR
    public MbMarker(int idMarker, String titleMarker, String descriptionMarker,
                    double lonMarker, double langMarker, int genreMarker, String imageMarker, String recordMarker) {
        this.idMarker = idMarker;
        this.titleMarker = titleMarker;
        this.descriptionMarker = descriptionMarker;
        this.lonMarker = lonMarker;
        this.langMarker = langMarker;
        this.genreMarker = genreMarker;
        this.imageMarker = imageMarker;
        this.recordMarker = recordMarker;
    }

    //METODOS
    public LatLng getLatLngMarker(double latitud, double longitud){
        return new LatLng(latitud,longitud);
    }

    //GETTERS
    public int getIdMarker() {
        return idMarker;
    }

    public String getTitleMarker() {
        return titleMarker;
    }

    public String getDescriptionMarker() {
        return descriptionMarker;
    }

    public double getLonMarker() {
        return lonMarker;
    }

    public double getLangMarker() {
        return langMarker;
    }

    public int getGenreMarker() {
        return genreMarker;
    }

    public String getImageMarker() {
        return imageMarker;
    }

    public String getRecordMarker() {
        return recordMarker;
    }

    //SETTERS

    public void setIdMarker(int idMarker) {
        this.idMarker = idMarker;
    }

    public void setTitleMarker(String titleMarker) {
        this.titleMarker = titleMarker;
    }

    public void setDescriptionMarker(String descriptionMarker) {
        this.descriptionMarker = descriptionMarker;
    }

    public void setLonMarker(double lonMarker) {
        this.lonMarker = lonMarker;
    }

    public void setLangMarker(double langMarker) {
        this.langMarker = langMarker;
    }

    public void setGenreMarker(int genreMarker) {
        this.genreMarker = genreMarker;
    }

    public void setImageMarker(String imageMarker) {
        this.imageMarker = imageMarker;
    }

    public void setRecordMarker(String recordMarker) {
        this.recordMarker = recordMarker;
    }

    public Marker toMarker (Context context){
        return new Marker(getTitleMarker(), getDescriptionMarker(), getLatLngMarker(getLangMarker(),getLonMarker()))
                .setIcon(new Icon(context, Icon.Size.LARGE, "danger", "3887be"));
    }
}


