package com.machinser.portfolio.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.machinser.portfolio.R;
import com.machinser.portfolio.utils.FeedAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by asnim on 24/09/17.
 */

public class DetailedNews extends Fragment {

    String event_title;
    String event_body;
    String event_image;
    private String TAG = "DetailedNews";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            Bundle b = getArguments();

        event_title = b.getString("event_title");
        event_body = b.getString("event_body");
        event_image = b.getString("event_image");
            return  inflater.inflate(R.layout.detailed_news,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView news_detailed_title = (TextView) view.findViewById(R.id.news_detailed_title);
        TextView news_detailed_content = (TextView) view.findViewById(R.id.news_detailed_content);
        final ImageView detailed_news_image = (ImageView)view.findViewById(R.id.detailed_news_image);

        news_detailed_title.setText(event_title);
        news_detailed_content.setText(event_body);

        if (event_image !=null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            Log.e(TAG,"Image "+ event_image);

            Picasso.with(getContext()).load(event_image).placeholder(R.drawable.mini_icon).into(detailed_news_image);

        }
    }
}
