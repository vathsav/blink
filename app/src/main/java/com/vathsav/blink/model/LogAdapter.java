package com.vathsav.blink.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vathsav.blink.R;

import java.util.List;

/**
 * Adapter for staggered grid of cards
 */

public class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
    List<LogItem> listOfLogs;
    Context context;

    public LogAdapter(List<LogItem> listOfLogs, Context context) {
        this.listOfLogs = listOfLogs;
        this.context = context;
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_log, parent, false);
        return new LogViewHolder(layout, context);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        holder.textViewTitle.setText(listOfLogs.get(position).get_log_title());
        holder.textViewTimestamp.setText(String.valueOf(listOfLogs.get(position).get_log_key()));
    }

    @Override
    public int getItemCount() {
        return listOfLogs.size();
    }
}
