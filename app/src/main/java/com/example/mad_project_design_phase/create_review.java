package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class create_review extends Working_Side {

    RatingBar rtbar;
    EditText txt_writeReview;
    Button btnSubmit;
    TextView foodName,url;
    ImageView food;
    DatabaseReference reff;
    DatabaseReference ref;

    FoodReviews reviews;



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    Intent intent;
    String RestID,FoodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        intent = getIntent();
        RestID = intent.getStringExtra("RestID");
        FoodID = intent.getStringExtra("FoodID");
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



        rtbar = (RatingBar) findViewById(R.id.ratingbar);
        txt_writeReview = (EditText) findViewById(R.id.txt_writeReview);
        btnSubmit = (Button) findViewById(R.id.btnSubmitReview);
        foodName = (TextView) findViewById(R.id.txtfoodname);
        food = (ImageView) findViewById(R.id.img_food);
        url = (TextView) findViewById(R.id.img_url);

        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID).child("Food").child(FoodID).child("FoodReviews").child("Customers");
        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID).child("Food").child(FoodID);
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String s = String.valueOf(rtbar.getRating());
                String r = txt_writeReview.getText().toString().trim();
                String username = CustomerDetails.getNames();
                String userImage = CustomerDetails.getUserImage();

                reviews = new FoodReviews(s,r,username,userImage);
                reff.child(CustomerDetails.getCustomerID()).setValue(reviews).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Review added Successfully", Toast.LENGTH_LONG).show();
                        }else{
        
                            Toast.makeText(getApplicationContext(),"Review adding failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String foodname = dataSnapshot.child("name").getValue().toString();
                String link = dataSnapshot.child("foodImage").getValue(String.class);
//                url.setText(link);
//                Picasso.get()
//                        .load(link)
//                        .into(food);
                foodName.setText(foodname);

                Glide.with(food.getContext())
                        .load(link)
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(food);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(create_review.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(create_review.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(create_review.this,edit_cart.class);
                startActivity(i);
            }
        });
    }
}