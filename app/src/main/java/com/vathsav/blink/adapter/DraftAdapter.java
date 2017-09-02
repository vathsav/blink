package com.vathsav.blink.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.vathsav.blink.R;
import com.vathsav.blink.model.DraftItem;
import com.vathsav.blink.utils.Constants;

import java.util.List;

/**
 * Adapter for staggered grid of cards
 */

public class DraftAdapter extends RecyclerView.Adapter<DraftViewHolder> {
    private final List<DraftItem> listOfDrafts;
    private final Context context;

    public DraftAdapter(List<DraftItem> listOfDrafts, Context context) {
        this.listOfDrafts = listOfDrafts;
        this.context = context;
    }

    @Override
    public DraftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_draft, parent, false);
        return new DraftViewHolder(layout, context);
    }

    @Override
    public void onBindViewHolder(final DraftViewHolder holder, int position) {
        holder.key = listOfDrafts.get(position).get_draft_key();
        holder.title = listOfDrafts.get(position).get_draft_title();
        holder.content = listOfDrafts.get(position).get_draft_content();
        holder.color = listOfDrafts.get(position).get_draft_color();
        holder.timestamp = listOfDrafts.get(position).get_draft_timestamp();

        switch (holder.color) {
            case "cyan":
                holder.layoutCardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_color_cyan));
                break;
            case "red":
                holder.layoutCardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_color_red));
                break;
            case "blue":
                holder.layoutCardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_color_blue));
                break;
            case "yellow":
                holder.layoutCardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_color_yellow));
                break;
            case "green":
                holder.layoutCardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_color_green));
                break;
            case "gray":
                holder.layoutCardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_color_gray));
                break;
        }

        holder.textViewTitle.setText(listOfDrafts.get(position).get_draft_title());
        holder.textViewTimestamp.setText(String.valueOf(listOfDrafts.get(position).get_draft_timestamp()));

        holder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Constants.intentNewLogActivity)
                        .putExtra("key", holder.key)
                        .putExtra("title", holder.title)
                        .putExtra("content", holder.content)
                        .putExtra("color", holder.color)
                );
            }
        });

        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child(Constants.user_id).child(Constants.referenceDrafts).child(holder.key).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfDrafts.size();
    }
}
