package com.pksroczynski.weatherly.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weatherly {
    @SerializedName("coord")
    private Coordinates coord;
    @SerializedName("weather")
    private ArrayList<Weather> weather;
    @SerializedName("main")
    private Temperature temp;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private Integer cod;
    @SerializedName("favourite")
    private Boolean favourite;

    public Weatherly() {
    }

    public Weatherly(Coordinates coord, ArrayList<Weather> weather, Temperature temp, String name, Integer cod, Boolean favourite) {
        this.coord = coord;
        this.weather = weather;
        this.temp = temp;
        this.name = name;
        this.cod = cod;
        this.favourite = favourite;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }
}
