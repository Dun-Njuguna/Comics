package com.example.movies.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.Constants;
import com.example.movies.R;
import com.example.movies.models.Comics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.comic1ImageView) ImageView mImageLabel;
    @BindView(R.id.comicNameTextView) TextView mNameLabel;
    @BindView(R.id.descriptionTextView) TextView mCategoriesLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.saveComicButton) TextView mSaveComicButton;

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
        mPhoneLabel.setOnClickListener(this);
        mSaveComicButton.setOnClickListener(this);
        Picasso.get().load(mComic.getThumbnailUrl()).into(mImageLabel);
        mNameLabel.setText(mComic.getTitle());
        String description = mComic.getDescription();
        if(description.equals("null")){
            mCategoriesLabel.setText("Description not available");
        }else{
            mCategoriesLabel.setText(description);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + "(503) 223-1282"));
            startActivity(phoneIntent);
        }
        if (v == mSaveComicButton) {
            DatabaseReference comicRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_Comic_Save);
            comicRef.push().setValue(mComic);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
