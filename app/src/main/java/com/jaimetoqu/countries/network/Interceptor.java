package com.jaimetoqu.countries.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaime on 4/16/17.
 */

public class Interceptor {

    private static final String BASE_URL = "https://restcountries-v1.p.mashape.com/";
    public static final int CODE = 200;

    public Country get() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                        .header("X-Mashape-Key", "7tCii2w18gmshZn2G5f4pQVRq3Igp198AFPjsnAfkENXEBvQld")
                        .header("Accept", "application/json")
                        .build();

                Response response = chain.proceed(request);
                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
            /*Never forget about adding the converter, otherwise you can not parse the data*/
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Country request = interceptor.create(Country.class);
        return request;
    }
}
