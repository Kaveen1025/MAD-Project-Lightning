package com.example.mad_project_design_phase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

//import org.jetbrains.annotations.NotNull;

public class RestaurantAdapter extends FirebaseRecyclerAdapter<RestaurantModel, RestaurantAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RestaurantAdapter(@NonNull @NotNull FirebaseRecyclerOptions<RestaurantModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position, @NonNull @NotNull RestaurantModel model) {

        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());


        Glide.with(holder.imgMenu.getContext())
                .load(model.getFoodImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgMenu);


        holder.oneFoodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),create_review.class);
                intent.putExtra("RestID",getRef(position).getParent().getParent().getKey());
                intent.putExtra("FoodID",getRef(position).getKey());

                v.getContext().startActivity(intent);
            }
        });

        holder.btnRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),rate_food.class);
                intent.putExtra("RestID",getRef(position).getParent().getParent().getKey());
                intent.putExtra("FoodID",getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });
    }
    @NonNull @NotNull @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_restaurant_menu, parent, false);
        return new myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{


        TextView name,price;
        ImageView imgMenu;
        Button btnRatings;
        CardView oneFoodCard;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.resFood);
            price = (TextView)itemView.findViewById(R.id.resPrice);
            imgMenu = itemView.findViewById(R.id.imgMenu);
            btnRatings =itemView.findViewById(R.id.btnRatings);
            oneFoodCard = itemView.findViewById(R.id.oneFoodCard);

        }
    }


}




