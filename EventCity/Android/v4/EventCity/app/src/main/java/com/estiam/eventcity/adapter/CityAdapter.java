package com.estiam.eventcity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.estiam.eventcity.R;
import com.estiam.eventcity.model.City;

import java.util.List;

/**
 * Created by ESTIAM on 20/06/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {

    private Context context;
    private List<City> cities;
    private OnCityClickListener listener;

    public interface OnCityClickListener {
        void onCityClick(City city);
    }

    public CityAdapter(Context context, List<City> cities, OnCityClickListener listener) {
        this.context = context;
        this.cities = cities;
        this.listener = listener;
    }

    public static class CityHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public CityHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void bind(final City city, final OnCityClickListener listener) {
            textView.setText(city.getName());
            Glide.with(itemView.getContext()).load(city.getImage()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onCityClick(city);
                }
            });
        }
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position) {
        holder.bind(cities.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
