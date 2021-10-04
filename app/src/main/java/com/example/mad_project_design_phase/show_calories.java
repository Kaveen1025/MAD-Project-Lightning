package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

        if(intent.hasExtra("op1")){
            String op = intent.getStringExtra("op1");
            String cal = intent.getStringExtra("cal1");
            j.put(op,Integer.parseInt(cal));
        }
         if(intent.hasExtra("op2")){
             String op = intent.getStringExtra("op2");
             String cal = intent.getStringExtra("cal2");
             j.put(op,Integer.parseInt(cal));
         }
         if(intent.hasExtra("op3")){
             String op = intent.getStringExtra("op3");
             String cal = intent.getStringExtra("cal3");
             j.put(op,Integer.parseInt(cal));
         }
         if(intent.hasExtra("op4")){
             String op = intent.getStringExtra("op4");
             String cal = intent.getStringExtra("cal4");
             j.put(op,Integer.parseInt(cal));
         }

//         if(j.isEmpty()){
//             TotalCalories.setText(intent.getStringExtra("InitialCal") + " kcal");
//         }


//      j.put(intent.getStringExtra("op1"),Integer.parseInt(intent.getStringExtra("cal1")));
//      j.put(intent.getStringExtra("op2"),Integer.parseInt(intent.getStringExtra("cal2")));
//      j.put(intent.getStringExtra("op3"),Integer.parseInt(intent.getStringExtra("cal3")));
//      j.put(intent.getStringExtra("op4"),Integer.parseInt(intent.getStringExtra("cal4")));



//
//     j.put("Spaghetti",129);
//          j.put("Chicken",160);
//        j.put("Cheese",189);
//       j.put("Creamy sauce", 62);
//
        // need image
        FoodName.setText(intent.getStringExtra("FoodName").toString());
        count = 0;
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