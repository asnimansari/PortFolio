package com.machinser.portfolio.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.machinser.portfolio.R;
import com.machinser.portfolio.models.Feed;
import com.machinser.portfolio.utils.FeedAdapter;
import com.machinser.portfolio.utils.FireBaseUtilities;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asnim on 09/06/17.
 */

public class NewsFeed extends Fragment {
//    ImageView banner;
//    WebView about_person_view;
    FirebaseDatabase database ;
    private DatabaseReference mDatabase;
    private ArrayList<Feed> feedArrayList;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FireBaseUtilities.getDatabase();
        mDatabase = database.getReference().child("feed");

        feedArrayList = new ArrayList<>();
        mDatabase.keepSynced(true);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                processnodes((Map<String,Object>) dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_feed,container,false);
    }
    private void processnodes(Map<String,Object> donors) {

//        String[] phone_nos = new String[3];
        for (Map.Entry<String, Object> entry : donors.entrySet()) {
            Map singleFeed = (Map) entry.getValue();
            Feed feed = new Feed(singleFeed);
            feedArrayList.add(feed);
        }
        displayprocessedfeed();
    }

    private void displayprocessedfeed() {
        if(feedArrayList!=null){
//            FeedAdapter fee
        }
    }
}
