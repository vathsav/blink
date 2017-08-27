package com.vathsav.blink.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.vathsav.blink.R;
import com.vathsav.blink.model.LogItemSetter;
import com.vathsav.blink.utils.Constants;

public class NewLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);

        final EditText editTextLogTitle = findViewById(R.id.edit_text_new_log_title);
        final EditText editTextLogContent = findViewById(R.id.edit_text_new_log_content);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fabPublish = findViewById(R.id.fab_publish_new_log);

        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextLogTitle.getText().toString();
                String content = editTextLogContent.getText().toString();

                FirebaseDatabase.getInstance().getReference().child(Constants.user_id).push().setValue(
                        new LogItemSetter(title, content, false, "blue", ServerValue.TIMESTAMP)
                ).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
                    }
                });
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
}
