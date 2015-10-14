package mx.leyenda.leyendabeta.io.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import mx.leyenda.leyendabeta.domain.MbMarker;

/**
 * Created by samo92 on 12/10/2015.
 */
public class ShowMarkerResponse {

    @SerializedName("markers")
    ArrayList<MbMarker> allMarkers;

    public ArrayList<MbMarker> getMarkers(){
        return allMarkers;
    }
}
