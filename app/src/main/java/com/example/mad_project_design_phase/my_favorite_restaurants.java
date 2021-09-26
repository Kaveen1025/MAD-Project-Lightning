package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class my_favorite_restaurants extends AppCompatActivity {

    RecyclerView rv;
    FRMainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_restaurants);

        rv = (RecyclerView) findViewById(R.id.FavRestaurants);

        rv.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<FavoriteRestaurant> options =
                new FirebaseRecyclerOptions.Builder<FavoriteRestaurant>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FavoriteRestaurant").child("C1"), FavoriteRestaurant.class)
                        .build();


        mainAdapter = new FRMainAdapter(options);

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