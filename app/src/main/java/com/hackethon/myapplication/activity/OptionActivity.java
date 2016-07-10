package com.hackethon.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hackethon.myapplication.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OptionActivity extends Activity {

    private ListView listView;
    private OptionsAdapter optionsAdapter;
    private List<TravellingMean> lists;
    private SharedPreferences sharedPreferences;
    private LocationInfo locationInfo;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        locationInfo = Util.getLocationInfoObject(this); // has all the coordinates for uber API
        Double origLat = locationInfo.getOrigLat();
        Double origLong = locationInfo.getOrigLong();
        Double destLat = locationInfo.getDestLat();
        Double destLong = locationInfo.getDestLong();
        Double distance = distance(origLat, destLat, origLong, destLong);
        Double car_avg = 10.0;
        Double diesel_cost = 60.0;
        Double numberOfLitres = distance/car_avg;
        Double total_cost_car = numberOfLitres * diesel_cost;
        Double total_cost_bus = distance * 2.5;
        Double estimated_time_bus = distance/25;
        Double estimated_time_car = distance/35;
        Double estimated_time_walk = distance/10;





        //assumption bus 10 km ----- 25

        String origCoord = String.valueOf(locationInfo.getOrigLat()) + "," + String.valueOf(locationInfo.getOrigLong());
        String destCoord = String.valueOf(locationInfo.getDestLat()) + "," + String.valueOf(locationInfo.getDestLong());
        //for UBER API


        listView = (ListView) findViewById(R.id.optionsList);
        lists = new ArrayList<>();
        sharedPreferences = Util.getInstanceOfSharedPreferences(this);
        lists.add(new TravellingMean("Bus", round(estimated_time_bus,2), round(total_cost_bus,2)));
        lists.add(new TravellingMean("Car", round(estimated_time_car,2), round(total_cost_car,2)));
        lists.add(new TravellingMean("Walk",round(estimated_time_walk,2) , 0));
        lists.add(new TravellingMean("Uber Cab", 50, 12));//............................from API
        System.out.println("&&&&&&&&&the options lists is  " + lists);

        optionsAdapter = new OptionsAdapter(this, lists);
        listView.setAdapter(optionsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        mode = "Bus";
                        break;
                    case 2:
                        mode = "Car";
                        break;
                    case 3:
                        mode = "Walk";
                        break;
                    case 4:
                        mode = "Uber Cab";
                        //uber Activity
                        break;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.MODE, mode);
                editor.commit();
                startActivity(new Intent(OptionActivity.this,MapsActivity.class));
            }
        });


    }

    public static double distance(double origLat, double destLat, double origLong,
                                  double destLong) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(destLat - origLat);
        Double lonDistance = Math.toRadians(destLong - origLong);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(origLat)) * Math.cos(Math.toRadians(destLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance)/1000;//km
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
