package com.vathsav.blink.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vathsav.blink.R;
import com.vathsav.blink.activity.MainActivity;
import com.vathsav.blink.model.LogItem;
import com.vathsav.blink.utils.Constants;

import java.util.List;

/**
 * Adapter for staggered grid of cards
 */

public class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
    private final List<LogItem> listOfLogs;
    private final Context context;

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
    public void onBindViewHolder(final LogViewHolder holder, int position) {
        final boolean isFavorite = listOfLogs.get(position).is_log_favorite();

        holder.key = listOfLogs.get(position).get_log_key();
        holder.title = listOfLogs.get(position).get_log_title();
        holder.content = listOfLogs.get(position).get_log_content();
        holder.color = listOfLogs.get(position).get_log_color();
        holder.timestamp = listOfLogs.get(position).get_log_timestamp();

        switch (holder.color) {
            case "cyan":
                holder.layoutCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border_cyan));
                break;
            case "red":
                holder.layoutCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border_red));
                break;
            case "blue":
                holder.layoutCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border_blue));
                break;
            case "yellow":
                holder.layoutCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border_yellow));
                break;
            case "green":
                holder.layoutCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border_green));
                break;
            case "gray":
                holder.layoutCardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border_gray));
                break;
        }

        holder.textViewTitle.setText(listOfLogs.get(position).get_log_title());

        if (listOfLogs.get(position).is_log_favorite())
            holder.imageButtonFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            holder.imageButtonFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        holder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Constants.intentNewTextLogActivity)
                        .putExtra("key", holder.key)
                        .putExtra("title", holder.title)
                        .putExtra("content", holder.content)
                );
            }
        });

        holder.imageButtonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Constants.referenceLogs)
                            .child(holder.key).child("log_favorite").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(MainActivity.coordinatorLayout, "Removed from Favorites!", Snackbar.LENGTH_LONG)
                                    .setAction("Okay", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    }).show();
                        }
                    });
                } else {
                    FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Constants.referenceLogs)
                            .child(holder.key).child("log_favorite").setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(MainActivity.coordinatorLayout, "Added to Favorites!", Snackbar.LENGTH_LONG)
                                    .setAction("Okay", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    }).show();
                        }
                    });
                }
            }
        });

        holder.imageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(Intent.createChooser(
                        new Intent(android.content.Intent.ACTION_SEND).setType("text/plain")
                                .putExtra(android.content.Intent.EXTRA_SUBJECT, holder.title)
                                .putExtra(android.content.Intent.EXTRA_TEXT, holder.content),
                        "How would you like to share your log?")
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfLogs.size();
    }
}
