package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class view_address extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewAddressAdapter viewAddressAdapter;

    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_address_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //fetching data

        FirebaseRecyclerOptions<CustomerAddress> options =
                new FirebaseRecyclerOptions.Builder<CustomerAddress>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customer Address").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73"), CustomerAddress.class)
                        .build();

       viewAddressAdapter = new ViewAddressAdapter(options);
        recyclerView.setAdapter(viewAddressAdapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), add_new_address.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        viewAddressAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewAddressAdapter.startListening();
    }


}