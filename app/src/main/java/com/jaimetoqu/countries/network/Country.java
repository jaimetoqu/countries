package com.jaimetoqu.countries.network;

import com.jaimetoqu.countries.models.Wrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jaime on 4/16/17.
 */

public interface Country {

    @GET("alpha/{code}")
    Call<Wrapper> countryCode(@Path("code") String code);
}
