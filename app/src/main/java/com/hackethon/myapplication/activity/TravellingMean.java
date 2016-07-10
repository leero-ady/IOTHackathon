package com.hackethon.myapplication.activity;


public class TravellingMean {
    private String name;
    private double time;
    private double money;

    @Override
    public String toString() {
        return "TravellingMean{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", money=" + money +
                '}';
    }

    public TravellingMean(String name, double time, double money) {
        this.name = name;
        this.time = time;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
