package com.vathsav.blink.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.vathsav.blink.R;
import com.vathsav.blink.model.LogItemSetter;
import com.vathsav.blink.utils.Constants;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        final EditText editTextLogTitle = findViewById(R.id.edit_text_new_log_title);
        final EditText editTextLogContent = findViewById(R.id.edit_text_new_log_content);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_new_log));
        }

        final String key = getIntent().getStringExtra("key");
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        final FloatingActionButton fabPublish = findViewById(R.id.fab_publish_new_log);
        ImageButton imageButtonColorCyan = findViewById(R.id.image_button_color_cyan);
        ImageButton imageButtonColorRed = findViewById(R.id.image_button_color_red);
        ImageButton imageButtonColorBlue = findViewById(R.id.image_button_color_blue);
        ImageButton imageButtonColorYellow = findViewById(R.id.image_button_color_yellow);
        ImageButton imageButtonColorGreen = findViewById(R.id.image_button_color_green);
        ImageButton imageButtonColorGray = findViewById(R.id.image_button_color_gray);

        fabPublish.setBackgroundColor(getResources().getColor(R.color.cardview_color_yellow));
        final String[] selectedColor = new String[1];

        imageButtonColorCyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor[0] = "cyan";
            }
        });

        imageButtonColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor[0] = "red";
            }
        });

        imageButtonColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor[0] = "blue";
            }
        });

        imageButtonColorYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor[0] = "yellow";
            }
        });

        imageButtonColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor[0] = "green";
            }
        });

        imageButtonColorGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor[0] = "gray";
            }
        });


        if (key != null) {
            editTextLogTitle.setText(title);
            editTextLogContent.setText(content);
        }

        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextLogTitle.getText().toString();
                String content = editTextLogContent.getText().toString();

                if (key != null) {
                    FirebaseDatabase.getInstance().getReference().child(Constants.user_id).child(key).setValue(
                            new LogItemSetter(title, content, false, selectedColor[0], ServerValue.TIMESTAMP)
                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(Constants.intentDetailsActivity);
                        }
                    });
                } else {
                    FirebaseDatabase.getInstance().getReference().child(Constants.user_id).push().setValue(
                            new LogItemSetter(title, content, false, selectedColor[0], ServerValue.TIMESTAMP)
                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(Constants.intentDetailsActivity);
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
