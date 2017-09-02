package com.vathsav.blink.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.vathsav.blink.R;
import com.vathsav.blink.model.DraftItemSetter;
import com.vathsav.blink.model.LogItemSetter;
import com.vathsav.blink.utils.Constants;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewTextLogActivity extends AppCompatActivity {

    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    EditText editTextLogTitle;
    EditText editTextLogContent;
    String key;
    String color = Constants.default_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text_log);

        editTextLogTitle = findViewById(R.id.edit_text_new_log_title);
        editTextLogContent = findViewById(R.id.edit_text_new_log_content);

        final Toolbar toolbar = findViewById(R.id.toolbar_new_text_log);
        toolbar.setTitle(getResources().getString(R.string.title_activity_new_log));
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        key = getIntent().getStringExtra("key");
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        if (getIntent().getStringExtra("key") != null) {
            color = getIntent().getStringExtra("color");
        }

        final FloatingActionButton fabPublish = findViewById(R.id.fab_publish_new_log);
        ImageButton imageButtonColorCyan = findViewById(R.id.image_button_color_cyan);
        ImageButton imageButtonColorRed = findViewById(R.id.image_button_color_red);
        ImageButton imageButtonColorBlue = findViewById(R.id.image_button_color_blue);
        ImageButton imageButtonColorYellow = findViewById(R.id.image_button_color_yellow);
        ImageButton imageButtonColorGreen = findViewById(R.id.image_button_color_green);
        ImageButton imageButtonColorGray = findViewById(R.id.image_button_color_gray);

        fabPublish.setBackgroundColor(getResources().getColor(R.color.cardview_color_yellow));

        imageButtonColorCyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "cyan";
                getWindow().setStatusBarColor(getResources().getColor(R.color.cardview_color_dark_cyan));
                toolbar.setBackgroundColor(getResources().getColor(R.color.cardview_color_cyan));
            }
        });

        imageButtonColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "red";
                getWindow().setStatusBarColor(getResources().getColor(R.color.cardview_color_dark_red));
                toolbar.setBackgroundColor(getResources().getColor(R.color.cardview_color_red));
            }
        });

        imageButtonColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "blue";
                getWindow().setStatusBarColor(getResources().getColor(R.color.cardview_color_dark_blue));
                toolbar.setBackgroundColor(getResources().getColor(R.color.cardview_color_blue));
            }
        });

        imageButtonColorYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "yellow";
                getWindow().setStatusBarColor(getResources().getColor(R.color.cardview_color_dark_yellow));
                toolbar.setBackgroundColor(getResources().getColor(R.color.cardview_color_yellow));
            }
        });

        imageButtonColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "green";
                getWindow().setStatusBarColor(getResources().getColor(R.color.cardview_color_dark_green));
                toolbar.setBackgroundColor(getResources().getColor(R.color.cardview_color_green));
            }
        });

        imageButtonColorGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "gray";
                getWindow().setStatusBarColor(getResources().getColor(R.color.cardview_color_dark_gray));
                toolbar.setBackgroundColor(getResources().getColor(R.color.cardview_color_gray));
            }
        });

        if (key != null) {
            editTextLogTitle.setText(title);
            editTextLogContent.setText(content);
        }

        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = editTextLogTitle.getText().toString();
                final String content = editTextLogContent.getText().toString();

                if (key != null) {
                    databaseReference.child(Constants.user_id).child(Constants.referenceLogs).child(key).setValue(
                            new LogItemSetter(title, content, false, color, ServerValue.TIMESTAMP)
                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(new Intent(Constants.intentDetailsActivity)
                                            .putExtra("title", title)
                                            .putExtra("content", content)
                                            .putExtra("color", color)
//                                    .putExtra("timestamp", timestamp)
                            );
                        }
                    });
                } else {
                    databaseReference.child(Constants.user_id).child(Constants.referenceLogs).push().setValue(
                            new LogItemSetter(title, content, false, color, ServerValue.TIMESTAMP)
                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(new Intent(Constants.intentDetailsActivity)
                                    .putExtra("title", title)
                                    .putExtra("content", content)
                                    .putExtra("color", color)
                            );
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (editTextLogTitle.getText().toString().isEmpty() && editTextLogContent.getText().toString().isEmpty())
                finish();
            else {
                if (color == null || color.equals("")) color = "cyan";

                if (key != null && key.length() > 0) {
                    // Editing a log or a draft. Must overwrite existing drafts. Child key.
                    databaseReference.child(Constants.user_id).child(Constants.referenceDrafts).child(key)
                            .setValue(new DraftItemSetter(
                                    editTextLogTitle.getText().toString(),
                                    editTextLogContent.getText().toString(),
                                    color,
                                    ServerValue.TIMESTAMP)
                            );
                } else {
                    databaseReference.child(Constants.user_id).child(Constants.referenceDrafts).push()
                            .setValue(new DraftItemSetter(
                                    editTextLogTitle.getText().toString(),
                                    editTextLogContent.getText().toString(),
                                    color,
                                    ServerValue.TIMESTAMP)
                            );
                }
                finish();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
