package com.example.movies.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.models.Comics;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicDetailFragment extends Fragment {
    @BindView(R.id.comic1ImageView) ImageView mImageLabel;
    @BindView(R.id.comicNameTextView) TextView mNameLabel;
    @BindView(R.id.descriptionTextView) TextView mCategoriesLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveComicButton) TextView mSaveRestaurantButton;

    private Comics mComic;

    public static ComicDetailFragment newInstance(Comics comics) {
        ComicDetailFragment comicDetailFragment = new ComicDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("comics", Parcels.wrap(comics));
        comicDetailFragment.setArguments(args);
        return comicDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComic = Parcels.unwrap(getArguments().getParcelable("comics"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mComic.getmThumbnailUrl()).into(mImageLabel);

        mNameLabel.setText(mComic.getTitle()));
        mCategoriesLabel.setText(mComic.getDescription());
//        mPhoneLabel.setText(mRestaurant.getPhone());
//        mAddressLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getAddress()));

        return view;
    }
}
