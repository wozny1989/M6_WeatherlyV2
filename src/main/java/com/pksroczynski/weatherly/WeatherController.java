package com.pksroczynski.weatherly;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.hawk.Hawk;
import com.pksroczynski.weatherly.helpers.Preferences;
import com.pksroczynski.weatherly.models.Weatherly;

import java.text.DecimalFormat;

public class WeatherController extends AppCompatActivity {
    Weatherly weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherly_controller);

        if (!Hawk.contains(Preferences.SELECTED)) {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        } else {
            weather = Hawk.get(Preferences.SELECTED);

            TextView location = (TextView)findViewById(R.id.locationTextView);
            location.setText(weather.getName());

            TextView temp = (TextView)findViewById(R.id.tempTextView);
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            temp.setText(decimalFormat.format(weather.getTemp().getTemp() - 273.15F) + "°C");

            ImageView icon = (ImageView) findViewById(R.id.weatherSymbolImageView);
            int resId = getResources().getIdentifier(weather.getWeather().get(0).getWeatherIconByCondition(), "drawable", getPackageName());
            icon.setImageResource(resId);
        }


        Button locations = (Button) findViewById(R.id.location);
        locations.setOnClickListener(view -> {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        });
    }
}
