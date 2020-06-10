package com.pksroczynski.weatherly.models;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("lon")
    private Double lon;
    @SerializedName("lat")
    private Double lat;

    public Coordinates() {
    }

    public Coordinates(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
