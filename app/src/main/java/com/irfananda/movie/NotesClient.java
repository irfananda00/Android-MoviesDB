package com.irfananda.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by skday on 2/22/17.
 *
 */

public interface NotesClient {
    @GET("movie/now_playing?api_key="+Service.API_KEY)
    Call<MovieDao> getMovie(
            @Query("page") int page
    );
}
