package com.pksroczynski.weatherly;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.hawk.Hawk;
import com.pksroczynski.weatherly.adapters.LocationsAdapter;
import com.pksroczynski.weatherly.api.ApiClient;
import com.pksroczynski.weatherly.helpers.Preferences;
import com.pksroczynski.weatherly.models.Weatherly;
import java.util.ArrayList;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {
    private LocationsAdapter adapter;
    private ArrayList<Weatherly> favourites = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (!Hawk.contains(Preferences.FAVOURITES)) {
            addToList("Austin", true);
        } else {
            favourites = Hawk.get(Preferences.FAVOURITES);
            initAdapter();
        }

        Button addLocalization = (Button)findViewById(R.id.btn_new_local);
        EditText txtLocalization = (EditText)findViewById(R.id.txt_local);
        addLocalization.setOnClickListener(view -> {
            String localization = txtLocalization.getText().toString();
            if (!localization.isEmpty()) {
                favourites.forEach(weatherly -> {
                    if (!weatherly.getName().equals(localization)) {
                        addToList(localization, false);
                    }
                });
            }
        });
    }

    private void addToList(String name, Boolean initial) {
        new ApiClient() .createClient(Preferences.API_URL).getWeather(name, Preferences.APP_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<Weatherly>>() {
                    @Override
                    public void onSuccess(Response<Weatherly> weatherResponse) {
                        if (weatherResponse.body() != null) {
                            favourites.add(weatherResponse.body());
                            Hawk.put(Preferences.FAVOURITES, favourites);
                            if (initial) {
                                Hawk.put(Preferences.SELECTED, weatherResponse.body());
                            }
                            initAdapter();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void initAdapter() {
        adapter = new LocationsAdapter(favourites, getApplicationContext());
        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

    }

}
