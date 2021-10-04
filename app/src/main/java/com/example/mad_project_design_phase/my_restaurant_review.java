package com.example.mad_project_design_phase;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class my_restaurant_review extends Working_Side {

    RecyclerView rv;

    My_Review_adapter mainAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;
    String CustomerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_restaurant_review);

        CustomerID = CustomerDetails.getCustomerID();
        notificationBtn = findViewById(R.id.notificationBtn);
        profileBtn = findViewById(R.id.profileBtn);
        cartBtn = findViewById(R.id.cartBtn);




        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navvd);
        toolbar = findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Assigning recycler view
        rv = (RecyclerView) findViewById(R.id.my_rest_review);

        rv.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<RCustomerReview> options =
                new FirebaseRecyclerOptions.Builder<RCustomerReview>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CustomerRestaurantReviews").child(CustomerID), RCustomerReview.class)
                        .build();


        mainAdapter = new My_Review_adapter(options);

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


    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(my_restaurant_review.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(my_restaurant_review.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(my_restaurant_review.this,edit_cart.class);
                startActivity(i);
            }
        });
    }



}