package com.hackethon.myapplication.activity;

import java.util.ArrayList;
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
        int count = 0;
        char[] ch = line.toCharArray();

        //making list of string 1st to omit \n
        List<String> sList = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        while (count < ch.length) {
            if (ch[count] == '\n') {
                sList.add(s.toString());
                s = new StringBuilder();
            }
            s.append(ch[count]);
            count++;
        }
        sList.add(s.toString());//to add last one

        // System.out.println("the list is " + sList);

        //adding into list
        for (String dir : sList) {
            System.out.println(dir);

            int lastIndexOfOpen = dir.lastIndexOf('(');
            int lastIndexOfClose = dir.lastIndexOf(')');
            int lastIndexOfSlash = dir.lastIndexOf('/');

            Instruction directionList = new Instruction();
            directionList.setDirection(dir.substring(0,lastIndexOfOpen-1));
            directionList.setDistance(dir.substring(lastIndexOfOpen + 1,lastIndexOfSlash));
            directionList.setTime(dir.substring(lastIndexOfSlash + 1,lastIndexOfClose - 1));
            //System.out.println("*******the instruction is " + directionList);
            instructionLists.add(directionList);
        }//System.out.println("the list is " + instructionLists);
        return instructionLists;


    }


    public static void main(String[] args) {

        Instruction directionList = new Instruction();
        String response = "Step 1: Head west toward Husen Shah Baba Rd (85 m/1 min)\n" +
                "Step 2: Turn right onto Husen Shah Baba Rd (0.2 km/1 min)\n" +
                "Step 3: Turn right at the 1st cross street onto Airport Rd/Vishrantwadi Airport RdPass by the gas station (on the left) (1.6 km/3 mins)\n" +
                "Step 4: Turn right after 509 Chowk (on the left)Pass by Mhasoba Temple (on the left in 700m)Destination will be on the left (2.1 km/4 mins)";


        System.out.println(directionList.parseRawData(response));

    }

}
