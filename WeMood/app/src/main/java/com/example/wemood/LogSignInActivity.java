package com.example.wemood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.opencensus.tags.Tag;

public class LogSignInActivity extends AppCompatActivity {


    final String TAG = "Sample";
    private Button signUpButton;
    private Button signInButton;
    private EditText addEmail;
    private EditText addPassWord;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign_in);


        signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setX(200);
        signUpButton.setY(500);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setX(950);
        signInButton.setY(330);
        addEmail = findViewById(R.id.add_user_name);
        addPassWord = findViewById(R.id.add_user_password);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LogSignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogSignInActivity.this, SignUpActivity.class);
                startActivity(intent);

            }


        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = addEmail.getText().toString();
                final String password = addPassWord.getText().toString();

                // get email and password and send it to fireBase Auth
                if ((!email.isEmpty()) && (!password.isEmpty())) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                        }
                    }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LogSignInActivity.this, "Sign in successfully!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LogSignInActivity.this, "Sign in failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    mAuthListener = new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                Intent intent = new Intent(LogSignInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    };


                } else {
                    Toast.makeText(LogSignInActivity.this, "Please check your input info",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);

    }

    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void setupFirebaseAuth() {


    }


}
