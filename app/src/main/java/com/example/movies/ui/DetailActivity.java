package com.example.movies.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.movies.R;
import com.example.movies.adapters.ComicPagerAdapter;
import com.example.movies.models.Comics;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private ComicPagerAdapter adapterViewPager;
    ArrayList<Comics> mComics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mComics = Parcels.unwrap(getIntent().getParcelableExtra("comics"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ComicPagerAdapter(getSupportFragmentManager(), mComics);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
