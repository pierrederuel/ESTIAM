package com.estiam.eventcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.estiam.eventcity.model.City;
import com.estiam.eventcity.model.Event;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null) {
            event = (Event) getIntent().getExtras().get("RESULT_EVENT");
        }

        TextView tvEvent = (TextView) findViewById(R.id.tvEvent);
        ImageView ivEvent = (ImageView) findViewById(R.id.ivEvent);
        tvEvent.setText(event.getTitle());
        Glide.with(getApplicationContext()).load(event.getImage()).into(ivEvent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ArrayList<Event> events = (ArrayList<Event>) getIntent().getExtras().get("RESULT_EVENTS");
                Intent intent = new Intent();
                intent.putExtra("RESULT_EVENTS", events);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
