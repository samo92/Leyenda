package mx.leyenda.leyendabeta.io;

import java.util.List;

import mx.leyenda.leyendabeta.constant.Constant;
import mx.leyenda.leyendabeta.domain.MbMarker;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by samo92 on 12/10/2015.
 */
public interface ApiService {

    @GET(Constant.PATH_MARKERS)
    void getMarkers(Callback<List<MbMarker>> serviceResponse);
}
