package com.behindthebadge.android.badge.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.behindthebadge.android.badge.R;

public class AddUserActivity extends AppCompatActivity {
    EditText mAddUserName;
    EditText mAddPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button addUserBtn = findViewById(R.id.add_user_button);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        mAddUserName = findViewById(R.id.add_user_text);
        mAddPhone = findViewById(R.id.add_phone_text);
    }

    private void addUser() {
        String newName = mAddUserName.getText().toString();
        String newPhone = mAddPhone.getText().toString();


    }
}
