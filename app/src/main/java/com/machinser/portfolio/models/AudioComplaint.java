package com.machinser.portfolio.models;

import com.google.firebase.database.ServerValue;

import java.util.Date;

/**
 * Created by asnim on 11/09/17.
 */

public class AudioComplaint {

    public String audio_path;
    public boolean has_seen;
    public boolean has_played;
    public Object date_created;
    public long file_size;


    public AudioComplaint(String audio_path,long file_size) {
        this.audio_path = audio_path;
        this.has_played = false;
        this.has_seen = false;
        this.file_size = file_size;
        this.date_created = ServerValue.TIMESTAMP;
    }

    public AudioComplaint() {

    }
}
