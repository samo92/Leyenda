package mx.leyenda.leyendabeta;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;

/**
 * Created by samo92 on 07/10/2015.
 */
public class MbMarker {
    //VARIABLES
    private MapView myMapView;
    private String titleMarker;
    private String descriptionMarker;
    private LatLng latLngMarker;

    public MbMarker(MapView myMapView, String titleMarker, String descriptionMarker, LatLng latLngMarker) {
        this.myMapView = myMapView;
        this.titleMarker = titleMarker;
        this.descriptionMarker = descriptionMarker;
        this.latLngMarker = latLngMarker;
    }

    public MapView getMyMapView() {
        return myMapView;
    }

    public String getTitleMarker() {
        return titleMarker;
    }

    public String getDescriptionMarker() {
        return descriptionMarker;
    }

    public LatLng getLatLngMarker() {
        return latLngMarker;
    }

    public void setMyMapView(MapView myMapView) {
        this.myMapView = myMapView;
    }

    public void setTitleMarker(String titleMarker) {
        this.titleMarker = titleMarker;
    }

    public void setDescriptionMarker(String descriptionMarker) {
        this.descriptionMarker = descriptionMarker;
    }

    public void setLatLngMarker(LatLng latLngMarker) {
        this.latLngMarker = latLngMarker;
    }
}
