package com.irfananda.movie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skday on 2/22/17.
 */

public class Service {
    public static final String API_KEY = "136451254291f50e7661446b9450ede6";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String IMG_URL = "http://image.tmdb.org/t/p/w500/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

}
