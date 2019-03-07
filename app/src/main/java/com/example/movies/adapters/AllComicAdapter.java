package com.example.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.models.Comics;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllComicAdapter extends RecyclerView.Adapter<AllComicAdapter.AllComicViewHolder> {
    private ArrayList<Comics> mComics = new ArrayList<>();
    private Context mContext;

    public AllComicAdapter(Context context, ArrayList<Comics> comics) {
        mContext = context;
        mComics = comics;
    }

    @Override
    public AllComicAdapter.AllComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comics_list_item, parent, false);
        AllComicViewHolder viewHolder = new AllComicViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllComicAdapter.AllComicViewHolder holder, int position) {
        holder.bindRestaurant(mComics.get(position));
    }

    @Override
    public int getItemCount() {
        return mComics.size();
    }

    public class AllComicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public AllComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRestaurant(Comics comics) {
            mNameTextView.setText(comics.getTitle());
            mCategoryTextView.setText(comics.getDescription());
            mRatingTextView.setText("Issue Number: " + comics.getIssueNumber());
        }
    }
}