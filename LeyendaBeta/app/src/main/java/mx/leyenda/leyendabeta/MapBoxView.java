package mx.leyenda.leyendabeta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.GpsLocationProvider;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.overlay.UserLocationOverlay;
import com.mapbox.mapboxsdk.views.MapView;

import java.util.ArrayList;

/**
 * Created by samo92 on 06/10/2015.
 */
public class MapBoxView extends AppCompatActivity {

    //Variables
    private ArrayList<Marker> myMarkers = new ArrayList<>();
    MbMarker myMarker;

    MapView myMapView;
    GpsLocationProvider locationProvider;
    AlertDialog alertDialog = null;
    LocationManager locationManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.findViewById(R.id.mapview);
        setContentView(R.layout.mapbox_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Validar estado de gps
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
           showAlertDialog(MapBoxView.this,"GPS","El GPS esta desactivado, ¿Desea activarlo?",true);
        }
        myMapView = (MapView) findViewById(R.id.mapview);
        locationProvider = new GpsLocationProvider(getApplicationContext());
        setupMapView();
        makeMarker(myMarkers);      //mandamos llamar el metodo para poblar el mapa con leyendas


    }

    private void setupMapView() {
        ///////////////////////FUNCIONALIDAD PARA TRACKEAR AL USUARIO///////////////////////////////
        myMapView.setUserLocationEnabled(true);
        myMapView.setUserLocationTrackingMode(UserLocationOverlay.TrackingMode.FOLLOW);
        myMapView.setUserLocationRequiredZoom(14);
        myMapView.goToUserLocation(true);
        UserLocationOverlay myLocationOverlay = new UserLocationOverlay(locationProvider, myMapView);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.setDrawAccuracyEnabled(true);
        myMapView.getOverlays().add(myLocationOverlay);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).
                setTitle(title)
                .setMessage(message)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void makeMarker (ArrayList<Marker> myMarkers){     //Metodo que añade un solo marker
        myMarkers.add(new Marker(myMapView,
                myMarker.getTitleMarker(), myMarker.getDescriptionMarker(), myMarker.getLatLngMarker()));
        myMapView.addMarkers(myMarkers);
    }


}
