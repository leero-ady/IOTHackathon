package com.hackethon.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by samridhamla06 on 09/07/16.
 */
public class Util {

    public static String getJsonFromObject(LocationInfo locationInfo){
        return new Gson().toJson(locationInfo);
    }

    public static LocationInfo toJsonFromString(String jsonString){
        return new Gson().fromJson(jsonString, LocationInfo.class);
    }

    public static SharedPreferences getInstanceOfSharedPreferences(Context context){
        return context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
    }

     public static LocationInfo getLocationInfoObject(Context context){
         SharedPreferences sharedPreferences = getInstanceOfSharedPreferences(context);
         String json = sharedPreferences.getString(Constants.LOCATION_INFO,"000");
         return toJsonFromString(json);
     }


}
