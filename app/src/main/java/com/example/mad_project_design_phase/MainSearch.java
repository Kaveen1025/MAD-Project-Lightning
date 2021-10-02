package com.example.mad_project_design_phase;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

public class MainSearch extends Working_Side implements View.OnClickListener{

    public CardView itl, jap, ind, amr, vege, other;
    SearchView search;
    Button submit;
    Intent i;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        i = new Intent(MainSearch.this,all_the_restaurants.class);
        search = (SearchView) findViewById(R.id.searchView);
        submit = (Button) findViewById(R.id.searchSubmit);



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


        itl = (CardView) findViewById(R.id.italian);
        jap = (CardView) findViewById(R.id.japanese);
        ind = (CardView) findViewById(R.id.indian);
        amr = (CardView) findViewById(R.id.american);
        vege = (CardView) findViewById(R.id.vegetarian);
        other = (CardView) findViewById(R.id.others);

        itl.setOnClickListener(this);
        jap.setOnClickListener(this);
        ind.setOnClickListener(this);
        amr.setOnClickListener(this);
        vege.setOnClickListener(this);
        other.setOnClickListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = search.getQuery().toString();
                i.putExtra("search",type);
                startActivity(i);

            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.italian:
                i.putExtra("filter", "Italian");
                startActivity(i);
                break;

            case R.id.japanese:
                i.putExtra("filter", "Japanese");
                startActivity(i);
                break;

            case R.id.american:
                i.putExtra("filter", "American");
                startActivity(i);
                break;

            case R.id.indian:
                i.putExtra("filter", "Indian");
                startActivity(i);
                break;

            case R.id.vegetarian:
                i.putExtra("filter", "Vegetarian");
                startActivity(i);
                break;

            case R.id.others:
                i.putExtra("filter", "Other");
                startActivity(i);
                break;

        }
    }


    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSearch.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSearch.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainSearch.this,edit_cart.class);
                startActivity(i);
            }
        });
    }

}