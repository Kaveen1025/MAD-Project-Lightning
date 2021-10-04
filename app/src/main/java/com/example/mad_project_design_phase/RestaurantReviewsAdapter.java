package com.example.mad_project_design_phase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RestaurantReviewsAdapter extends FirebaseRecyclerAdapter<CustomerRestaurantReviews, RestaurantReviewsAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RestaurantReviewsAdapter(@NonNull @NotNull FirebaseRecyclerOptions<CustomerRestaurantReviews> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull RestaurantReviewsAdapter.myViewHolder holder, final int position,
                                    @NonNull @NotNull CustomerRestaurantReviews model) {
        holder.name.setText(model.getName());
        holder.rating.setText(model.getNoOfStars());
        holder.review.setText(model.getReview());
        Glide.with(holder.img.getContext()).load(model.getCustomerProfile()).placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.img);
    }
    @NonNull
    @NotNull
    @Override
    public RestaurantReviewsAdapter.myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_favorite_rest_card, parent, false);
        return new RestaurantReviewsAdapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,review,rating;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.customerProfile);
            name = (TextView)itemView.findViewById(R.id.custName);
            review = (TextView)itemView.findViewById(R.id.review);
            rating = (TextView)itemView.findViewById(R.id.custRating);

        }
    }

}


