package com.example.wemood;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FriendsExistMoodList extends ArrayAdapter<Mood> {

    private ArrayList<Mood> moods;
    private Context context;

    public FriendsExistMoodList(Context context, ArrayList<Mood> moods) {
        super(context,0,moods);
        this.moods = moods;
        this.context = context;
    }

    // get the view of the friendmoodlist
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.friend_exist_page_content, parent, false);
        }

        // for each mood get the textview and images by id's
        Mood mood = moods.get(position);

        TextView MoodExplanation = view.findViewById(R.id.mood_explaination);
        TextView MoodDate =view.findViewById(R.id.mood_date);
        TextView MoodTime =view.findViewById(R.id.mood_time);
        TextView MoodLocation =view.findViewById(R.id.mood_location);

        MoodExplanation.setText(mood.getExplanation());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        MoodDate.setText(dateFormat.format(mood.getDatetime().getTime()));
        MoodTime.setText(timeFormat.format(mood.getDatetime().getTime()));
        MoodLocation.setText(mood.getLocation());

        // Classify the moods by different mood states
        // set the background color by different mood states
        String emotionalState = mood.getEmotionalState();
        if (emotionalState == "happy"){
            view.setBackgroundColor(Color.RED);
        }else if (emotionalState == "sad"){
            view.setBackgroundColor(Color.BLUE);
        }
        else if (emotionalState == "tired"){
            view.setBackgroundColor(Color.YELLOW);
        }

        return view;
    }
}
