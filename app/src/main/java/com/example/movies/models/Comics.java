package com.example.movies.models;

import org.parceler.Parcel;

@Parcel
public class Comics {

    String title;
    String issueNumber;
    String description;
    String thumbnailUrl;
    private String pushId;
    String index;
    public Comics(){}

    public Comics (String title, String issueNumber, String description, String thumbnailUrl){
        this.title = title;
        this.issueNumber = issueNumber;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.index = "not_specified";
    }

    public String getTitle() {
        return title;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
