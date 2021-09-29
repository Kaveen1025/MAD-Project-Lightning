package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class favorits_food extends AppCompatActivity {

    RecyclerView recyclerView;
    favouriteFood_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorits_food);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        recyclerView = (RecyclerView) findViewById((R.id.favourite_rv));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions <FavouriteModel> options =
                new FirebaseRecyclerOptions.Builder<FavouriteModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("FavoriteFood").child("C1"),FavouriteModel.class)
                .build();

        adapter = new favouriteFood_Adapter(options);
        recyclerView.setAdapter(adapter);


    }

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }


}