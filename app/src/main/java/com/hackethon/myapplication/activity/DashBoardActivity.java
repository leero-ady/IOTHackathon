package com.hackethon.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.hackethon.myapplication.R;
import com.hackethon.myapplication.activity.BusActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 8/7/16.
 */
public class DashBoardActivity extends Activity {

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE_FOR_FROM_TEXT = 1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE_FOR_TO_TEXT = 2;
    private static final String TAG = "DashBoardActivity";

    @BindView(R.id.input_from)
    EditText from;

    @BindView(R.id.input_to)
    EditText to;

    @OnClick(R.id.input_from)
    void openAutoCompleteForFromText() {
        startAutoCompleteWidget(PLACE_AUTOCOMPLETE_REQUEST_CODE_FOR_FROM_TEXT);
    }

    @OnClick(R.id.input_to)
    void openAutoCompleteForToText() {
        startAutoCompleteWidget(PLACE_AUTOCOMPLETE_REQUEST_CODE_FOR_TO_TEXT);
    }

    @OnClick(R.id.submit)
    void navigateToSelectionScreen() {
        if (!TextUtils.isEmpty(from.getText()) && !TextUtils.isEmpty(to.getText())) {
            startActivity(new Intent(this, BusActivity.class));
        }
    }

    private void startAutoCompleteWidget(int requestCode) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, requestCode);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
            Log.d(TAG, "startAutoCompleteWidget() called with: " + "");
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            Log.d(TAG, "startAutoCompleteWidget() called with: " + "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Place place = null;
        if (resultCode == RESULT_OK) {
            place = PlaceAutocomplete.getPlace(this, data);
            Log.d(TAG, "Place: " + place.getName());
        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            Log.d(TAG, status.getStatusMessage());
            return;
        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
            return;
        }

        switch (requestCode) {
            case PLACE_AUTOCOMPLETE_REQUEST_CODE_FOR_FROM_TEXT:
                from.setText(place != null ? place.getName() : "");
                break;
            case PLACE_AUTOCOMPLETE_REQUEST_CODE_FOR_TO_TEXT:
                to.setText(place != null ? place.getName() : "");
                break;
            default:
                return;
        }

    }
}
