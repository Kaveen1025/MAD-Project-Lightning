package com.example.mad_project_design_phase;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class notification extends AppCompatActivity {

    RecyclerView ryvNotification;

    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //Assigning recycler view
        ryvNotification = (RecyclerView) findViewById(R.id.ryvNotification);

        ryvNotification.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<NotificationModel> options =
                new FirebaseRecyclerOptions.Builder<NotificationModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notifications").child("C1"), NotificationModel.class)
                        .build();


        notificationAdapter = new NotificationAdapter(options);

        ryvNotification.setAdapter(notificationAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();

        notificationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notificationAdapter.stopListening();
    }
}