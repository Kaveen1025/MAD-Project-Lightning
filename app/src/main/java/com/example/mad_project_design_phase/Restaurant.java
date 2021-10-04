package com.example.mad_project_design_phase;



import androidx.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Restaurant extends Working_Side {
    TextView resName, resAddress;
    ImageView imgRestu;
    RecyclerView ryvMenu;
    DatabaseReference db;
    RestaurantAdapter restaurantAdapter;
    Intent intent;
    String RestID;
    Button restReviewBtn;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        restReviewBtn = findViewById(R.id.restReviewBtn);
        resName=(TextView) findViewById(R.id.resName);
        resAddress=(TextView) findViewById(R.id.resAddress);
        imgRestu=(ImageView) findViewById(R.id.imgRestu);

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

        intent = getIntent();
        RestID = intent.getStringExtra("RestID");

        db = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
//
                    resName.setText(snapshot.child("name").getValue().toString());
                    resAddress.setText(snapshot.child("address").getValue().toString());

//                    Glide.with(imgRestu.getContext())
//                            .load(snapshot.child("mainImage").getValue())
//                            .placeholder(R.drawable.common_google_signin_btn_icon_dark)
//                            .error(R.drawable.common_google_signin_btn_icon_dark_normal)
//                            .into(imgRestu);

                }
                else {
                    Toast.makeText(Restaurant.this,"No Card to Display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Assigning recycler view
        ryvMenu = (RecyclerView) findViewById(R.id.ryvMenu);

        ryvMenu.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<RestaurantModel> options =
                new FirebaseRecyclerOptions.Builder<RestaurantModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID).child("Food"), RestaurantModel.class)
                        .build();


        restaurantAdapter = new RestaurantAdapter(options);

        ryvMenu.setAdapter(restaurantAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();

        restaurantAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        restaurantAdapter.stopListening();
    }


    protected void onResume() {
        super.onResume();

        restReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant.this,restaurant_ratings_review.class);
                intent.putExtra("RestID",RestID);
                startActivity(intent);
            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Restaurant.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Restaurant.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Restaurant.this,edit_cart.class);
                startActivity(i);
            }
        });
    }

}