package com.hackethon.myapplication.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instruction {

    private String direction, distance, time;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "direction='" + direction + '\'' +
                ", distance='" + distance + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public List<Instruction> parseRawData(String line) {

        List<Instruction> instructionLists = new ArrayList<Instruction>();
        String[] lines = line.split(System.getProperty("line.separator"));
        List<String> sList = Arrays.asList(lines);
        //System.out.println("the list is *********" + sList + "end is ******" );

        //adding into list
        for (String dir : sList) {
            //System.out.println("the selected dir is ......" + dir);

            int lastIndexOfOpen = dir.lastIndexOf('(');
            int lastIndexOfClose = dir.lastIndexOf(')');
            int lastIndexOfSlash = dir.lastIndexOf('/');

            Instruction directionList = new Instruction();
            directionList.setDirection(dir.substring(0,lastIndexOfOpen-1));
            directionList.setDistance(dir.substring(lastIndexOfOpen + 1,lastIndexOfSlash));
            directionList.setTime(dir.substring(lastIndexOfSlash + 1,lastIndexOfClose));
            //System.out.println("*******the instruction is " + directionList);
            instructionLists.add(directionList);
        }

        System.out.println("the list is " + instructionLists);
        return instructionLists;


    }

}
