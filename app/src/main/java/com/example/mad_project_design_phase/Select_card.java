package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Bundle;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.firebase.database.FirebaseDatabase;

public class Select_card extends AppCompatActivity {

    RecyclerView ryvSelectCard;

    SelectCardAdapter selectCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);

        //Assigning recycler view
        ryvSelectCard = (RecyclerView) findViewById(R.id.ryvSelectCard);
        ryvSelectCard.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<SelectCardModel> options =
                new FirebaseRecyclerOptions.Builder<SelectCardModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Payment").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73"), SelectCardModel.class)
                        .build();


        selectCardAdapter = new SelectCardAdapter(options);

        ryvSelectCard.setAdapter(selectCardAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();

        selectCardAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        selectCardAdapter.stopListening();
    }
}