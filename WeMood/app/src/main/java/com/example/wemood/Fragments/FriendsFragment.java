package com.example.wemood.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.wemood.FriendNameList;
import com.example.wemood.R;
//import com.example.wemood.ShowExistFriend;
import com.example.wemood.User;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;

    ListView friendList;
    ArrayAdapter<User> friendAdapter;
    ArrayList<User> frienddataList;

    public FriendsFragment() {
        // Required empty public constructor
    }

    public static FriendsFragment newInstance(String param1) {
        FriendsFragment fragment = new FriendsFragment();
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
        View rootView = inflater.inflate(R.layout.friend_content, container, false);
//        TextView contentTv = rootView.findViewById(R.id.content_tv);
//        contentTv.setText(mContentText);

        friendList = (ListView) rootView.findViewById(R.id.friend_list_content);
        frienddataList = new ArrayList<>();

//        frienddataList.add(new User("Alpha","passward1"));
        frienddataList.add(new User("Zoey","passward1"));
//        frienddataList.add(new User("Boyuan","passward1"));
//        frienddataList.add(new User("Zuhao","passward1"));
//        frienddataList.add(new User("Ziyi","passward1"));
//        frienddataList.add(new User("Ruochen","passward1"));
//        frienddataList.add(new User("Willy","passward1"));
//        frienddataList.add(new User("Cherry","passward1"));
//        frienddataList.add(new User("Rose","passward1"));
//        frienddataList.add(new User("Candy","passward1"));
//        frienddataList.add(new User("Apple","passward1"));

        friendAdapter = new FriendNameList(getActivity(), frienddataList);
        friendList.setAdapter(friendAdapter);

        final Button searchButton = rootView.findViewById(R.id.friend_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FriendsNotExistFragment friendsNotExitsFragment = new FriendsNotExistFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, friendsNotExitsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendsExistFragment friendsExitsFragment = new FriendsExistFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, friendsExitsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
