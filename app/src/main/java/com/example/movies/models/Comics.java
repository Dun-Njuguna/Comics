package com.example.movies.models;

import org.parceler.Parcel;

@Parcel
public class Comics {

    String title;
    String issueNumber;
    String description;
    String thumbnailUrl;

    public Comics(){}

    public Comics (String title, String issueNumber, String description, String thumbnailUrl){
        this.title = title;
        this.issueNumber = issueNumber;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;

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

}
