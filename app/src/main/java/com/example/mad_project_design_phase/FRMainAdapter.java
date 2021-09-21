package com.example.mad_project_design_phase;

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

import org.jetbrains.annotations.NotNull;

public class FRMainAdapter extends FirebaseRecyclerAdapter<FavoriteRestaurant,FRMainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FRMainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<FavoriteRestaurant> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position, @NonNull @NotNull FavoriteRestaurant model) {

        holder.name.setText(model.getNames());
        holder.cuisineType.setText(model.getCuisineTypes());
        holder.address.setText(model.getAddresss());


        /*Glide.with(holder.img.getContext())
                .load(model.getTurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                //.circleCrop()

                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);*/



    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_favorite_rest_card, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //ImageView ;
        TextView name,address,cuisineType;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);




            //img = (CircleImageView)itemView.findViewById(R.id.img1);

            //img = (ImageView)itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.RestaurantName);
            address = (TextView)itemView.findViewById(R.id.Address);
            cuisineType = (TextView)itemView.findViewById(R.id.CusinieType);

        }
    }

}
