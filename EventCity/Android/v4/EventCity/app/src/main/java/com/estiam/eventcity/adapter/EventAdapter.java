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
import com.estiam.eventcity.model.Event;

import java.util.List;

/**
 * Created by ESTIAM on 21/06/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private Context context;
    private List<Event> events;
    private OnEventClickListener listener;

    public interface OnEventClickListener {
        void onEventClick(Event event);
    }

    public EventAdapter(Context context, List<Event> events, OnEventClickListener listener) {
        this.context = context;
        this.events = events;
        this.listener = listener;
    }

    public static class EventHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public EventHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void bind(final Event event, final OnEventClickListener listener) {
            textView.setText(event.getTitle());
            Glide.with(itemView.getContext()).load(event.getImage()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onEventClick(event);
                }
            });
        }
    }

    @Override
    public EventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new EventAdapter.EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventHolder holder, int position) {
        holder.bind(events.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
