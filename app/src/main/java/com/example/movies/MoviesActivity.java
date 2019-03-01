package com.example.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity {

    @BindView(R.id.searchTextView) TextView mSearchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("text");
        mSearchTextView.setText("Search result for: " + search);
    }
}
