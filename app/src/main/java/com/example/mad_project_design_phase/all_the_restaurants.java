package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class all_the_restaurants extends AppCompatActivity {

    RecyclerView rv;

    RestMainAdapter mainAdapter;

    Intent i;
    String startName,orderBy,endName ;
    int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_the_restaurants);
        i = getIntent();
//        if(i.getStringExtra("filter").toString().equals("")){
//            Log.i("msg","msg");
//        }else if(!i.getStringExtra("filter").toString().equals("")){
//            Log.i("msg","msg1");
//        }

        if(i.hasExtra("filter")){

            startName = i.getStringExtra("filter");
            orderBy = "cuisineType";
            endName = startName + "~";
            Log.i("msg",endName);

        }else if(i.hasExtra("search")){
            startName = i.getStringExtra("search");
            orderBy = "name";
            endName = startName + "~";
            Log.i("msg","msg1");
        }else{
           flag =1;
        }

        //Assigning recycler view
        rv = (RecyclerView) findViewById(R.id.restRecycler);

        rv.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<AllRestaurant> options ;
        if(flag == 0) {
            options =
                    new FirebaseRecyclerOptions.Builder<AllRestaurant>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").orderByChild(orderBy).startAt(startName).endAt(endName), AllRestaurant.class)
                            .build();
            mainAdapter = new RestMainAdapter(options);

            rv.setAdapter(mainAdapter);
        }else if(flag ==1){
            options =
                    new FirebaseRecyclerOptions.Builder<AllRestaurant>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant"), AllRestaurant.class)
                            .build();
            mainAdapter = new RestMainAdapter(options);

            rv.setAdapter(mainAdapter);
        }

//        mainAdapter = new RestMainAdapter(options);
//
//        rv.setAdapter(mainAdapter);
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