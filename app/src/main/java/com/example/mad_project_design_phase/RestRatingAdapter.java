package com.example.mad_project_design_phase;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.annotations.NotNull;

public class RestRatingAdapter extends FirebaseRecyclerAdapter<restRatingModel,RestRatingAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public RestRatingAdapter(@NonNull FirebaseRecyclerOptions<restRatingModel> options) {
        super(options);
    }

    protected void onBindViewHolder(@NotNull RestRatingAdapter.myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NotNull restRatingModel model) {

        holder.name.setText(model.getName());
        holder.review.setText(model.getReview());
        holder.noOfstars.setText(model.getNoOfStars());
        Glide.with(holder.userImage.getContext())
                .load(model.getUserImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.userImage);

       Log.i("dddddddd", "onBindViewHolder: fsdf");
    }


    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restratingscard,parent,false);
        return new myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        TextView name,review,noOfstars;
        ImageView userImage;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userName);
            review = itemView.findViewById(R.id.rateItem_review);
            noOfstars = itemView.findViewById(R.id.rate_rating);
            userImage = itemView.findViewById(R.id.profile_image);

            Log.i("dddddddd", "onBindViewHolder: fsdf");


        }
    }


}