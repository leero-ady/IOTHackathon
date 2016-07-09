package com.hackethon.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.hackethon.myapplication.R;
import java.util.ArrayList;
import java.util.List;

public class DirnsActivity extends AppCompatActivity {

    private ListView listView;
    private String response;
    private CustomAdapter customAdapter;
    private Instruction instruction;
    private List<Instruction> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirns);
        response = "Step 1: Head west toward Husen Shah Baba Rd (85 m/1 min)\n" +
                "Step 2: Turn right onto Husen Shah Baba Rd (0.2 km/1 min)\n" +
                "Step 3: Turn right at the 1st cross street onto Airport Rd/Vishrantwadi Airport RdPass by the gas station (on the left) (1.6 km/3 mins)\n" +
                "Step 4: Turn right after 509 Chowk (on the left)Pass by Mhasoba Temple (on the left in 700m)Destination will be on the left (2.1 km/4 mins)";



        instruction = new Instruction();
        lists = new ArrayList<>();
        lists = instruction.parseRawData(response);
        System.out.println("value of dier list " + lists);
        customAdapter = new CustomAdapter(this, lists);
        listView = (ListView)findViewById(R.id.instructionList);
        listView.setAdapter(customAdapter);
    }

}
