package com.example.wemood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wemood.Fragments.ProfileFragment;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        TextView nickname = findViewById(R.id.nickname);
        EditText username = findViewById(R.id.username);
        EditText firstname = findViewById(R.id.firstname);
        EditText lastname = findViewById(R.id.lastname);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        Button save = findViewById(R.id.save);

        String new_nickname = nickname.getText().toString();
        String new_username = username.getText().toString();
        String new_firstname = firstname.getText().toString();
        String new_lastname = lastname.getText().toString();
        String new_email = email.getText().toString();
        String new_phone = phone.getText().toString();

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickname");
        String userName = intent.getStringExtra("username");
        String firstName = intent.getStringExtra("firstname");
        String lastName = intent.getStringExtra("lastname");
        String Email = intent.getStringExtra("email");
        String Phone = intent.getStringExtra("phone");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, ProfileFragment.class);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
