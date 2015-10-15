package mx.leyenda.leyendabeta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mapbox.mapboxsdk.overlay.GpsLocationProvider;
import com.mapbox.mapboxsdk.overlay.ItemizedIconOverlay;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.overlay.UserLocationOverlay;
import com.mapbox.mapboxsdk.views.MapView;

import java.util.ArrayList;
import java.util.List;

import mx.leyenda.leyendabeta.domain.MbMarker;
import mx.leyenda.leyendabeta.io.ApiClient;
import mx.leyenda.leyendabeta.ui.fragment.PlayDialogFragment;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by samo92 on 06/10/2015.
 */
public class MapBoxView extends AppCompatActivity {

    //Variables
    private ArrayList<Marker> myMarkers = new ArrayList<>();


    MapView myMapView;
    GpsLocationProvider locationProvider;
    AlertDialog alertDialog = null;
    LocationManager locationManager = null;

    //METODOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.findViewById(R.id.mapview);
        setContentView(R.layout.mapbox_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Validar estado de gps
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlertDialog(MapBoxView.this, "GPS", "El GPS esta desactivado, ¿Desea activarlo?", true);
        }
        myMapView = (MapView) findViewById(R.id.mapview);
        locationProvider = new GpsLocationProvider(getApplicationContext());
        setupMapView();
        getMarkers();
        //makeMarker(myMarkers);      //mandamos llamar el metodo para poblar el mapa con leyendas

        //makeMarker(myMarkers);      //mandamos llamar el metodo para poblar el mapa con leyendas


    }

    private void showEditDialog(String url) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        PlayDialogFragment playDialogFragment = PlayDialogFragment.newInstance("Reproducir", url);
        playDialogFragment.show(fragmentManager, "dialogfragment_play");
    }

    private void getMarkers() {
        ApiClient.getMarkers(new Callback<List<MbMarker>>() {
            @Override
            public void success(final List<MbMarker> mbMarkers, Response response) {

                for (int i = 0; i < mbMarkers.size(); i++) {
                    myMarkers.add(mbMarkers.get(i).toMarker(MapBoxView.this).addTo(myMapView));
                    //.setIcon(new Icon(this, Icon.Size.LARGE, "danger", "3887be"));
                }
                myMapView.addMarkers(myMarkers);
                //markers.add(myMarker.toMarker());
                myMapView.addItemizedOverlay(new ItemizedIconOverlay(MapBoxView.this, myMarkers, new ItemizedIconOverlay.OnItemGestureListener<Marker>() {


                    @Override
                    public boolean onItemSingleTapUp(int i, Marker marker) {
                        String titulo = mbMarkers.get(i).getTitleMarker();
                        Toast.makeText(MapBoxView.this, "Marker Selected: " + titulo, Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(int i, Marker marker) {
                        String url = mbMarkers.get(i).getRecordMarker();
                        showEditDialog(url);
                        return true;
                    }
                }));


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
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


    /*private void makeMarker (ArrayList<Marker> myMarkers){     //Metodo que añade un solo marker
        myMarkers.add(new Marker(myMapView,
                                myMarker.getTitleMarker(),
                                myMarker.getDescriptionMarker(),
                                myMarker.getLatLngMarker(myMarker.getLangMarker(), myMarker.getLonMarker()))
                                .setIcon(new Icon(this, Icon.Size.LARGE, "danger", "3887be")));

        //myMarkers.add(myMarker.toMarker().addTo(myMapView));//.setIcon(new Icon(this, Icon.Size.LARGE, "danger", "3887be"));

        myMapView.addMarkers(myMarkers);
    }*/
}
