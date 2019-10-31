package com.example.wemood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private EditText addUserName;
    private EditText addEmail;
    private EditText addPassWord;
    private EditText addPhone;

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Button signUpButton;
    private FirebaseFirestore db;
    private CollectionReference collectionReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize FireBase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Database
        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Users");

        signUpButton = findViewById(R.id.sign_up_activity_button);
        addPassWord = findViewById(R.id.user_password);
        addEmail = findViewById(R.id.email);
        addPhone = findViewById(R.id.phone);
        addUserName = findViewById(R.id.username);

        addEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (addEmail.getText().toString().trim().matches(emailPattern) && addEmail.getText().toString().trim().length() > 0) {

                } else {
                    addEmail.setError("Invalid Email Address!");
                }

            }
        });



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password = addPassWord.getText().toString();
                final String email = addEmail.getText().toString();
                final String phone = addPhone.getText().toString();
                final String userName = addUserName.getText().toString();

                if (email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Cannot leave empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                createAccount(userName, email, password, phone);


                Intent intent = new Intent(SignUpActivity.this, LogSignInActivity.class);
                startActivity(intent);


            }
        });

    }

    private void createAccount(final String username, final String email, final String password, final String phone) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    final FirebaseUser user = mAuth.getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                setUserInfo(user.getUid(), username, email, phone);

                                Toast.makeText(SignUpActivity.this, "Sign up successfully!",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignUpActivity.this, LogSignInActivity.class);
                                startActivity(intent);
                            }
                        }
                    });


                } else {
                    Toast.makeText(SignUpActivity.this, "Email already exists",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUserInfo(String userId, String username, String email, String phone) {
        User user = new User(email, username, phone, userId);
        db.collection("Users").document(username).set(user);

    }

}