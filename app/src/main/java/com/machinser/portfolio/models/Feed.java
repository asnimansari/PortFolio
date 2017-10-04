package com.machinser.portfolio.models;

import android.util.Log;

import com.google.firebase.database.ServerValue;
import com.machinser.portfolio.utils.FeedTypes;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by asnim on 11/06/17.
 */

public class Feed {


    public String event_title;
    public String event_body;
    public int event_category;
    public Object date_created;
    public String date_string;



    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }
    public String event_image;


    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_body() {
        return event_body;
    }

    public void setEvent_body(String event_body) {
        this.event_body = event_body;
    }
    public Feed(String event_title,String event_body){
        this.event_body = event_body;
        this.event_category = FeedTypes.NEWS;
        this.event_title =  event_title;
        this.date_created = ServerValue.TIMESTAMP;
        this.event_image = null;
        this.date_string = new Date().toString();

    }public Feed(String event_title,String event_body,int event_category){
        this.event_body = event_body;
        this.event_category = event_category;
        this.event_title =  event_title;
        this.date_created = ServerValue.TIMESTAMP;
        this.event_image = null;
        this.date_string = new Date().toString();

    }
    public Feed(){

    }

    public  String getDisplayDateString() {
        if (this.date_string == null){
            return null;
        }

        Log.e("date string",this.date_string);

        Date past = new Date(this.date_string);
        Date now = new Date();
        try {
            long milliseconds = TimeUnit.MILLISECONDS.toMillis(now.getTime() - past.getTime());
            long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
            long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
            long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());

            if(days>0){
                return days + " days";
            }else if(hours > 0){
                return hours + " hours";
            }
            else if(minutes>0){
                return minutes + "mins";
            }
            else{
                return "Just Now";
            }
        }
        catch (Exception j){
            j.printStackTrace();
        }
        return "";
    }


}
