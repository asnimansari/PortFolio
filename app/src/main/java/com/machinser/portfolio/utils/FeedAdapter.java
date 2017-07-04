package com.machinser.portfolio.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.machinser.portfolio.models.Feed;
import com.machinser.portfolio.R;
import com.machinser.portfolio.models.FeedBack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import com.machinser.portfolioadmin.models.News;

/**
 * Created by asnim on 26/05/17.
 */

public class FeedAdapter extends ArrayAdapter<Feed> {
    Context context;
    int layoutResourseID;
    ArrayList<Feed> data  = null;

    public FeedAdapter(Context context, int layoutResourceId, ArrayList<Feed> data){
        super(context,layoutResourceId,data);
        data = new ArrayList<>();
        this.layoutResourseID = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View row = convertView;
        FeedBackHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourseID,parent,false);
            holder = new FeedBackHolder();
            holder.feed_body = (TextView) row.findViewById(R.id.feed_body);
            holder.feed_title =  (TextView) row.findViewById(R.id.feed_title);
            holder.feed_image =  (ImageView) row.findViewById(R.id.feed_image);
            row.setTag(holder);

        }
        else{
            holder = (FeedBackHolder)row.getTag();
        }


        Feed feed = getItem(position);

        holder.feed_body.setText(feed.event_body);
        holder.feed_title.setText(feed.event_title);



        if(feed.event_image!=null){
            Picasso.with(getContext()).load(feed.event_image).into(holder.feed_image);

        }

        return  row;
    }


    static class FeedBackHolder{
        TextView feed_body;

        TextView feed_title;
        ImageView feed_image;
;
    }


}
