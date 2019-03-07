package com.example.movies.models;

import java.util.ArrayList;

public class Comics {

    private String title;
    private String issueNumber;
    private String description;
    private String mThumbnailUrl;

    public Comics (String title, String issueNumber, String description, String mThumbnailUrl){
        this.title = title;
        this.issueNumber = issueNumber;
        this.description = description;
        this.mThumbnailUrl = mThumbnailUrl;

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

    public String getmThumbnailUrl() {
        return mThumbnailUrl;
    }

}
