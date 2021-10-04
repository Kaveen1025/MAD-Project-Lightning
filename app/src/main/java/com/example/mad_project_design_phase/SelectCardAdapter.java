package com.example.mad_project_design_phase;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
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

public class SelectCardAdapter extends FirebaseRecyclerAdapter<SelectCardModel, SelectCardAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SelectCardAdapter(@NonNull @NotNull FirebaseRecyclerOptions<SelectCardModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position, @NonNull @NotNull SelectCardModel model) {

        holder.cardtype.setText(model.getCardType());
        holder.cardnumber.setText(model.getCardNumber());
        holder.btnPayC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),checkout.class);
                v.getContext().startActivity(intent);
            }
        });


    }


    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oneselectcard, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView cardtype,cardnumber;

        Button btnSelect,btnPayC;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            cardtype = (TextView)itemView.findViewById(R.id.txtType);
            cardnumber = (TextView)itemView.findViewById(R.id.txtCardNo);


            btnSelect =itemView.findViewById(R.id.btnSelect);
            btnPayC =itemView.findViewById(R.id.btnPayC);

        }
    }


}
