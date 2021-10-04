package com.example.mad_project_design_phase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class rate_food extends Working_Side {

    RecyclerView recyclerView;
    rate_Adapter adapter;
    TextView name, des, url;
    ImageView img;
    Button customize;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    Intent intent;
    String RestID,FoodID;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_food);

        intent = getIntent();
        RestID = intent.getStringExtra("RestID");
        FoodID = intent.getStringExtra("FoodID");

//        RestID = "R3";
//        FoodID = "F1";

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

        name = (TextView) findViewById(R.id.foodRating_name);
        des = (TextView) findViewById(R.id.foodRating_des);
       // url = (TextView) findViewById(R.id.rateFood_url);
        img =(ImageView) findViewById(R.id.foodRating_img);
        customize =(Button) findViewById(R.id.btn_customize);

        recyclerView = (RecyclerView) findViewById(R.id.foodRating_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<rate_Model> options=
                new FirebaseRecyclerOptions.Builder<rate_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID).child("Food").child(FoodID).child("FoodReviews")
                                .child("Customers"), rate_Model.class)
                        .build();

        adapter = new rate_Adapter(options);
        recyclerView.setAdapter( adapter);

        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID).child("Food").child(FoodID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rateName =  snapshot.child("name").getValue().toString();
                String rateDes =  snapshot.child("description").getValue().toString();
                String link = snapshot.child("foodImage").getValue(String.class);


                Glide.with(img.getContext())
                        .load(link)
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(img);
                name.setText(rateName);
                des.setText(rateDes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    protected void onResume() {
        super.onResume();

        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rate_food.this,customise_food.class);
                intent.putExtra("RestID",RestID);
                intent.putExtra("FoodID",FoodID);
                startActivity(intent);
            }
        });
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(rate_food.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(rate_food.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(rate_food.this,edit_cart.class);
                startActivity(i);
            }
        });
    }

}