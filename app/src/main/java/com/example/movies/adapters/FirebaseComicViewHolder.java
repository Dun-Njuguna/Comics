package com.example.movies.adapters;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies.Constants;
import com.example.movies.R;
import com.example.movies.models.Comics;
import com.example.movies.ui.DetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseComicViewHolder extends RecyclerView.ViewHolder  {
    public  ImageView mComicImageView;
    View mView;
    Context mContext;

    public FirebaseComicViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
    }

    public void bindComics(Comics comics) {
        mComicImageView = (ImageView) mView.findViewById(R.id.comicImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.comicTitleView);
        TextView description = (TextView) mView.findViewById(R.id.descriptionTextView);

        Picasso.get().load(comics.getThumbnailUrl()).into(mComicImageView);

        titleTextView.setText(comics.getTitle());
        description.setText(comics.getDescription());
    }

//      @Override
//      public void onClick(View view) {
//
//          final ArrayList<Comics> comics = new ArrayList<>();
//          DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_Comic_Save);
//          ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//              @Override
//              public void onDataChange(DataSnapshot dataSnapshot) {
//                  for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                      comics.add(snapshot.getValue(Comics.class));
//                  }
//
//                  int itemPosition = getLayoutPosition();
//
//                  Intent intent = new Intent(mContext, DetailActivity.class);
//                  intent.putExtra("position", itemPosition + "");
//                  intent.putExtra("comics", Parcels.wrap(comics));
//
//                  mContext.startActivity(intent);
//              }
//
//              @Override
//              public void onCancelled(DatabaseError databaseError) {
//              }
//          });
//
//      };

}

