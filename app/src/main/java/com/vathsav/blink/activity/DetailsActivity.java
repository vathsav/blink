package com.vathsav.blink.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.vathsav.blink.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String title = getIntent().getStringExtra("title");
        final String content = getIntent().getStringExtra("content");
        final String color = getIntent().getStringExtra("color");
        String timestamp = getIntent().getStringExtra("timestamp");

        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        switch (color) {
            case "cyan":
                collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.cardview_color_cyan));
                break;
            case "red":
                collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.cardview_color_red));
                break;
            case "blue":
                collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.cardview_color_blue));
                break;
            case "yellow":
                collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.cardview_color_yellow));
                break;
            case "green":
                collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.cardview_color_green));
                break;
            case "gray":
                collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.cardview_color_gray));
                break;
        }

        TextView textViewContent = findViewById(R.id.text_view_content);

        toolbar.setTitle(title);
        textViewContent.setText(content);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(
                        new Intent(android.content.Intent.ACTION_SEND).setType("text/plain")
                                .putExtra(android.content.Intent.EXTRA_SUBJECT, title)
                                .putExtra(android.content.Intent.EXTRA_TEXT, content),
                        "How would you like to share your log?")
                );
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
