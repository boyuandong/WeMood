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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    Button signUpButton;
    Button signInButton;
    EditText addUserName;
    EditText addPassWord;
    CollectionReference collectionReference;
    ArrayList<User> userList;
    List<String> userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign_in);

        FirebaseFirestore db;

        signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setX(200);
        signUpButton.setY(500);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setX(950);
        signInButton.setY(330);
        addUserName = findViewById(R.id.add_user_name);
        addPassWord = findViewById(R.id.add_user_password);

        userList = new ArrayList<>();

        userInfo = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        collectionReference = db.collection("Users");

        //to get the whole userList while start the app and to check whether username and password matched
        //under sign in button

        /*db.collection("Users").document("1")

                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });*/



        //to get new user registery info(name and password and store into firestore
        //after click sign up button, empty editText and jump into main activity


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogSignInActivity.this, SignUpActivity.class);
                startActivity(intent);

            }


        });

        //under this sign in button we verify the username and password
        //if doesn't match we show error message else match we jump to main activity

        /*signInButton.setOnClickListener(new View.OnClickListener() {
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
        });*/

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = addUserName.getText().toString();
                final String password = addPassWord.getText().toString();

                collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for(QueryDocumentSnapshot doc: queryDocumentSnapshots){
                            final String userName = doc.getId();
                            if(userName.equals(name)){
                                Log.d("name",name);
                                Log.d("name",userName);

                                Map<String,String> userInfo = (Map<String,String>) doc.getData().get("userInfo");
                                if (userInfo.get("password").equals(password)){
                                    collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                            userList.clear();
                                            for (QueryDocumentSnapshot doc: queryDocumentSnapshots){
                                                //Log.d(TAG, String.valueOf(doc.getData().get("password")));
                                                String name = doc.getId();
                                                //String password = (String) doc.getData().get("password");
                                                if (userName.equals(name)){
                                                    Map<String,String> userInfo = (Map<String,String>) doc.getData().get("userInfo");
                                                    //Log.d("name1", user_attribute.get(0));
                                                    String password = userInfo.get("password");
                                                    String firstName = userInfo.get("firstName");
                                                    String lastName = userInfo.get("lastName");
                                                    String email = userInfo.get("email");
                                                    String phone = userInfo.get("phone");
                                                    User user = new User(name,password,firstName,lastName,email,phone);
                                                    Intent intent = new Intent(LogSignInActivity.this,MainActivity.class);
                                                    intent.putExtra("User",user);
                                                    startActivity(intent);
                                                }

                                            }

                                        }
                                    });


                                }else {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Wrong Password",
                                            Toast.LENGTH_SHORT);

                                    toast.show();

                                }
                            }


                        }
                    }
                });


            }
        });



    }


}
