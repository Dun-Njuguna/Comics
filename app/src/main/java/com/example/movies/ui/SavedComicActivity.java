package com.example.movies.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.Constants;
import com.example.movies.R;
import com.example.movies.adapters.FirebaseComicViewHolder;
import com.example.movies.models.Comics;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedComicActivity extends AppCompatActivity {

    private DatabaseReference mComicReference;
    private FirebaseRecyclerAdapter<Comics, FirebaseComicViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        mComicReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_Comic_Save);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Comics> options =
                new FirebaseRecyclerOptions.Builder<Comics>()
                        .setQuery(mComicReference, Comics.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Comics, FirebaseComicViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseComicViewHolder firebaseComicViewHolder, int position, @NonNull Comics comics) {
                firebaseComicViewHolder.bindComics(comics);
            }

            @NonNull
            @Override
            public FirebaseComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_list_item_drag, parent, false);
                return new FirebaseComicViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}
