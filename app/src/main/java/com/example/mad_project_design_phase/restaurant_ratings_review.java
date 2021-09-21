package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class restaurant_ratings_review extends AppCompatActivity {

    Button SubmitReview;
    TextView RestaurantName,ratingTxt;
    RatingBar Ratings;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_ratings_review);
        SubmitReview = findViewById(R.id.SubmitReview);
        RestaurantName = findViewById(R.id.SRestaurantName);
        ratingTxt = findViewById(R.id.ratingValue);
        Ratings = findViewById(R.id.ratings);

//        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Customers");
//
//       ref.addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//               for(DataSnapshot snapshot1 : snapshot.getChildren()) {
//                   Log.i("d", "onDataChange: dd");
//               }
//           }
//
//           @Override
//           public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//           }
//       });





        RestaurantName.setText("Matara Food");
        ratingTxt.setText("4.0 / 5.0");
        Ratings.setRating(4);
        Ratings.setNumStars(5);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog(restaurant_ratings_review.this);
                bottomSheet.setContentView(R.layout.activity_rate_restaurant);
                bottomSheet.setCanceledOnTouchOutside(false);
                bottomSheet.show();

                ImageButton closeDialog;
                Button submitReviewsss;
                RatingBar SubRatings;
                EditText subReview;

                closeDialog = bottomSheet.findViewById(R.id.closeDialog);
                submitReviewsss = bottomSheet.findViewById(R.id.submitReview);
                SubRatings = bottomSheet.findViewById(R.id.SubRatings);
                subReview = bottomSheet.findViewById(R.id.subReview);

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheet.cancel();
                    }
                });


                submitReviewsss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String review = subReview.getText().toString().trim();
                        String noOfStars = String.valueOf(SubRatings.getRating());
                        // Restaurant needs to be select one , Customer needs to be logged one
                        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Reviews").child("Customers");
                        RestaurantReview R1 = new RestaurantReview(review,noOfStars);
                        ref.child("C2").setValue(R1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    /// clear input fields
                                    Toast.makeText(restaurant_ratings_review.this, "Review Submitted Successfully!", Toast.LENGTH_SHORT).show();
                                }else{

                                }
                            }
                        });
                        //ref.child().setValue();
                    }
                });

            }
        });
    }
}