package com.vathsav.blink.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.vathsav.blink.R;
import com.vathsav.blink.model.LogItemSetter;

public class NewLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        final EditText editTextLogTitle = findViewById(R.id.edit_text_new_log_title);
        final EditText editTextLogContent = findViewById(R.id.edit_text_new_log_content);

        FloatingActionButton fabPublish = findViewById(R.id.fab_publish_new_log);

        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextLogTitle.getText().toString();
                String content = editTextLogContent.getText().toString();

                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new LogItemSetter(title, content, ServerValue.TIMESTAMP)
                );
            }
        });
    }
}
