package com.vathsav.blink.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vathsav.blink.R;
import com.vathsav.blink.utils.Constants;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        final EditText editTextPhoneNumber = (EditText) findViewById(R.id.edit_text_phone_number);
        Button buttonVerifyPhoneNumber = (Button) findViewById(R.id.button_verify_phone_number);

        buttonVerifyPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextPhoneNumber.getText().toString().length() == 10) {
                    // TODO: 02/10/16 Add OTP verification
                    startActivity(Constants.intentMainActivity);
                } else {
                    Toast.makeText(getApplicationContext(), "Phone number is invalid!", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
