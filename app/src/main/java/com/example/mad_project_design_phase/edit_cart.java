package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class edit_cart extends AppCompatActivity {

    RecyclerView recyclerView;
    FoodCartAdapter foodCartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cart_main);

        recyclerView = (RecyclerView)findViewById(R.id.CartRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //fetching data

        FirebaseRecyclerOptions<FoodCart> options =
                new FirebaseRecyclerOptions.Builder<FoodCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1"), FoodCart.class)
                        .build();

        foodCartAdapter = new FoodCartAdapter(options);
        recyclerView.setAdapter(foodCartAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        foodCartAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        foodCartAdapter.startListening();
    }


}