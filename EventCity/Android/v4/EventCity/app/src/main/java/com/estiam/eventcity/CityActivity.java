package com.estiam.eventcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.estiam.eventcity.model.City;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {
    private City city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null) {
            city = (City) getIntent().getExtras().get("RESULT_CITY");
        }

        TextView tvCity = (TextView) findViewById(R.id.tvCity);
        ImageView ivCity = (ImageView) findViewById(R.id.ivCity);
        tvCity.setText(city.getName());
        Glide.with(getApplicationContext()).load(city.getImage()).into(ivCity);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            navigate(null);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void navigate(View view) {
        Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
        intent.putExtra("RESULT_EVENTS", city.getEvents());
        intent.putExtra("RESULT_CITY", city);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("CITY", "onActivityResult");
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                city = (City) data.getSerializableExtra("RESULT_CITY");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ArrayList<City> cities = (ArrayList<City>) getIntent().getExtras().get("RESULT_CITIES");
                Intent intent = new Intent();
                intent.putExtra("RESULT_CITIES", cities);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
