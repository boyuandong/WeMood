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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        final CollectionReference collectionReference;
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
        collectionReference = db.collection("Users");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, ProfileFragment.class);
                final String new_firstname = firstname.getText().toString();
                final String new_lastname = lastname.getText().toString();
                final String new_email = email.getText().toString();
                final String new_phone = phone.getText().toString();
                collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            final String userName = doc.getId();
                            //System.out.println(userName);
                            //System.out.println(userNAme);
                            //System.out.println("-------------");
                            if (userName.equals(userNAme)) {
                                Map<String, String> userInfo = (Map<String, String>) doc.getData().get("userInfo");
                                //Log.d("name1", user_attribute.get(0));
                                userInfo.put("firstName", new_firstname);
                                userInfo.put("lastName", new_lastname);
                                userInfo.put("email", new_email);
                                userInfo.put("phone", new_phone);
                            }
                        }
                    }
                });
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
}
