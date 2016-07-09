package com.hackethon.myapplication.activity;

import com.google.gson.annotations.SerializedName;


public class LocationInfo {

    @SerializedName(Constants.ORIG_LAT)
    private Double origLat;
    @SerializedName(Constants.ORIG_LONG)
    private Double origLong;
    @SerializedName(Constants.DEST_LAT)
    private Double destLat;
    @SerializedName(Constants.DEST_LONG)
    private Double destLong;

    @Override
    public String toString() {
        return "LocationInfo{" +
                "origLat=" + origLat +
                ", origLong=" + origLong +
                ", destLat=" + destLat +
                ", destLong=" + destLong +
                '}';
    }



    public Double getDestLong() {
        return destLong;
    }

    public LocationInfo() {
    }

    public void setDestLong(Double destLong) {
        this.destLong = destLong;
    }

    public Double getDestLat() {
        return destLat;
    }

    public void setDestLat(Double destLat) {
        this.destLat = destLat;
    }

    public Double getOrigLong() {
        return origLong;
    }

    public void setOrigLong(Double origLong) {
        this.origLong = origLong;
    }

    public Double getOrigLat() {
        return origLat;
    }

    public void setOrigLat(Double origLat) {
        this.origLat = origLat;
    }




}

