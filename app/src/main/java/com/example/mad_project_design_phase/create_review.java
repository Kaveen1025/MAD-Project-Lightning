package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class create_review extends AppCompatActivity {

    RatingBar rtbar;
    EditText txt_writeReview;
    Button btnSubmit;
    DatabaseReference reff;

    FoodReviews reviews;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        rtbar = findViewById(R.id.ratingbar);
        txt_writeReview = findViewById(R.id.txt_writeReview);
        btnSubmit = findViewById(R.id.btnSubmitReview);

        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews").child("Customers");


        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String s = String.valueOf(rtbar.getRating());
                String r = txt_writeReview.getText().toString().trim();

                reviews = new FoodReviews(s,r);
                //Toast.makeText(getApplicationContext(),s +" Stars",Toast.LENGTH_SHORT).show();

                reff.child("C2").setValue(reviews).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    }
}