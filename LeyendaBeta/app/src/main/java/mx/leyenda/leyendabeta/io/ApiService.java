package mx.leyenda.leyendabeta.io;

import mx.leyenda.leyendabeta.constant.Constant;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by samo92 on 12/10/2015.
 */
public interface ApiService {
    @GET(Constant.PATH_MARKERS
    )
    //void showCourse(Callback<ShowCourseResponse> serviceResponse);
    //void showInstructor(Callback<ShowInstructorResponse>serviceResponseInstructor);
}
