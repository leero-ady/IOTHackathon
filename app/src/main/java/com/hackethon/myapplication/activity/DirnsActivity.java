package com.hackethon.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hackethon.myapplication.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DirnsActivity extends AppCompatActivity {

    private ListView listView;
    private String response;
    private CustomAdapter customAdapter;
    private Instruction instruction;
    private List<Instruction> lists;
    public static final String PARKING_URL = Constants.URL + "directions";
    private LocationInfo locationInfo;
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;
    private JSONObject responseReceived;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirns);
        String json = Util.getJsonFromObject(Util.getLocationInfoObject(this));
        initialiseLocals();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, PARKING_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseReceived = response;
                Log.d("resp directions", responseReceived.toString());
                try {
                    actOnResponse();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley error", error.toString());
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void initialiseLocals() {
        instruction = new Instruction();
    }

    private void actOnResponse() throws JSONException {
        String response = responseReceived.getString(Constants.DIRECTIONS);
        lists = new ArrayList<>();
        lists = instruction.parseRawData(response);
        System.out.println("value of dier list " + lists);
        customAdapter = new CustomAdapter(this, lists);
        listView = (ListView) findViewById(R.id.instructionList);
        listView.setAdapter(customAdapter);
    }

}
