package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class rate_food extends AppCompatActivity {

    RecyclerView recyclerView;
    rate_Adapter adapter;
    TextView name, des, url;
    ImageView img;
    Button customize;
    String FoodID, RestaurantID;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_food);

        name = (TextView) findViewById(R.id.foodRating_name);
        des = (TextView) findViewById(R.id.foodRating_des);
        url = (TextView) findViewById(R.id.rateFood_url);
        img =(ImageView) findViewById(R.id.foodRating_img);
        customize =(Button) findViewById(R.id.btn_customize);

        recyclerView = (RecyclerView) findViewById(R.id.foodRating_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<rate_Model> options=
                new FirebaseRecyclerOptions.Builder<rate_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodReviews")
                                .child("Customers"), rate_Model.class)
                        .build();

        adapter = new rate_Adapter(options);
        recyclerView.setAdapter( adapter);

        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodDetails");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rateName =  snapshot.child("name").getValue().toString();
                String rateDes =  snapshot.child("description").getValue().toString();
                String link = snapshot.child("foodImage").getValue(String.class);
                url.setText(link);
                Picasso.get()
                        .load(link)
                        .into(img);
                name.setText(rateName);
                des.setText(rateDes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}