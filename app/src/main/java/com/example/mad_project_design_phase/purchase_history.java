package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class purchase_history extends AppCompatActivity {

    RecyclerView ryvPurchase_History;

    PurchaseHistoryAdapter purchasehistoryAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        //Assigning recycler view
        ryvPurchase_History = (RecyclerView) findViewById(R.id.ryvPurchase_History);

        ryvPurchase_History.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<PurchaseHistoryModel> options =
                new FirebaseRecyclerOptions.Builder<PurchaseHistoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PurchaseHistory").child("C1"), PurchaseHistoryModel.class)
                        .build();


        purchasehistoryAdapter = new PurchaseHistoryAdapter(options);

        ryvPurchase_History.setAdapter(purchasehistoryAdapter);
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
}