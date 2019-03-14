package com.example.movies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.movies.Constants;
import com.example.movies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedComicReference;
    private ValueEventListener mSearchedComicReferenceListener;


    @BindView(R.id.saved) Button mSavedComicsButton;
    @BindView(R.id.search) Button search;

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.image9, R.drawable.ml, R.drawable.images12};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedComicReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_COMIC);
//        mSearchedComicReferenceListener = mSearchedComicReference.addValueEventListener(new ValueEventListener() { //attach listener
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
//                for (DataSnapshot comicSnapshot : dataSnapshot.getChildren()) {
//                    String comic = comicSnapshot.getValue().toString();
//                    Log.d("Comic updated", "comic: " + comic); //log
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
//
//            }
//        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        search.setOnClickListener(this);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        mSavedComicsButton.setOnClickListener(this);


    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedComicReference.removeEventListener(mSearchedComicReferenceListener);
//    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };



    @Override
    public void onClick(View v) {
        if(v == search) {
            Intent intent = new Intent(MainActivity.this, ComicsActivity.class);
            startActivity(intent);
        }

        if (v == mSavedComicsButton) {
            Intent intent = new Intent(MainActivity.this, SavedComicActivity.class);
            startActivity(intent);
        }
    }

//        private void addToSharedPreferences(String comic) {
//        mEditor.putString(Constants.PREFERENCES_Comic_KEY, comic).apply();
//    }


}
