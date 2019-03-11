package com.example.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.movies.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

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

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onClick(View v) {
        if(v == search) {
            String titleSearch = title.getText().toString();
            Intent intent = new Intent(MainActivity.this, ComicsActivity.class);
            intent.putExtra("titleSearch", titleSearch);
            startActivity(intent);
        }
    }

}
