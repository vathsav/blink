package com.vathsav.blink.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vathsav.blink.R;
import com.vathsav.blink.utils.Constants;

/**
 * ViewHolder for staggered grid of cards
 */

public class LogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textViewTitle;
    TextView textViewTimestamp;
    Context context;
    String title;
    String content;
    long timestamp;

    public LogViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);
        textViewTitle = itemView.findViewById(R.id.text_view_card_title);
        textViewTimestamp = itemView.findViewById(R.id.text_view_card_timestamp);
    }

    @Override
    public void onClick(View view) {
        context.startActivity(new Intent(Constants.intentDetailsActivity)
                .putExtra("title", title)
                .putExtra("content", content)
                .putExtra("timestamp", timestamp)
        );
    }
}
