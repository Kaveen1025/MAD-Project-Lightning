package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class My_reviews extends AppCompatActivity {

    RecyclerView recyclerview;
    myReviews_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reviews);

        recyclerview = (RecyclerView) findViewById(R.id.my_Reviews_rv);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ReviewsModel> options =
                new FirebaseRecyclerOptions.Builder<ReviewsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("CustomerFoodReviewDetails").child("C1"), ReviewsModel.class)
                .build();

        adapter = new myReviews_Adapter(options);
        recyclerview.setAdapter(adapter);

    }

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}