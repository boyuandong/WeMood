package com.example.wemood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wemood.Fragments.ProfileFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        TextView nickname = findViewById(R.id.nickname);
        //TextView username = findViewById(R.id.username);
        final EditText firstname = findViewById(R.id.firstname);
        final EditText lastname = findViewById(R.id.lastname);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        final Button save = findViewById(R.id.save);

        FirebaseFirestore db;
        final DocumentReference documentReference;
        final String TAG = "Sample";

        //String new_nickname = nickname.getText().toString();
        final String userNAme = MainActivity.user.getName();
        nickname.setText(userNAme);

        /*Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickname");
        String userName = intent.getStringExtra("username");
        String firstName = intent.getStringExtra("firstname");
        String lastName = intent.getStringExtra("lastname");
        String Email = intent.getStringExtra("email");
        String Phone = intent.getStringExtra("phone"); */

        db = FirebaseFirestore.getInstance();
        documentReference = db.collection("Users").document(userNAme);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, ProfileFragment.class);
                final String new_firstname = firstname.getText().toString();
                final String new_lastname = lastname.getText().toString();
                final String new_email = email.getText().toString();
                final String new_phone = phone.getText().toString();
                documentReference
                        .update("firstName",new_firstname,
                        "lastName",new_lastname,
                        "email",new_email,
                        "phone",new_phone)
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
                /*
                collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            final String userName = doc.getId();
                            if (userName.equals(userNAme)) {
                                Log.d("name",userNAme);
                                Log.d("name",userName);
                                collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        for (QueryDocumentSnapshot doc: queryDocumentSnapshots){
                                            String name = doc.getId();
                                            if (userName.equals(name)){
                                                Map<String,String> userInfo = (Map<String,String>) doc.getData().get("userInfo");
                                            }
                                            collectionReference
                                                    .document(name)
                                                    .update("firstName", new_firstname,
                                                            "lastName", new_lastname,
                                                            "email", new_email,
                                                            "phone", new_phone)
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
                                        }

                                    }
                                });

                            }
                        }
                    }
                }); */
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
}
