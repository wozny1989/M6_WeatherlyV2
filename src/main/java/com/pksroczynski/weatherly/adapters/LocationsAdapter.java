package com.pksroczynski.weatherly.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.orhanobut.hawk.Hawk;
import com.pksroczynski.weatherly.R;
import com.pksroczynski.weatherly.WeatherController;
import com.pksroczynski.weatherly.helpers.Preferences;
import com.pksroczynski.weatherly.models.Weatherly;

import java.util.ArrayList;

public class LocationsAdapter extends ArrayAdapter<Weatherly>{

    private ArrayList<Weatherly> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        RadioButton element;
        ImageView isFavourite;
    }

    public LocationsAdapter(ArrayList<Weatherly> data, Context context) {
        super(context, R.layout.location_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Weatherly dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.location_item, parent, false);
            viewHolder.element = (RadioButton) convertView.findViewById(R.id.element);
            viewHolder.element.setOnClickListener(view -> {
                setSelected(dataModel);
            });
            viewHolder.isFavourite = (ImageView) convertView.findViewById(R.id.isFavourite);
            viewHolder.isFavourite.setOnClickListener(view -> {
                setFavourite(viewHolder, dataModel);
            });
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.element.setText(dataModel.getName());
        setElementDefaultChoosen(viewHolder, dataModel);
        if (dataModel.getFavourite() != null && dataModel.getFavourite()) {
            viewHolder.isFavourite.setImageResource(android.R.drawable.star_big_on);
        } else {
            viewHolder.isFavourite.setImageResource(android.R.drawable.star_big_off);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    private void setElementDefaultChoosen(ViewHolder viewHolder, Weatherly dataModel) {
        if (Hawk.contains(Preferences.SELECTED)) {
            Weatherly weatherly = Hawk.get(Preferences.SELECTED);
            if (weatherly.getName().equals(dataModel.getName())) {
                viewHolder.element.setChecked(true);
            }
        }
    }

    private void setSelected(Weatherly dataModel) {
        Hawk.put(Preferences.SELECTED, dataModel);
        Intent intent = new Intent(getContext(), WeatherController.class);
        getContext().startActivity(intent);
    }

    private void setFavourite(ViewHolder viewHolder, Weatherly dataModel) {
        if (dataModel.getFavourite() == null) {
            dataModel.setFavourite(true);
        }
        dataModel.setFavourite(!dataModel.getFavourite());
        if (dataModel.getFavourite() != null && dataModel.getFavourite()) {
            viewHolder.isFavourite.setImageResource(android.R.drawable.star_big_on);
        } else {
            viewHolder.isFavourite.setImageResource(android.R.drawable.star_big_off);
        }
        ArrayList<Weatherly> weather = Hawk.get(Preferences.FAVOURITES);
        weather.forEach(weatherly ->  {
            if (dataModel.getName().equals(weatherly.getName())) {
                weatherly.setFavourite(dataModel.getFavourite());
            }
        });
        Hawk.put(Preferences.FAVOURITES, weather);
    }


}
