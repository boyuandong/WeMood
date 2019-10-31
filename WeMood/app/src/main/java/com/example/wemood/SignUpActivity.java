package com.example.wemood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewAnimator;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText addUserName;
    EditText addPassWord;
    EditText addFirstName;
    EditText addLastName;
    EditText addEmail;
    EditText addPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final String TAG = "Sample";
        Button signUpButton;



        FirebaseFirestore db;

        signUpButton = findViewById(R.id.sign_up_activity_button);
        addUserName =findViewById(R.id.user_name);
        addPassWord = findViewById(R.id.user_password);
        addFirstName = findViewById(R.id.first_name);
        addLastName = findViewById(R.id.last_name);
        addEmail = findViewById(R.id.email);
        addPhone = findViewById(R.id.phone);

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Users");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = addUserName.getText().toString();
                final String password = addPassWord.getText().toString();
                final String firstName = addFirstName.getText().toString();
                final String lastName  = addLastName.getText().toString();
                final String email  = addEmail.getText().toString();
                final String phone  = addPhone.getText().toString();

                //HashMap<String, String> data = new HashMap<>();

                Map<String, Map<String,String>> data = new HashMap<>();
                Map<String,String> userInfo = new HashMap<>();
                userInfo.put("password",password);
                userInfo.put("firstName",firstName);
                userInfo.put("lastName",lastName);
                userInfo.put("email",email);
                userInfo.put("phone",phone);

                if (name.length() > 0 && userInfo.size() > 0){
                    data.put("userInfo", userInfo);

                    collectionReference
                            .document(name)
                            .set(data)

                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"Data addition successful");

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"Data addition failed" + e.toString());
                                }
                            });

                    addUserName.setText("");
                    addPassWord.setText("");
                    addFirstName.setText("");
                    addLastName.setText("");
                    addEmail.setText("");
                    addPhone.setText("");

                    Intent intent = new Intent(SignUpActivity.this, LogSignInActivity.class);
                    startActivity(intent);


                }
            }
        });




    }
}
