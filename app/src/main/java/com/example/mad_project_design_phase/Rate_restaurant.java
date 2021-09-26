package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Rate_restaurant extends AppCompatActivity {

    Button submitReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_restaurant);
        submitReview = findViewById(R.id.submitReview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("d", "onClick: dd");
            }
        });
    }
}