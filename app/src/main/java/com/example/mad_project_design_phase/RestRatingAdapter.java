package com.example.mad_project_design_phase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RestRatingAdapter extends FirebaseRecyclerAdapter<restRatingModel,RestRatingAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RestRatingAdapter(@NonNull @NotNull FirebaseRecyclerOptions<restRatingModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, final int position, @NonNull @NotNull restRatingModel model) {
        holder.userName.setText(model.getName());
        holder.rateRating.setText(model.getNoOfStars());
        holder.rateItem_review.setText(model.getReview());
        Glide.with(holder.profileImage.getContext())
                .load(model.getUserImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.profileImage);

    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restratingscard, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView profileImage;
        TextView userName;
        TextView rateItem_review;
        TextView rateRating;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.userName);
            rateItem_review = itemView.findViewById(R.id.rateItem_review);
            rateRating = itemView.findViewById(R.id.rate_rating);


        }
    }

}
