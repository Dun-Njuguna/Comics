package com.example.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.models.Comics;
import com.example.movies.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
        holder.bindComics(mComics.get(position));
    }

    @Override
    public int getItemCount() {
        return mComics.size();
    }

    public class AllComicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.comicImageView) ImageView mComicImageView;
        @BindView(R.id.comicTitleView) TextView mNameTitleView;
        @BindView(R.id.descriptionTextView) TextView mDescriptionTextView;
        @BindView(R.id.issueNumberTextView) TextView mIssueNumberTextView;
        private Context mContext;

        public AllComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("comics", Parcels.wrap(mComics));
            mContext.startActivity(intent);
        }

        public void bindComics(Comics comics) {
            Picasso.get().load(comics.getmThumbnailUrl()).fit().into(mComicImageView);
            mNameTitleView.setText(comics.getTitle());
            String description = comics.getDescription();
            if(description.equals("null")){
                mDescriptionTextView.setText("Description not available");
            }else{
                mDescriptionTextView.setText(description);
            }
            mIssueNumberTextView.setText("Issue Number: " + comics.getIssueNumber());
        }
    }
}