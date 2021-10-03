package com.example.mad_project_design_phase;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class Working_Side extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Intent i;
    ImageButton notificationBtn,profileBtn,cartBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_side);



        notificationBtn = findViewById(R.id.notificationBtn);
        profileBtn = findViewById(R.id.profileBtn);
        cartBtn = findViewById(R.id.cartBtn);


        // ******
        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navvd);
        toolbar = findViewById(R.id.toolbarss);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // *******
    }



    public  void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){

            case R.id.myFavRestaurants:
                 i = new Intent(Working_Side.this,my_favorite_restaurants.class);
                 startActivity(i);
                break;

            case R.id.myFavFoods:
                i = new Intent(Working_Side.this,favorits_food.class);
                startActivity(i);
                break;

            case R.id.myFoodReviewss:
                i = new Intent(Working_Side.this,My_reviews.class);
                startActivity(i);
                break;
            case R.id.myRestaurantReviewss:
                i = new Intent(Working_Side.this,my_restaurant_review.class);
                startActivity(i);
                break;

            case R.id.cards:
               i = new Intent(Working_Side.this,card_details.class);
                startActivity(i);
                break;
            case R.id.orderss:
                i = new Intent(Working_Side.this,view_orders.class);
                startActivity(i);
                break;
            case R.id.PH:
                i = new Intent(Working_Side.this,purchase_history.class);
                startActivity(i);
                break;
            case R.id.logout:
                i = new Intent(Working_Side.this,login_page_2.class);

                /// write a code for sign out form the DB*******
                startActivity(i);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Working_Side.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Working_Side.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Working_Side.this,edit_cart.class);
                startActivity(i);
            }
        });
    }


}