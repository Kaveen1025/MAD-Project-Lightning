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
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, final int position, @NonNull @NotNull FavoriteRestaurant model) {

        holder.name.setText(model.getName());
        holder.cuisineType.setText(model.getCuisineType());
        holder.address.setText(model.getAddress());


        Glide.with(holder.img.getContext())
                .load(model.getRestImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                //.circleCrop()

                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setCancelable(false);


                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("FavoriteRestaurant").child("C1")
                                .child(getRef(position).getKey()).removeValue();
                        
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                    }
                });
               // final AlertDialog dialog = builder.create();
                builder.show();
            }
        });


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

        ImageView img;
        TextView name,address,cuisineType;
        ImageButton DeleteBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);




            //img = (CircleImageView)itemView.findViewById(R.id.img1);

            img = (ImageView)itemView.findViewById(R.id.imageView2);
            name = (TextView)itemView.findViewById(R.id.RestaurantName);
            address = (TextView)itemView.findViewById(R.id.Address);
            cuisineType = (TextView)itemView.findViewById(R.id.CusinieType);
            DeleteBtn = (ImageButton) itemView.findViewById(R.id.DeleteBtn);

        }
    }

}
