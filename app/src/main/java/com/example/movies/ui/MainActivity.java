package com.example.movies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.movies.Constants;
import com.example.movies.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.search) Button search;
    @BindView(R.id.searchtext) EditText title;

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.image9, R.drawable.ml, R.drawable.images12};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        search.setOnClickListener(this);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void addToSharedPreferences(String comic) {
        mEditor.putString(Constants.PREFERENCES_Comic_KEY, comic).apply();
    }

    @Override
    public void onClick(View v) {
        if(v == search) {
            String titleSearch = title.getText().toString();
            if(!(titleSearch).equals("")) {
                addToSharedPreferences(titleSearch);
            }
            Intent intent = new Intent(MainActivity.this, ComicsActivity.class);
//            intent.putExtra("titleSearch", titleSearch);
            startActivity(intent);
        }
    }

}
