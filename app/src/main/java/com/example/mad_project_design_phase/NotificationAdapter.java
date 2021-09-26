package com.example.mad_project_design_phase;


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

import java.util.Objects;

//import ru.embersoft.expandabletextview.ExpandableTextView;

//import org.jetbrains.annotations.NotNull;

public class NotificationAdapter extends FirebaseRecyclerAdapter<NotificationModel, NotificationAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotificationAdapter(@NonNull @NotNull FirebaseRecyclerOptions<NotificationModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position, @NonNull @NotNull NotificationModel model) {

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());

    }


    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onenotitficationcard, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView title,description;

        Button btnNotShow;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.txtNotTitle);
            description = (TextView)itemView.findViewById(R.id.txtNotDescription);



            btnNotShow =itemView.findViewById(R.id.btnNotShow);

        }
    }


}



