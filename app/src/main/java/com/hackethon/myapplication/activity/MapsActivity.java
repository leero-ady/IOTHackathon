package com.hackethon.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hackethon.myapplication.R;
import com.hackethon.myapplication.activity.model.DirectionModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final String TAG = "MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.d(TAG, "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.d(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private ArrayList<LatLng> decodePoly(String encoded) {
        ArrayList<LatLng> poly = new ArrayList();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng position = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(position);
        }
        return poly;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        ((ApplicationClass) getApplicationContext()).getDirectionAPIService().getDirections("Pune", "Katraj").
                enqueue(new Callback<DirectionModel>() {
                    @Override
                    public void onResponse(Call<DirectionModel> call, Response<DirectionModel> response) {
                        Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response.body() + "]");
                        ArrayList<LatLng> points = new ArrayList<LatLng>();
                        for (int i = 0; i < response.body().getRoutes().size(); i++) {
                            for (int j = 0; j < response.body().getRoutes().get(i).getLegs().size(); j++) {
                                for (int k = 0; k < response.body().getRoutes().get(i).getLegs().get(j).getSteps().size(); k++) {
                                    for (int x = 0; x < response.body().getRoutes().get(i).getLegs().get(j).getSteps().size(); x++) {
                                        Log.d(TAG, response.body().getRoutes().get(i).getLegs().get(j).getSteps().get(x).getHtmlInstructions());
                                        mMap.addPolyline(new PolylineOptions()
                                                .addAll(decodePoly(response.body().getRoutes().get(i).getLegs().get(j).getSteps().get(x).getPolyline().getPoints()))
                                                .width(5)
                                                .color(Color.RED));
                                    }
                                }
                            }
                        }
                        mMap.addPolyline(new PolylineOptions()
                                .add()
                                .width(5)
                                .color(Color.RED));
                    }

                    @Override
                    public void onFailure(Call<DirectionModel> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: " + "call = [" + call + "], t = [" + t + "]");
                    }
                });
    }
}

