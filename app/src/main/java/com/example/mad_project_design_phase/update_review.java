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

import java.util.HashMap;

public class update_review extends Working_Side {

    EditText txt_updateReview;
    TextView name,des,price,url;
    Button btnUpdate;
    ImageView food;
    FoodReviews foodReviews;
    DatabaseReference reff;
    Intent intent;
    String FoodID,RestID,CustomerID;



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_review);


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
        FoodID = intent.getStringExtra("FoodID");
        RestID = intent.getStringExtra("RestID");
        CustomerID = CustomerDetails.getCustomerID();

        txt_updateReview = (EditText) findViewById(R.id.txt_updateReview);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        name = (TextView) findViewById(R.id.txt_update_name);
        des =(TextView) findViewById(R.id.txt_update_des);
        price = (TextView) findViewById(R.id.txt_updatePrice);
        food = (ImageView) findViewById(R.id.img_updateReview);

        foodReviews = new FoodReviews();


        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestID).child("Food").child(FoodID).child("FoodDetails");
        
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String foodDes = dataSnapshot.child("description").getValue().toString();
                String foodname = dataSnapshot.child("name").getValue().toString();
                String Price = dataSnapshot.child("price").getValue().toString();
                String link = dataSnapshot.child("foodImage").getValue(String.class);

                Glide.with(food.getContext())
                        .load(link)
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(food);

                name.setText(foodname);
                des.setText(foodDes);
                price.setText(Price);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String r = txt_updateReview.getText().toString().trim();
                updateReview(r);

            }
        });

    }

    private void updateReview (String review){

        HashMap Review = new HashMap();
        Review.put("review", review);

        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child(RestID).child("Restaurant1").child("Food").child(FoodID)
                .child("FoodReviews").child("Customers").child(CustomerID);

        upRef.updateChildren(Review).addOnCompleteListener(new OnCompleteListener(){

            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(update_review.this, "Your Review Updated Successfully!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(update_review.this, "Your Review Updating is Unsuccessfull", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_review.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_review.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_review.this,edit_cart.class);
                startActivity(i);
            }
        });
    }


}