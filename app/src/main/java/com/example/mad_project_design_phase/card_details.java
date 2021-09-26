package com.example.mad_project_design_phase;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;

        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.firebase.database.FirebaseDatabase;

public class card_details extends AppCompatActivity {

    RecyclerView ryvCard_Details;

    CardDetailsAdapter cardDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        //Assigning recycler view
        ryvCard_Details = (RecyclerView) findViewById(R.id.ryvCard_Details);

        ryvCard_Details.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CardDetailsModel> options =
                new FirebaseRecyclerOptions.Builder<CardDetailsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Payment").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73"), CardDetailsModel.class)
                        .build();


        cardDetailsAdapter = new CardDetailsAdapter(options);

        ryvCard_Details.setAdapter(cardDetailsAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();

        cardDetailsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cardDetailsAdapter.stopListening();
    }
}