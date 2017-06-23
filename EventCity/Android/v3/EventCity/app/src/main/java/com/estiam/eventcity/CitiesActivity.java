package com.estiam.eventcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.estiam.eventcity.adapter.CityAdapter;
import com.estiam.eventcity.api.EventCityApiService;
import com.estiam.eventcity.model.City;
import com.estiam.eventcity.model.EventCityResponse;

import java.util.ArrayList;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitiesActivity extends AppCompatActivity {
    ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
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
*/

        EventCityApiService apiService = new Retrofit.Builder()
                .baseUrl("http://192.168.0.15:3000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(EventCityApiService.class);

        apiService.getCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void handleResponse(EventCityResponse<City> response) {
        cities = response.getData();
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
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
