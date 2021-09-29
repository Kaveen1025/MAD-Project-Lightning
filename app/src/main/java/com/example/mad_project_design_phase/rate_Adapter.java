package com.example.mad_project_design_phase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class rate_Adapter extends FirebaseRecyclerAdapter<rate_Model,rate_Adapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public rate_Adapter(@NonNull FirebaseRecyclerOptions<rate_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull rate_Model model) {
        holder.name.setText(model.getName());
        holder.rate.setText(model.getNoOfStars());
        holder.review.setText(model.getReview());

        Glide.with(holder.img.getContext())
                .load(model.getUserImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name,rate,review;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.profile_image);
            name = (TextView) itemView.findViewById(R.id.userName);
            rate = (TextView) itemView.findViewById(R.id.rate_rating);
            review = (TextView) itemView.findViewById(R.id.rateItem_review);
        }
    }
}
