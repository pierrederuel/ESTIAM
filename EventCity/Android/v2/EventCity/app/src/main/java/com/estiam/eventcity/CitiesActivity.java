package com.estiam.eventcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.estiam.eventcity.adapter.CityAdapter;
import com.estiam.eventcity.model.City;
import com.estiam.eventcity.model.Event;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {
    ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Event e = new Event();
        e.setTitle("Titre evt tourcoing");
        e.setImage("http://www.act-consultants.fr/sites/default/files/styles/full/public/contenus/425/tourcoing_2010_logo.png?itok=FYDY6yB8");

        cities = new ArrayList<City>();
        City c = new City();
        c.setName("Tourcoing");
        c.setImage("http://www.act-consultants.fr/sites/default/files/styles/full/public/contenus/425/tourcoing_2010_logo.png?itok=FYDY6yB8");
        c.getEvents().add(e);
        cities.add(c);

        Event e1 = new Event();
        e1.setTitle("Titre evt Lille");
        e1.setImage("https://dailynord.fr/wordpress/wp-content/uploads/2013/05/logoLille_1-0.jpg");

        City c1 = new City();
        c1.setName("Lille");
        c1.setImage("https://dailynord.fr/wordpress/wp-content/uploads/2013/05/logoLille_1-0.jpg");
        c1.getEvents().add(e1);
        cities.add(c1);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvCities);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CityAdapter(this, cities, new CityAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(City city) {
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("RESULT_CITIES", cities);
                intent.putExtra("RESULT_CITY", city);
                startActivityForResult(intent, 1);
            }
        }));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                cities = (ArrayList<City>) data.getSerializableExtra("RESULT_CITIES");
            }
        }
    }
}
