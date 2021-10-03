package com.example.mad_project_design_phase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myReviews_Adapter extends FirebaseRecyclerAdapter<ReviewsModel,myReviews_Adapter.myViewHolder> {

    private Context mContext;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myReviews_Adapter(@NonNull FirebaseRecyclerOptions<ReviewsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myReviews_Adapter.myViewHolder holder, int position, @NonNull ReviewsModel model) {

        holder.description.setText(model.getDescription());
        holder.name.setText(model.getName());

        Glide.with(holder.img.getContext())
                .load(model.getFoodImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.foodReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), update_delete_review.class);
                intent.putExtra("FoodID", getRef(position).getKey());
                intent.putExtra("RestID",model.getRestID());
               // Log.i("abc",getRef(position).getKey());
                //Log.i("abc",getRef(position).getParent().getParent().getKey());
               // Log.i("abc",get);
                v.getContext().startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public myReviews_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_reviews_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView description;
        Button name;
        CardView foodReview;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.foodImage);
            description =(TextView) itemView.findViewById(R.id.txt_foodDes);
            name = (Button) itemView.findViewById(R.id.btn_foodName);
            foodReview = itemView.findViewById(R.id.foodReviewsBtn);


        }
    }
}
