package com.example.wemood.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.wemood.R;

public class FriendsNotExistFragment extends Fragment {
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;

    public FriendsNotExistFragment() {
        // Required empty public constructor
    }

    public static FriendsNotExistFragment newInstance(String param1) {
        FriendsNotExistFragment fragment = new FriendsNotExistFragment();
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
        View rootView = inflater.inflate(R.layout.friend_locked_page, container, false);
//        TextView contentTv = rootView.findViewById(R.id.content_tv);
//        contentTv.setText(mContentText);

        final Button cancelButton = rootView.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FriendsFragment friendsFragment = new FriendsFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, friendsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
