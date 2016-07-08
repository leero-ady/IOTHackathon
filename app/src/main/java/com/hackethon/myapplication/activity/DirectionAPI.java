package com.hackethon.myapplication.activity;

import com.hackethon.myapplication.activity.model.DirectionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 8/7/16.
 */
public interface DirectionAPI {

//base url
    //https://maps.googleapis.com/maps/api/

    //https://maps.googleapis.com/maps/api/directions/json?origin=Pune&destination=Katraj

    @GET("directions/json")
    Call<DirectionModel> getDirections(@Query("origin") String param1,
                                 @Query("destination") String param2);


}
