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


public class update_delete_review extends AppCompatActivity {

    TextView review,Foodname, Des, price;
//    RatingBar rtbar;
//    ImageView FoodImage;
    Button btnDelete, btnUpdate;

    FoodReviews foodReview;

    DatabaseReference ref;
    DatabaseReference reff1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_review);

        review = (TextView) findViewById(R.id.txtReview);
//        rtbar =(RatingBar) findViewById(R.id.updateRatingbar);
        Foodname =(TextView) findViewById(R.id.txt_updateDelete_name);
        Des = (TextView) findViewById(R.id.txt_updateDelete_des);
        price = (TextView) findViewById(R.id.txt_updateDeletePrice);
//        FoodImage =(ImageView) findViewById(R.id.img_updateDeleteReview);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews").child("Customers").child("C2");
        reff1 = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String rate = dataSnapshot.child("noOfStars").getValue().toString();
                String foodreview = dataSnapshot.child("review").getValue().toString();
                String des = dataSnapshot.child("Description").getValue().toString();
                String foodname = dataSnapshot.child("Name").getValue().toString();
                String Price = dataSnapshot.child("Price").getValue().toString();
//                String link = dataSnapshot.getValue(String.class);
//                Picasso.get().load(link).into(FoodImage);
//                float r = Float.parseFloat(rate);
//
               review.setText(foodreview);
//                rtbar.setRating(r);
                Foodname.setText(foodname);
                Des.setText(des);
                price.setText(Price);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews").child("Customers").child("C2");

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
}