package mx.leyenda.leyendabeta.io;

import mx.leyenda.leyendabeta.constant.Constant;
import mx.leyenda.leyendabeta.io.model.ShowMarkerResponse;
import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by samo92 on 12/10/2015.
 */
public class ApiClient {

    private static ApiService apiService;
    public static ApiService getApiService(){
        if(apiService==null){
            RestAdapter restAdapter= new RestAdapter.Builder().setEndpoint(Constant.URL_BASE).
                    setLogLevel(RestAdapter.LogLevel.BASIC).build();

            apiService=restAdapter.create(ApiService.class);
        }
        return apiService;
    }

    public static void getMarkers(Callback<ShowMarkerResponse> serverResponse){
        getApiService().getMarkers(serverResponse);
    }
}
