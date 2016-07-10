package com.hackethon.myapplication.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hackethon.myapplication.R;

import java.util.List;

/**
 * Created by samridhamla06 on 09/07/16.
 */
public class OptionsAdapter extends ArrayAdapter<TravellingMean> {
    private TextView name;
    private TextView time;
    private TextView money;
    private List<TravellingMean> travellingMeanList;


    public OptionsAdapter(Context context,List<TravellingMean> travellingMeanList) {
        super(context, R.layout.option_list, travellingMeanList);
        this.travellingMeanList = travellingMeanList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.option_list, null);
        name = (TextView)view.findViewById(R.id.name);
        time = (TextView)view.findViewById(R.id.timeValue2);
        money = (TextView)view.findViewById(R.id.money);

        name.setText(travellingMeanList.get(position).getName());
        time.setText(String.valueOf(travellingMeanList.get(position).getTime()) + " hrs");
        money.setText(" Rs " + String.valueOf(travellingMeanList.get(position).getMoney()));

        return view;
    }
}
