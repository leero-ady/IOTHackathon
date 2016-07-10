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
public class CustomAdapter extends ArrayAdapter<Instruction> {
    private TextView direction;
    private TextView time;
    private TextView distance;
    private List<Instruction> instructions;


    public CustomAdapter(Context context, List<Instruction> lists) {
        super(context, R.layout.list_item, lists);
        instructions = lists;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, null);
        direction = (TextView)view.findViewById(R.id.direction);
        time = (TextView)view.findViewById(R.id.timeValue);
        distance = (TextView)view.findViewById(R.id.distance);

        direction.setText(instructions.get(position).getDirection());
        time.setText(instructions.get(position).getTime());
        distance.setText(instructions.get(position).getDistance());
        return view;
    }
}
