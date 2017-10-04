package com.machinser.portfolio.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.machinser.portfolio.R;
import com.machinser.portfolio.models.Feed;
import com.machinser.portfolio.utils.FeedAdapter;
import com.machinser.portfolio.utils.FireBaseUtilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by asnim on 09/06/17.
 */

public class NewsFeed extends Fragment {
    private String TAG = "NEWS_FEED";


    FirebaseDatabase database ;
    private DatabaseReference mDatabase;
    private ArrayList<Feed> feedArrayList;
    private RecyclerView lv;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (RecyclerView) view.findViewById(R.id.lv);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FireBaseUtilities.getDatabase();
        mDatabase = database.getReference().child("feed");



        feedArrayList = new ArrayList<>();
        mDatabase.keepSynced(true);
        mDatabase.orderByChild("date_created");
        mDatabase.limitToLast(1);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();

                Feed individual_news_feed;// = new Feed();
                feedArrayList.clear();

                for (DataSnapshot dataSnapshot1 :dataSnapshots){
                    individual_news_feed = dataSnapshot1.getValue(Feed.class);
                    feedArrayList.add(individual_news_feed);
                    Log.e(TAG,individual_news_feed.event_title);
                }

                Collections.reverse(feedArrayList);


                FeedAdapter feedAdapter = new FeedAdapter(feedArrayList,getContext(),getFragmentManager());
                lv.setAdapter(feedAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_layout,container,false);
    }

}
