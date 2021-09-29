package com.example.mad_project_design_phase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class PurchaseHistoryAdapter extends FirebaseRecyclerAdapter<PurchaseHistoryModel, PurchaseHistoryAdapter.myViewHolder> {

    private  Float totalPrice = 0.0f;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PurchaseHistoryAdapter(@NonNull @NotNull FirebaseRecyclerOptions<PurchaseHistoryModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull PurchaseHistoryAdapter.myViewHolder holder, int position, @NonNull @NotNull PurchaseHistoryModel model) {

        holder.id.setText(model.getId());
        holder.dateAndTime.setText(model.getDateAndTime());
        holder.price.setText(model.getPrice());

        totalPrice = totalPrice + Float.parseFloat(model.getPrice());
        Log.i("a", String.valueOf(totalPrice));

//        holder.totalPrice.setText(String.valueOf(totalPrice));
    }


    @NonNull
    @NotNull
    @Override
    public PurchaseHistoryAdapter.myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_payment_history, parent, false);
        return new PurchaseHistoryAdapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView id,dateAndTime,price,totalPrice;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.txtID);
            dateAndTime = (TextView)itemView.findViewById(R.id.txtDate);
            price = (TextView)itemView.findViewById(R.id.txtPrice);
            totalPrice = (TextView) itemView.findViewById(R.id.txtTotal);

        }
    }


}



