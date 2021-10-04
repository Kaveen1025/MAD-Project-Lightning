package com.example.mad_project_design_phase;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class favouriteFood_Adapter extends FirebaseRecyclerAdapter<FavouriteModel,favouriteFood_Adapter.myViewHolder> {

    String UserID;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public favouriteFood_Adapter(@NonNull FirebaseRecyclerOptions<FavouriteModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull favouriteFood_Adapter.myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull FavouriteModel model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.calories.setText(model.getCalories());

        Glide.with(holder.img.getContext())
                .load(model.getFoodImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("FavoriteFood").child("C1").child(getRef(position).getKey()).removeValue();
            }
        });

    }

    @NonNull
    @Override
    public favouriteFood_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_food_item,parent,false);
        return  new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView price, name, calories;
        ImageButton deleteBtn;


        public myViewHolder(@NonNull View itemView){
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.favourite_img);
            price = (TextView) itemView.findViewById(R.id.favourite_price);
            name = (TextView) itemView.findViewById(R.id.favourite_name);
            calories = (TextView) itemView.findViewById(R.id.favourite_cal);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.delete_icon);

        }

    }
}
