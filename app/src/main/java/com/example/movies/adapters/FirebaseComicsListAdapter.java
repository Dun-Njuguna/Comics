package com.example.movies.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.R;
import com.example.movies.models.Comics;
import com.example.movies.ui.DetailActivity;
import com.example.movies.util.ItemTouchHelperAdapter;
import com.example.movies.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseComicsListAdapter extends FirebaseRecyclerAdapter<Comics, FirebaseComicViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    private ChildEventListener mChildEventListener;
    private ArrayList<Comics> mComics = new ArrayList<>();

    public FirebaseComicsListAdapter(FirebaseRecyclerOptions<Comics> options, Query ref, OnStartDragListener onStartDragListener, Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mComics.add(dataSnapshot.getValue(Comics.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onBindViewHolder(final FirebaseComicViewHolder viewHolder, int position, Comics model) {
        viewHolder.bindComics(model);
        viewHolder.mComicImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("comics", Parcels.wrap(mComics));
                mContext.startActivity(intent);
            }
        });
    }


    @NonNull
    @Override
    public FirebaseComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_list_item_drag, parent, false);
        return new FirebaseComicViewHolder(view);
    }


//    @Override
//    protected void onBindViewHolder(final FirebaseComicViewHolder viewHolder, int position,Comics model) {
//        viewHolder.bindComics(model);
//        viewHolder.mComicImageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
//                    mOnStartDragListener.onStartDrag(viewHolder);
//                }
//                return false;
//            }
//        });
//    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mComics, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mComics.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Comics comics : mComics) {
            int index = mComics.indexOf(comics);
            DatabaseReference ref = getRef(index);
            comics.setIndex(Integer.toString(index));
            ref.setValue(comics);
        }
    }


}