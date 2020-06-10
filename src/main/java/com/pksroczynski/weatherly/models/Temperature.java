package com.pksroczynski.weatherly.models;

import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("temp")
    private Double temp;

    public Temperature() {
    }

    public Temperature(Double temp) {
        this.temp = temp;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
