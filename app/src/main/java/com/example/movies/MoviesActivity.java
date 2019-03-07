package com.example.movies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


    private ListView mListView;
    public ArrayList<Comics> mComics = new ArrayList<>();

    @BindView(R.id.searchTextView)
    TextView mSearchTextView;

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
                String restaurant = ((TextView) view).getText().toString();
                Toast.makeText(MoviesActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String search = intent.getStringExtra("titleSearch");
        mSearchTextView.setText("Search result for: " + search);

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

                mComics = marvelService.processResults(response);
                MoviesActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        String[] comicTitles = new String[mComics.size()];
                        for (int i = 0; i < comicTitles.length; i++) {
                            comicTitles[i] = mComics.get(i).getTitle();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(MoviesActivity.this,
                                android.R.layout.simple_list_item_1, comicTitles);
                        mListView.setAdapter(adapter);
                    }

                });

                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
//                        mComics = marvelService.processResults(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
