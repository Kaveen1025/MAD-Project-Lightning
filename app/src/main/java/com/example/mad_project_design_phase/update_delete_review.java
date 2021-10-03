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


public class update_delete_review extends Working_Side {

    TextView review,foodName, fDes, price,url;
    RatingBar rtbar;
    ImageView FoodImage;
    Button btnDelete, btnUpdate;

    DatabaseReference ref;
    DatabaseReference reff;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;
    Intent intent;
    String FoodID,RestaurantID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_review);

        intent = getIntent();
        FoodID = intent.getStringExtra("FoodID");
        RestaurantID = intent.getStringExtra("RestID");

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


        review = (TextView) findViewById(R.id.txtReview);
        rtbar =(RatingBar) findViewById(R.id.updateRatingbar);
        foodName =(TextView) findViewById(R.id.txt_update_name);
        fDes = (TextView) findViewById(R.id.txt_update_des);
        price = (TextView) findViewById(R.id.txt_updatePrice);
        FoodImage =(ImageView) findViewById(R.id.img_updateReview);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);


        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestaurantID).child("Food").child(FoodID).child("FoodReviews")
                .child("Customers").child("C1");
        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestaurantID).child("Food").child(FoodID).child("FoodDetails");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String rate = dataSnapshot.child("noOfStars").getValue().toString();
                String foodReview = dataSnapshot.child("review").getValue().toString();
                float r = Float.parseFloat(rate);
                review.setText(foodReview);
                rtbar.setRating(r);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String des = dataSnapshot.child("description").getValue().toString();
                String foodname = dataSnapshot.child("name").getValue().toString();
                String Price = dataSnapshot.child("price").getValue().toString();
                String link = dataSnapshot.child("foodImage").getValue(String.class);
//                url.setText(link);
//                Picasso.get()
//                        .load(link)
//                        .into(FoodImage);
                Glide.with(FoodImage.getContext())
                        .load(link)
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(FoodImage);

                foodName.setText(foodname);
                fDes.setText(des);
                price.setText(Price);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestaurantID).child("Food").child(FoodID).child("FoodReviews")
                        .child("Customers").child("C1");

                delRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Review Deleted Successfully",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"review Deleting Failed !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }


    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_delete_review.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_delete_review.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_delete_review.this,edit_cart.class);
                startActivity(i);
            }
        });
    }
}