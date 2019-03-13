package com.example.movies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.movies.Constants;
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

public class ComicsActivity extends AppCompatActivity {

    public static final String TAG = ComicsActivity.class.getSimpleName();
    private String[] restaurants = new String[]{};


    public ArrayList<Comics> coomics = new ArrayList<>();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private AllComicAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private String mComicSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("titleSearch");
//        mSearchTextView.setText("Search result for: " + search);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mComicSearch = mSharedPreferences.getString(Constants.PREFERENCES_Comic_KEY, null);
        Log.d("Comic search ", mComicSearch);
        if (mComicSearch != null) {
            getComics(mComicSearch);
        }
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

                ComicsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AllComicAdapter(getApplicationContext(), coomics);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ComicsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                    }
                });
            }

        });
    }
}
