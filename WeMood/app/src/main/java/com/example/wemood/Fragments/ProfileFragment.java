package com.example.wemood.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wemood.LogSignInActivity;
import com.example.wemood.MyMoodList;
import com.example.wemood.R;
import com.example.wemood.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nonnull;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    private String userName;
    private String userid;
    private String eMail;
    private String pHone;
    private String new_phone;
    private MyMoodList myMoodList;
    private int num_moods;
    private int num_followers;
    private int num_following;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private FirebaseFirestore db;
    private CollectionReference collectionReference;
    private DocumentReference documentReference;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView figure = rootView.findViewById(R.id.figure);
        TextView moods = rootView.findViewById(R.id.moods);
        TextView followers = rootView.findViewById(R.id.followers);
        TextView following = rootView.findViewById(R.id.following);
        TextView username = rootView.findViewById(R.id.username);
        //TextView userID = rootView.findViewById(R.id.userid);
        TextView email = rootView.findViewById(R.id.email);
        EditText phone = rootView.findViewById(R.id.phone);
        Button history = rootView.findViewById(R.id.history);
        RadioButton logout = rootView.findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        userName = user.getDisplayName();
        System.out.println(userName);
        userid = user.getUid();
        eMail = user.getEmail();
        documentReference = db.collection("Users").document(userName);

        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        pHone = user.getPhone();
                        System.out.println(pHone);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }
        });


        //pHone = user.getPhoneNumber();

        username.setText(userName);
        //userID.setText("User ID: " + userid);
        email.setText("Email: " + eMail);
        phone.setText("Phone No.:" + pHone);
        new_phone = phone.getText().toString();

        /*documentReference
                .update("phone", new_phone)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            Log.d(TAG, "DocumentSnapshot successfully updated!");
        }
    })
            .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Log.w(TAG, "Error updating document", e);
        }
    });
*/
    collectionReference = db.collection("Users")
            .document("Users")
                .collection("MoodList");
    num_moods = 0;
        collectionReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    num_moods += 1;
                    Log.d(TAG, document.getId() + " => " + document.getData());
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        }
    });

    myMoodList = new MyMoodList();
        moods.setText("Moods\n"+String.valueOf(num_moods));
    //num_followers = myMoodList.getMyMoods().size();
        followers.setText("Followers\n"+String.valueOf(num_moods));
    //num_following = myMoodList.getMyMoods().size();
        following.setText("Following\n"+String.valueOf(num_moods));

        logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //mAuth.signOut();
            Intent intent = new Intent(getActivity(), LogSignInActivity.class);
            startActivity(intent);
        }
    });

        return rootView;
    }
}
