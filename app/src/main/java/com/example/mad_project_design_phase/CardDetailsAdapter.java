package com.example.mad_project_design_phase;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

//import org.jetbrains.annotations.NotNull;

public class CardDetailsAdapter extends FirebaseRecyclerAdapter<CardDetailsModel, CardDetailsAdapter.myViewHolder> {


    private myViewHolder holder;
    private int position;
    private CardDetailsModel model;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CardDetailsAdapter(@NonNull @NotNull FirebaseRecyclerOptions<CardDetailsModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull @NotNull CardDetailsModel model) {

        holder.cardBank.setText(model.getCardBank());
        holder.cardDate.setText(model.getCardDate());
        holder.cardHolder.setText(model.getCardHolder());
        holder.cardNumber.setText(model.getCardNumber());
        holder.cardType.setText(model.getCardType());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder Builder = new AlertDialog.Builder(holder.itemView.getContext());
                Builder.setTitle("Are You Sure?");
                Builder.setMessage("Deleted data cannot be Undone");

                Builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Payment").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                Builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.itemView.getContext(),"Cancel.",Toast.LENGTH_SHORT ).show();
                    }
                });
                Builder.show();
            }
        });



    }


    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecreditcard, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView cardBank,cardDate,cardHolder,cardType,cardNumber;
        Button btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            cardBank = (TextView)itemView.findViewById(R.id.selCardBank);
            cardDate = (TextView)itemView.findViewById(R.id.selCardDate);
            cardNumber = (TextView)itemView.findViewById(R.id.selCardNumber);
            cardType = (TextView)itemView.findViewById(R.id.selCardType);
            cardHolder = (TextView)itemView.findViewById(R.id.selCardHolder);


            btnDelete =itemView.findViewById(R.id.btnDelete);

        }
    }


}

