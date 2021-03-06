package com.example.mad_project_design_phase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class FoodCartAdapter extends FirebaseRecyclerAdapter<FoodCart, FoodCartAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FoodCartAdapter(@NonNull FirebaseRecyclerOptions<FoodCart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull FoodCart model) {

        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());

        Glide.with(holder.food_image.getContext())
                .load(model.getFoodImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.food_image);

       holder.quantitys.setText("1");
      holder.increment.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Integer number = Integer.parseInt(holder.quantitys.getText().toString());
              number  += 1;
              Integer newPrice = Integer.parseInt(model.getPrice()) * number;
              holder.quantitys.setText(String.valueOf(number));
                holder.price.setText(String.valueOf(newPrice));
          }
      });



    holder.decrement.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Integer number = Integer.parseInt(holder.quantitys.getText().toString());
        number  -= 1;
        if(number == 0){
            holder.quantitys.setText("1");
        }
        Integer newPrice = Integer.parseInt(model.getPrice())  * number;
        holder.quantitys.setText(String.valueOf(number));
        holder.price.setText(String.valueOf(newPrice));

    }
});

        holder.CartI_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which){

                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_edit_cart,parent,false);

        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{


        TextView name, description, price,total,quantitys;
        ImageView food_image;
        //ElegantNumberButton number;
        ImageButton increment,decrement,CartI_Delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.foodNtext);
            description = (TextView) itemView.findViewById(R.id.foodDtext);
            price = (TextView) itemView.findViewById(R.id.foodPtext);
           // number = itemView.findViewById(R.id.txt_amount);
            total = itemView.findViewById(R.id.total);
            food_image = (ImageView) itemView.findViewById(R.id.food_image);
           increment = (ImageButton) itemView.findViewById(R.id.increment);
            decrement =(ImageButton)  itemView.findViewById(R.id.decrement);
           quantitys = itemView.findViewById(R.id.quantitys);
            CartI_Delete = (ImageButton)  itemView.findViewById(R.id.CartI_Delete);
            //ConstraintLayout myCart = (ConstraintLayout)itemView.findViewById(R.id.my_cart);
        }


    }


}
