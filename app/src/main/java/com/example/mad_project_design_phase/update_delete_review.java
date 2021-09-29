package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;


public class update_delete_review extends AppCompatActivity {

    TextView review,foodName, fDes, price,url;
    RatingBar rtbar;
    ImageView FoodImage;
    Button btnDelete, btnUpdate;

    DatabaseReference ref;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_review);

        review = (TextView) findViewById(R.id.txtReview);
        rtbar =(RatingBar) findViewById(R.id.updateRatingbar);
        foodName =(TextView) findViewById(R.id.txt_update_name);
        fDes = (TextView) findViewById(R.id.txt_update_des);
        price = (TextView) findViewById(R.id.txt_updatePrice);
        FoodImage =(ImageView) findViewById(R.id.img_updateReview);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);


        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews")
                .child("Customers").child("C1");
        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodDetails");
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
                url.setText(link);
                Picasso.get()
                        .load(link)
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
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews")
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
}