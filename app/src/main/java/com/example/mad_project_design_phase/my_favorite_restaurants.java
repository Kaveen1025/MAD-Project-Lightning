package com.example.mad_project_design_phase;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.widget.Toolbar;

public class my_favorite_restaurants extends Working_Side{
                                            // ********
    RecyclerView rv;
    FRMainAdapter mainAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    //Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_restaurants);
        //********
        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navvd);
        toolbar = findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //**********
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