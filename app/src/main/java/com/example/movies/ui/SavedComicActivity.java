package com.example.movies.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.Constants;
import com.example.movies.R;
import com.example.movies.adapters.FirebaseComicViewHolder;
import com.example.movies.adapters.FirebaseComicsListAdapter;
import com.example.movies.models.Comics;
import com.example.movies.util.OnStartDragListener;
import com.example.movies.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedComicActivity extends AppCompatActivity implements OnStartDragListener {

//    private DatabaseReference mComicReference;
//    private FirebaseRecyclerAdapter<Comics, FirebaseComicViewHolder> mFirebaseAdapter;
//    private ItemTouchHelper mItemTouchHelper;

    private DatabaseReference mComicReference;
    private FirebaseComicsListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    public static final String TAG = SavedComicActivity.class.getSimpleName();



    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

//        mComicReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_Comic_Save);
        setUpFirebaseAdapter();
    }

//    private void setUpFirebaseAdapter(){
//        FirebaseRecyclerOptions<Comics> options =
//                new FirebaseRecyclerOptions.Builder<Comics>()
//                        .setQuery(mComicReference, Comics.class)
//                        .build();
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Comics, FirebaseComicViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull FirebaseComicViewHolder firebaseComicViewHolder, int position, @NonNull Comics comics) {
//                firebaseComicViewHolder.bindComics(comics);
//            }
//
//            @NonNull
//            @Override
//            public FirebaseComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_list_item_drag, parent, false);
//                return new FirebaseComicViewHolder(view);
//            }
//        };
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Log.v(TAG, "Response " + uid);
        mComicReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_Comic_Save );
        Log.v(TAG, "ssssss " + mComicReference);
        FirebaseRecyclerOptions<Comics> options =
                new FirebaseRecyclerOptions.Builder<Comics>()
                        .setQuery(mComicReference, Comics.class)
                        .build();

        mFirebaseAdapter = new FirebaseComicsListAdapter(options, mComicReference, this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
}
