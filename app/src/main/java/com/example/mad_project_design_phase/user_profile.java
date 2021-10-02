package com.example.mad_project_design_phase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class user_profile extends Working_Side {

    EditText et_firstname, et_last_name, et_email, up_phone, up_address;
    Button up_edit, btn_View_Address;
    DatabaseReference dbRef;
    //    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String CustomerID;
    CircleImageView profile_image;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        notificationBtn = findViewById(R.id.notificationBtn);
        profileBtn = findViewById(R.id.profileBtn);
        cartBtn = findViewById(R.id.cartBtn);


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


        et_firstname = findViewById(R.id.et_firstname);
        et_last_name = findViewById(R.id.et_last_name);
        et_email= findViewById(R.id.et_email);
        up_phone = findViewById(R.id.up_phone);
        up_address = findViewById(R.id.up_address);
        up_edit = findViewById(R.id.up_edit);
        btn_View_Address = findViewById(R.id.btn_View_Address);
        profile_image = findViewById(R.id.profile_image);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("GGMo8PMil0YZXMlm09ys77peK7N2");// id **

       dbRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                   et_firstname.setText(snapshot.child("firstName").getValue().toString());
                   et_last_name.setText(snapshot.child("lastName").getValue().toString());
                   et_email.setText(snapshot.child("email").getValue().toString());
                   up_phone.setText(snapshot.child("phoneNumber").getValue().toString());
                   up_address.setText(snapshot.child("address").getValue().toString());
                   String link = snapshot.child("pimage").getValue(String.class);
                   Picasso.get().load(link).into(profile_image);


           }
           @Override
           public void onCancelled(@NonNull @NotNull DatabaseError error) {
               Log.i("d", String.valueOf(error));
           }
       });


    }


    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(user_profile.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(user_profile.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(user_profile.this,edit_cart.class);
                startActivity(i);
            }
        });
    }


}