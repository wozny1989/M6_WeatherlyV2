package com.pksroczynski.weatherly.models;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    private Integer id;
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    public Weather() {
    }

    public Weather(Integer id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getWeatherIconByCondition() {
    int condition = this.getId();
    if (condition >= 0 && condition < 300) {
        return "tstorm1";
    } else if (condition >= 300 && condition < 500) {
        return "light_rain";
    } else if (condition >= 500 && condition < 600) {
        return "shower3";
    } else if (condition >= 600 && condition <= 700) {
        return "snow4";
    } else if (condition >= 701 && condition <= 771) {
        return "fog";
    } else if (condition >= 772 && condition < 800) {
        return "tstorm3";
    } else if (condition == 800) {
        return "sunny";
    } else if (condition >= 801 && condition <= 804) {
        return "cloudy2";
    } else if (condition >= 900 && condition <= 902) {
        return "tstorm3";
    } else if (condition == 903) {
        return "snow5";
    } else if (condition == 904) {
        return "sunny";
    } else if (condition >= 905 && condition <= 1000) {
        return "tstorm3";
    }
    return "status_unknown";
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
