package com.machinser.portfolio.models;

import java.util.Date;

/**
 * Created by asnim on 10/06/17.
 */

public class FeedBack {
    public  String feedback_subject;

    public FeedBack(String compaint_subject, String complaint_user, String complaint_body) {
        this.feedback_subject = compaint_subject;
        this.feedback_user = complaint_user;
        this.feedback_body = complaint_body;
        this.date_created = new Date().toString();
        this.has_read = false;
    }

    public String getCompaint_subject() {
        return feedback_subject;
    }

    public void setCompaint_subject(String compaint_subject) {
        this.feedback_subject = compaint_subject;
    }

    public String getComplaint_user() {
        return feedback_user;
    }

    public void setComplaint_user(String complaint_user) {
        this.feedback_user = complaint_user;
    }

    public String getComplaint_body() {
        return feedback_body;
    }

    public void setComplaint_body(String complaint_body) {
        this.feedback_body = complaint_body;
    }

    public  String feedback_user;
    public  String feedback_body;
    public  String date_created;
    public boolean has_read;

}
