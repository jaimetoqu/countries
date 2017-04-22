package com.jaimetoqu.countries.background;

import android.os.AsyncTask;

import com.jaimetoqu.countries.models.Wrapper;
import com.jaimetoqu.countries.network.Country;
import com.jaimetoqu.countries.network.Interceptor;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jaime on 4/16/17.
 */

public class GetCountryCode extends AsyncTask<String, Void, Wrapper> {

    @Override
    protected Wrapper doInBackground(String... strings) {
        Country country = new Interceptor().get();
        Call<Wrapper> call = country.countryCode(strings[0]);
        try {
            Response<Wrapper> response = call.execute();
            if (Interceptor.CODE == response.code() && response.isSuccessful()) {
                return response.body();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
