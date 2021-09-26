package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class all_the_restaurants extends AppCompatActivity {

    RecyclerView rv;

    RestMainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_the_restaurants);

        //Assigning recycler view
        rv = (RecyclerView) findViewById(R.id.restRecycler);

        rv.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<AllRestaurant> options =
                new FirebaseRecyclerOptions.Builder<AllRestaurant>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TestRestaurant"), AllRestaurant.class)
                        .build();


        mainAdapter = new RestMainAdapter(options);

        rv.setAdapter(mainAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}