package com.example.movies.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.adapters.AllComicAdapter;
import com.example.movies.models.Comics;
import com.example.movies.services.MarvelService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {

    public static final String TAG = MoviesActivity.class.getSimpleName();
    private String[] restaurants = new String[]{};


    public ArrayList<Comics> coomics = new ArrayList<>();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private AllComicAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("titleSearch");
//        mSearchTextView.setText("Search result for: " + search);

        getComics(search);
    }

    private void getComics(String search) {
        final MarvelService marvelService = new MarvelService();
        marvelService.findcomics(search, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                coomics = marvelService.processResults(response);

                MoviesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AllComicAdapter(getApplicationContext(), coomics);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MoviesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                    }
                });
            }

        });
    }
}
