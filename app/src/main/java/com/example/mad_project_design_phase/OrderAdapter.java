package com.example.mad_project_design_phase;

import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class OrderAdapter extends FirebaseRecyclerAdapter<OrderModel,OrderAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public OrderAdapter(@NonNull FirebaseRecyclerOptions<OrderModel> options) {
        super(options);
    }

    protected void onBindViewHolder(@NotNull OrderAdapter.myViewHolder holder, int position, @NotNull OrderModel model1) {



        holder.restName.setText(model1.getRestaurantName());
        holder.totalPrice2.setText(model1.getTotalPrice());
        Glide.with(holder.restImage.getContext())
                .load(model1.getRestImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.restImage);
        holder.mainFoods.setText(model1.getMainFood());
        holder.totalAmount.setText(model1.getTotalPrice());

        FirebaseDatabase.getInstance().getReference().child("Orders").child("C1").child(getRef(position).getKey()).child("Foods")
                .addValueEventListener(new ValueEventListener() {

                    String[] prices = new String[3];
                    String[] qtys = new String[3];
                    String[] foodnames = new  String[3];
                    @Override
                    public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                        int i = 0;

                        for (DataSnapshot childSnap : snapshot.getChildren()) {

                            prices[i] = childSnap.child("price").getValue().toString();
                            qtys[i] = childSnap.child("quantity").getValue().toString();
                            foodnames[i] = childSnap.child("name").getValue().toString();
                            i++;

                        }

                        holder.food1.setText(foodnames[0]);
                        holder.food2.setText(foodnames[1]);
                        holder.food3.setText(foodnames[2]);

                        holder.price1.setText(prices[0]);
                        holder.price2.setText(prices[1]);
                        holder.price3.setText(prices[2]);

                        holder.qty2.setText(qtys[0]);
                        holder.qty3.setText(qtys[1]);
                        holder.qty4.setText(qtys[2]);

                    }

                    @Override
                    public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

                    }
                });






    }

    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercard,parent,false);
        return new myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder{

        TextView restName,totalPrice2,mainFoods,food1,food2,food3,qty2,qty3,qty4,price1,price2,price3,totalAmount;
        ImageView restImage;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            restName = (TextView) itemView.findViewById(R.id.rest_name2);

            totalPrice2 = (TextView) itemView.findViewById(R.id.tot_price2);

            mainFoods = (TextView) itemView.findViewById(R.id.foods2);

            food1 = (TextView) itemView.findViewById(R.id.food1);
            food2 = (TextView) itemView.findViewById(R.id.food2);
            food3 = (TextView) itemView.findViewById(R.id.food3);

            qty2 = (TextView) itemView.findViewById(R.id.qty2);
            qty3 = (TextView) itemView.findViewById(R.id.qty3);
            qty4 = (TextView) itemView.findViewById(R.id.qty4);

            price1 = (TextView) itemView.findViewById(R.id.price1);
            price2 = (TextView) itemView.findViewById(R.id.price2);
            price3 = (TextView) itemView.findViewById(R.id.price3);

            restImage = (ImageView) itemView.findViewById(R.id.restImage);
            totalAmount = (TextView) itemView.findViewById(R.id.total_amount1);

            TextView[] foods = { food1,food2,food3};
            TextView[] qty = {qty2,qty3,qty4};
            TextView[] price = {price1,price2,price3};

        }
    }
}
