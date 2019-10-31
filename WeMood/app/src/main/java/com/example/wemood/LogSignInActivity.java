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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import io.opencensus.tags.Tag;

public class LogSignInActivity extends AppCompatActivity {
    ArrayList<User> userList;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign_in);

        final String TAG = "Sample";
        Button signUpButton;
        Button signInButton;
        final EditText addUserName;
        final EditText addPassWord;

        FirebaseFirestore db;

        signUpButton = findViewById(R.id.sign_up_button);
        signInButton = findViewById(R.id.sign_in_button);
        addUserName = findViewById(R.id.add_user_name);
        addPassWord = findViewById(R.id.add_user_password);

        userList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        final CollectionReference collectionReference = db.collection("Users");

        //to get the whole userList while start the app and to check whether username and password matched
        //under sign in button


        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                userList.clear();
                for (QueryDocumentSnapshot doc: queryDocumentSnapshots){
                    Log.d(TAG, String.valueOf(doc.getData().get("password")));
                    String name = doc.getId();
                    String password = (String) doc.getData().get("password");
                    userList.add(new User(name,password));
                }
            }
        });

        //to get new user registery info(name and password and store into firestore
        //after click sign up button, empty editText and jump into main activity
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = addUserName.getText().toString();
                final String password = addPassWord.getText().toString();

                HashMap<String, String> data = new HashMap<>();

                if (name.length() > 0 && password.length() > 0){
                    data.put("password", password);

                    collectionReference
                            .document(name)
                            .set(data);
                    addUserName.setText("");
                    addPassWord.setText("");

                    /*collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            userList.clear();
                            for (QueryDocumentSnapshot doc: queryDocumentSnapshots){
                                Log.d(TAG, String.valueOf(doc.getData().get("password")));
                                String name = doc.getId();
                                String password = (String) doc.getData().get("password");
                                userList.add(new User(name,password));
                            }
                        }
                    });*/
                }

                Intent intent = new Intent(LogSignInActivity.this, MainActivity.class);
                user = new User(name, password);
                intent.putExtra("user", user);
                startActivity(intent);
            }


        });

        //under this sign in button we verify the username and password
        //if doesn't match we show error message else match we jump to main activity

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = addUserName.getText().toString();
                final String password = addPassWord.getText().toString();
                //do something
                    for (int i = 0; i < userList.size(); i++){
                        Log.d("name", userList.get(i).getName());
                        Log.d("name",name);
                        Log.d("name",userList.get(i).getPassword());
                        Log.d("name",password);
                        if(name.equals(userList.get(i).getName())   && password.equals(userList.get(i).getPassword())  ){
                            Intent intent = new Intent(LogSignInActivity.this,MainActivity.class);
                            startActivity(intent);

                        }
                    }

            }
        });
        //Log.d("myTag", "This is my message");
    }
}
