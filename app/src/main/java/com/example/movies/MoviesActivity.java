package com.example.movies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity {


    private String[] restaurants = new String[] {"Good Will Hunting (1997)", " Arrival (2016)",
            "Lost In Translation (2003)", "The Princess Bride (1987)", "The Terminator (1984)", "No Country For Old Men (2007)",
            "Predator (1987)", "Indiana Jones And The Last Crusade (1989)", "True Romance (1993)", " The Social Network (2010)",
            "Spirited Away (2001)", " Captain America: Civil War (2016)"};


    private ListView mListView;

    @BindView(R.id.searchTextView) TextView mSearchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        mListView = (ListView) findViewById(R.id.listView);

        Typeface openSans = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        mSearchTextView.setTypeface(openSans);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(MoviesActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String search = intent.getStringExtra("text");
        mSearchTextView.setText("Search result for: " + search);
    }
}
