package com.example.wemood.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.wemood.LogSignInActivity;
import com.example.wemood.MainActivity;
import com.example.wemood.R;

public class ProfileFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;

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
        TextView nickname = rootView.findViewById(R.id.nickname);
        EditText firstname = rootView.findViewById(R.id.firstname);
        EditText lastname = rootView.findViewById(R.id.lastname);
        EditText email = rootView.findViewById(R.id.email);
        EditText phone = rootView.findViewById(R.id.phone);
        Button history = rootView.findViewById(R.id.history);
        RadioButton logout = rootView.findViewById(R.id.logout);

        int num_moods = MainActivity.user.getMyMoodList().size();
        moods.setText("Moods\n"+String.valueOf(num_moods));
        int num_followers = MainActivity.user.getFriendList().size() - MainActivity.user.getRefusedList().size();
        followers.setText("Followers\n"+String.valueOf(num_followers));
        int num_following = MainActivity.user.getFriendList().size();
        following.setText("Following\n"+String.valueOf(num_following));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogSignInActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
