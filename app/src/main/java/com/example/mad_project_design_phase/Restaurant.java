package com.example.mad_project_design_phase;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Restaurant extends AppCompatActivity {
    TextView resName, resAddress;
    ImageView imgRestu;
    RecyclerView ryvMenu;
    DatabaseReference db;
    RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        resName=(TextView) findViewById(R.id.resName);
        resAddress=(TextView) findViewById(R.id.resAddress);
        imgRestu=(ImageView) findViewById(R.id.imgRestu);

        db = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("R2");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
//
                    resName.setText(snapshot.child("name").getValue().toString());
                    resAddress.setText(snapshot.child("address").getValue().toString());
//                    imgRestu.

                }
                else {
                    Toast.makeText(Restaurant.this,"No Card to Display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Assigning recycler view
        ryvMenu = (RecyclerView) findViewById(R.id.ryvMenu);

        ryvMenu.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<RestaurantModel> options =
                new FirebaseRecyclerOptions.Builder<RestaurantModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child("R2").child("Food"), RestaurantModel.class)
                        .build();


        restaurantAdapter = new RestaurantAdapter(options);

        ryvMenu.setAdapter(restaurantAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();

        restaurantAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        restaurantAdapter.stopListening();
    }
}