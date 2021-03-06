package com.example.mad_project_design_phase;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class purchase_history extends Working_Side{

    RecyclerView ryvPurchase_History;

    PurchaseHistoryAdapter purchasehistoryAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;
    TextView txtTotal;
    String CustomerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        CustomerID = CustomerDetails.getCustomerID();
//        CustomerID = "C1";
        notificationBtn = findViewById(R.id.notificationBtn);
        profileBtn = findViewById(R.id.profileBtn);
        cartBtn = findViewById(R.id.cartBtn);

        txtTotal = findViewById(R.id.txtTotal);

        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navvd);
        toolbar = findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //Assigning recycler view
        ryvPurchase_History = (RecyclerView) findViewById(R.id.ryvPurchase_History);

        ryvPurchase_History.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<PurchaseHistoryModel> options =
                new FirebaseRecyclerOptions.Builder<PurchaseHistoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PurchaseHistory").child(CustomerID), PurchaseHistoryModel.class)
                        .build();


        purchasehistoryAdapter = new PurchaseHistoryAdapter(options);

        ryvPurchase_History.setAdapter(purchasehistoryAdapter);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver, new IntentFilter("Total Spending"));


    }

    @Override
    protected void onStart() {
        super.onStart();

        purchasehistoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        purchasehistoryAdapter.stopListening();
    }


    protected void onResume() {
        super.onResume();


        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(purchase_history.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(purchase_history.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(purchase_history.this,edit_cart.class);
                startActivity(i);
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int TotalPrice = intent.getIntExtra("TotalPrice", 0);
            txtTotal.setText("LKR. " + TotalPrice + ".00");
        }
    };


}