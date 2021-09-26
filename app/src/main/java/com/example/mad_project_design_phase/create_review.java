package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;


public class create_review extends AppCompatActivity {

    RatingBar rtbar;
    EditText txt_writeReview;
    Button btnSubmit;
    TextView foodName,url;
    ImageView food;
    DatabaseReference reff;
    DatabaseReference ref;
    String UserID,FoodID, RestaurantID;
    FoodReviews reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        //rtbar = (RatingBar) findViewById(R.id.ratingbar);
        //txt_writeReview = (EditText) findViewById(R.id.txt_writeReview);
        //btnSubmit = (Button) findViewById(R.id.btnSubmitReview);
        //foodName = (TextView) findViewById(R.id.txt_foodname);
        //food = (ImageView) findViewById(R.id.img_food);
        //url = (TextView) findViewById(R.id.img_url);

        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestaurantID).child("Food").child(FoodID).child("FoodReviews").child("Customers");
        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(RestaurantID).child("Food").child(FoodID).child("FoodDetails");
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String s = String.valueOf(rtbar.getRating());
                String r = txt_writeReview.getText().toString().trim();

                reviews = new FoodReviews(s,r);
                reff.child(UserID).setValue(reviews).addOnCompleteListener(new OnCompleteListener<Void>() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}