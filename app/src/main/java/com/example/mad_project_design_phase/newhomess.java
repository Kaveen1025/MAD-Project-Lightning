package com.example.mad_project_design_phase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class newhomess extends Working_Side {

    TextView food_name1,food_name2,food1_des, food2_des,food1_price,food2_price,cafe_name,cuisine_type,locations;
    ImageView food_image1,food_image2,restaurant_image;
    Button search,allRestaurnat;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;
    DatabaseReference dbRef;
    ImageSlider imageSlider;



    String CustomerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhomess);


        search = findViewById(R.id.btn_search);
        allRestaurnat = findViewById(R.id.btn_dicover_rest);

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


        food_name1 = findViewById(R.id.food_name1);
        food_name2 = findViewById(R.id.food_name2);
        food1_des = findViewById(R.id.food1_des);
        food2_des = findViewById(R.id.food2_des);
        food1_price = findViewById(R.id.food1_price);
        food2_price = findViewById(R.id.food2_price);
        food_image1 = findViewById(R.id.food_image1);
        food_image2 = findViewById(R.id.food_image2);

        cafe_name = findViewById(R.id.cafe_name);
        cuisine_type = findViewById(R.id.cuisine_type);
        locations = findViewById(R.id.locations);
        restaurant_image = findViewById(R.id.restaurant_image);

        imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1580191947416-62d35a55e71d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1561758033-d89a9ad46330?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1561350111-7daa4f284bc6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1476224203421-9ac39bcb3327?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1541658016709-82535e94bc69?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1169&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1615837197154-2e801f4bd294?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1506224772180-d75b3efbe9be?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1625497248300-475c9557dd3a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"));

        imageSlider.setImageList(slideModels,true);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodDetails");// id **

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                food_name1.setText(snapshot.child("name").getValue().toString());
                food1_des.setText(snapshot.child("description").getValue().toString());
                food1_price.setText(snapshot.child("price").getValue().toString());
                String link = snapshot.child("foodImage").getValue(String.class);
                Picasso.get().load(link).into(food_image1);


            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("d", String.valueOf(error));
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F2").child("FoodDetails");// id **

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                food_name2.setText(snapshot.child("name").getValue().toString());
                food2_des.setText(snapshot.child("description").getValue().toString());
                food2_price.setText(snapshot.child("price").getValue().toString());
                String link = snapshot.child("foodImage").getValue(String.class);
                Picasso.get().load(link).into(food_image2);


            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("d", String.valueOf(error));
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1");// id **

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                cafe_name.setText(snapshot.child("name").getValue().toString());
                cuisine_type.setText(snapshot.child("cuisineType").getValue().toString());
                locations.setText(snapshot.child("address").getValue().toString());

                String link = snapshot.child("mainImage").getValue(String.class);
                Picasso.get().load(link).into(restaurant_image);


            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.i("d", String.valueOf(error));
            }
        });






       // CustomerID = CustomerDetails.getCustomerID();
       // Log.i("dddd",CustomerID);
    }


    protected void onResume() {
        super.onResume();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(newhomess.this,MainSearch.class);
                startActivity(i);
            }
        });

        allRestaurnat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(newhomess.this,all_the_restaurants.class);
                startActivity(i);

            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(newhomess.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(newhomess.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(newhomess.this,edit_cart.class);
                startActivity(i);
            }
        });
    }
}