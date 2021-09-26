package com.example.mad_project_design_phase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;


public class ViewAddressAdapter extends FirebaseRecyclerAdapter<CustomerAddress, ViewAddressAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewAddressAdapter(FirebaseRecyclerOptions<CustomerAddress> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull CustomerAddress model) {

        holder.Address.setText(model.getAddress());
        holder.Province.setText(model.getProvince());
        holder.City.setText(model.getCity());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Address.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        FirebaseDatabase.getInstance().getReference().child("Customer Address").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which){

                        Toast.makeText(holder.Address.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_address,parent,false);

        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{


        TextView Address, Province, City;
        Button btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            Address = (TextView) itemView.findViewById(R.id.addresstext);
            Province = (TextView) itemView.findViewById(R.id.provincetext);
            City = (TextView) itemView.findViewById(R.id.citytext);

            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }


}
