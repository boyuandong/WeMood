package com.example.wemood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMoodActivity extends AppCompatActivity {
    User user;
    Mood mood;
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Users");

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        userList = new ArrayList<>();
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                userList.clear();
                for (QueryDocumentSnapshot doc: queryDocumentSnapshots){
                    //Log.d(TAG, String.valueOf(doc.getData().get("password")));
                    String name = doc.getId();
                    String password = (String) doc.getData().get("password");
                    userList.add(new User(name,password));
                }
            }
        });


        Button Add = findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText exp = findViewById(R.id.explanation);
                String explanation = exp.getText().toString();
                String name = user.getName();
                mood = new Mood("2019", "11:00", "happy", explanation, "alone", "Cameron");
                user.addMood(mood);
                HashMap<String, String> data = new HashMap<>();

                for (int i = 0; i < userList.size(); i++){

                    if(name.equals(userList.get(i).getName()) && explanation.length() > 0){
                        data.put(user.getPassword(),mood.toString());
                        collectionReference
                                .document(name)
                                .set(data);
                    }
                }
                finish();
            }
        });

    }
}
