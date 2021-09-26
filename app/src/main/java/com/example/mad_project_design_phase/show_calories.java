package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class show_calories extends AppCompatActivity {

    TextView FoodName,TotalCalories;
    int count = 0;
    int totalCalories = 0;

    ImageButton closeBtn;
    int[] optionNames = {
      R.id.option1Name,R.id.option2Name,R.id.option3Name,R.id.option4Name
    };

    int[] optionCal = {
      R.id.option1Cal,R.id.option2Cal,R.id.option3Cal,R.id.option4cal
    };


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_calories);

        FoodName = findViewById(R.id.foodName);
        TotalCalories = findViewById(R.id.TotalCalories);
        closeBtn = findViewById(R.id.closeBtn22);

        HashMap<String,Integer> j = new HashMap<>();
       Intent intent = getIntent();

      j.put(intent.getStringExtra("Opt1Value"),Integer.parseInt(intent.getStringExtra("calories1")));
      j.put(intent.getStringExtra("Opt2Value"),Integer.parseInt(intent.getStringExtra("calories2")));
      j.put(intent.getStringExtra("Opt3Value"),Integer.parseInt(intent.getStringExtra("calories3")));
      j.put(intent.getStringExtra("Opt4Value"),Integer.parseInt(intent.getStringExtra("calories4")));
        for(Map.Entry<String, Integer> set : j.entrySet()) {
            TextView s = findViewById(optionNames[count]);
            s.setText(set.getKey());
            TextView d = findViewById(optionCal[count]);
            d.setText(String.valueOf(set.getValue()) + " kcal");
            totalCalories += set.getValue();
            count++;
        }
        TotalCalories.setText(String.valueOf(totalCalories) + " kcal");
    }


    @Override
    protected void onResume() {
        super.onResume();

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(show_calories.this,customise_food.class);
                startActivity(intent1);
            }
        });

    }
}