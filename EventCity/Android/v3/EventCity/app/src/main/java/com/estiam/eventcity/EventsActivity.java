package com.estiam.eventcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.estiam.eventcity.adapter.EventAdapter;
import com.estiam.eventcity.model.City;
import com.estiam.eventcity.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventsActivity extends AppCompatActivity {
    ArrayList<Event> events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras() != null) {
            events = (ArrayList<Event>) getIntent().getExtras().get("RESULT_EVENTS");
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvEvents);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new EventAdapter(getApplicationContext(), events, new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                intent.putExtra("RESULT_EVENT", event);
                intent.putExtra("RESULT_EVENTS", events);
                startActivityForResult(intent, 3);
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("EVENTS", "onActivityResult");
        if (requestCode == 3) {
            if(resultCode == RESULT_OK) {
                events = (ArrayList<Event>) data.getSerializableExtra("RESULT_EVENTS");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                City city = (City) getIntent().getExtras().get("RESULT_CITY");
                Intent intent = new Intent();
                intent.putExtra("RESULT_CITY", city);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
