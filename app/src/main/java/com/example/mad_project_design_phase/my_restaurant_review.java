package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.FirebaseDatabase;

public class my_restaurant_review extends AppCompatActivity {

    RecyclerView rv;

    My_Review_adapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_restaurant_review);


        //Assigning recycler view
        rv = (RecyclerView) findViewById(R.id.my_rest_review);

        rv.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<RCustomerReview> options =
                new FirebaseRecyclerOptions.Builder<RCustomerReview>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CustomerReviews").child("C1"), RCustomerReview.class)
                        .build();


        mainAdapter = new My_Review_adapter(options);

        rv.setAdapter(mainAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mainAdapter.stopListening();
    }


}