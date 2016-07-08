package com.hackethon.myapplication.activity;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 8/7/16.
 */
public class ApplicationClass extends Application {

    DirectionAPI directionAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDirectionApi();
    }

    private void buildDirectionApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        directionAPI = retrofit.create(DirectionAPI.class);
    }

    public DirectionAPI getDirectionAPIService(){
        return directionAPI;
    }
}
