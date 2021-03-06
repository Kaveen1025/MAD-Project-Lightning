package com.example.mad_project_design_phase;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class RestMainAdapter extends FirebaseRecyclerAdapter<AllRestaurant, RestMainAdapter.myViewHolder> {
public String userID; 
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RestMainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<AllRestaurant> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, final int position, @NonNull @NotNull AllRestaurant model) {

        holder.name.setText(model.getName());
        holder.cuisineType.setText(model.getCuisineType());
        holder.address.setText(model.getAddress());



        Glide.with(holder.mainImage.getContext())
                .load(model.getMainImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                //.circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.mainImage);

        Glide.with(holder.logoImg.getContext())
                .load(model.getLogo())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)

                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.logoImg);


        FirebaseDatabase.getInstance().getReference().child("TestRestaurant").child("R1").child("Reviews").child("Customers")
                .addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        int noOfStars = 0;
                        int customerCount = 0;
                        for (DataSnapshot childSnap : snapshot.getChildren()) {

                             noOfStars = noOfStars + Integer.parseInt(childSnap.child("noOfStars").getValue().toString());
                             customerCount++;
                             Log.i("dd",String.valueOf(noOfStars));
                        }



                        int totalStars = customerCount * 5;

                        float rating = ( noOfStars / totalStars ) * 5;

                        holder.rating.setText(String.valueOf(rating + 1 * 4) + " / 5.0");

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

        holder.restCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Restaurant.class);
                intent.putExtra("RestID",getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });

        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AFavoriteRestaurant FA = new AFavoriteRestaurant(model.getAddress(), model.getCuisineType(),model.getName(), model.getLogo());
               //FirebaseDatabase.getInstance().getReference().child("FavoriteRestaurant").child(userID).child(getRef(position.getKey())).setValue(FA);

            }
        });



    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView logoImg,mainImage;
        TextView name,address,cuisineType,rating;
        ImageButton favButton;
        CardView restCard;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            logoImg = itemView.findViewById(R.id.RestLogo);
            mainImage = itemView.findViewById(R.id.mainImage);
            name = itemView.findViewById(R.id.RestName);
            address = itemView.findViewById(R.id.RestAddress);
            cuisineType = itemView.findViewById(R.id.cuisineType);
            favButton = itemView.findViewById(R.id.favImageButton);
            rating = itemView.findViewById(R.id.restRatings);
            restCard = itemView.findViewById(R.id.restCard);
        }
    }

}
