package com.vathsav.blink.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vathsav.blink.R;
import com.vathsav.blink.utils.Constants;

/**
 * ViewHolder for staggered grid of cards
 */

public class DraftViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    final TextView textViewTitle;
    final TextView textViewTimestamp;
    final ImageButton imageButtonEdit;
    final ImageButton imageButtonDelete;
    final RelativeLayout layoutCardView;
    final Context context;

    String key;
    String title;
    String content;
    String color;
    long timestamp;

    public DraftViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);
        textViewTitle = itemView.findViewById(R.id.text_view_card_title);
        textViewTimestamp = itemView.findViewById(R.id.text_view_card_timestamp);
        imageButtonEdit = itemView.findViewById(R.id.image_button_edit);
        imageButtonDelete = itemView.findViewById(R.id.image_button_delete);
        layoutCardView = itemView.findViewById(R.id.layout_card_log);
    }

    @Override
    public void onClick(View view) {
        context.startActivity(new Intent(Constants.intentDetailsActivity)
                .putExtra("title", title)
                .putExtra("content", content)
                .putExtra("color", color)
                .putExtra("timestamp", timestamp)
        );
    }
}
