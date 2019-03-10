package com.example.movies.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.movies.models.Comics;
import com.example.movies.ui.ComicDetailFragment;

import java.util.ArrayList;


public class ComicPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Comics> mComics;

    public ComicPagerAdapter(FragmentManager fm, ArrayList<Comics> comics) {
        super(fm);
        mComics = comics;
    }

    @Override
    public Fragment getItem(int position) {
        return ComicDetailFragment.newInstance(mComics.get(position));
    }

    @Override
    public int getCount() {
        return mComics.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mComics.get(position).getTitle();
    }
}