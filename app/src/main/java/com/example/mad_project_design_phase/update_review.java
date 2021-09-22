package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class update_review extends AppCompatActivity {

    EditText txt_updateReview;
    Button btnUpdate;

    FoodReviews foodReviews;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_review);

        //txt_updateReview = (EditText) findViewById(R.id.txt_updateReview);
       // btnUpdate = (Button) findViewById(R.id.btnUpdate);

        foodReviews = new FoodReviews();

        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews").child("Customers").child("C2");

        btnUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String review = txt_updateReview.getText().toString().trim();

            }
        });
    }

    private void updateReview (String review){

        HashMap Review = new HashMap();
        Review.put("review", review);

        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews").child("Customers").child("C2");

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

}