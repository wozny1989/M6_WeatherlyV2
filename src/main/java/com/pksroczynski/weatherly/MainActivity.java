package com.pksroczynski.weatherly;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class MainActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();
    }
}
