package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class customise_food extends AppCompatActivity {

    CheckBox op1,op2,op3,op4;
    TextView p1,p2,p3,p4,totalPrice,name, des,url;
    ImageView food;
    Button addToCart, showCal;
    String total;

    DatabaseReference ref1, ref2, ref3, ref4, ref, ref5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise_food);

        op1 = findViewById(R.id.chk_option1);
        op2 = findViewById(R.id.chk_option2);
        op3 = findViewById(R.id.chk_option3);
        op4 = findViewById(R.id.chk_option4);
        p1 = findViewById(R.id.tv_p1);
        p2 = findViewById(R.id.tv_p2);
        p3 = findViewById(R.id.tv_p3);
        p4 = findViewById(R.id.tv_p4);
        name = findViewById(R.id.txt_cuzName);
        des = findViewById(R.id.txt_cuzDes);
        url = findViewById(R.id.customize_url);
        totalPrice = findViewById(R.id.txt_TotalPrice);
        addToCart = findViewById(R.id.btn_addtocart);
        showCal = findViewById(R.id.btn_showCal);

        ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("CustomizeOption");
        ref1 = ref.child("op1");
        ref2 = ref.child("op2");
        ref3 = ref.child("op3");
        ref4 = ref.child("op4");
        ref5 = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Food").child("F1").child("FoodDetails");

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                String opt1 =datasnapshot.child("optionName").getValue().toString();
                String price1 = datasnapshot.child("price").getValue().toString();
                op1.setText(opt1);
                p1.setText(price1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String opt2 = datasnapshot.child("optionName").getValue().toString();
                String price2 = datasnapshot.child("price").getValue().toString();
                op2.setText(opt2);
                p2.setText(price2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String opt3 = datasnapshot.child("optionName").getValue().toString();
                String price3 = datasnapshot.child("price").getValue().toString();
                op3.setText(opt3);
                p3.setText(price3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String opt4 = datasnapshot.child("optionName").getValue().toString();
                String price4 = datasnapshot.child("price").getValue().toString();
                op4.setText(opt4);
                p4.setText(price4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                 total = datasnapshot.child("price").getValue().toString();
                totalPrice.setText(total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String foodDes = dataSnapshot.child("description").getValue().toString();
                String foodname = dataSnapshot.child("name").getValue().toString();
                String link = dataSnapshot.child("foodImage").getValue(String.class);
                url.setText(link);
                Picasso.get()
                        .load(link)
                        .into(food);
                    name.setText(foodname);
               des.setText(foodDes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        op1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Float newtotal = 0.00f;
                if(b){
                     newtotal = Float.parseFloat(p1.getText().toString()) + Float.parseFloat(totalPrice.getText().toString());
                }else{
                    newtotal = Float.parseFloat(totalPrice.getText().toString())  - Float.parseFloat(p1.getText().toString()) ;
                }

                totalPrice.setText(String.valueOf(newtotal));

            }
        });

        op2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Float newtotal;
                if(b){
                    newtotal = Float.parseFloat(p2.getText().toString()) + Float.parseFloat(totalPrice.getText().toString());
                }else{
                    newtotal = Float.parseFloat(totalPrice.getText().toString())  - Float.parseFloat(p2.getText().toString()) ;
                }


                totalPrice.setText(String.valueOf(newtotal));

            }
        });

        op3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Float newtotal;
                if(b){
                    newtotal = Float.parseFloat(p3.getText().toString()) + Float.parseFloat(totalPrice.getText().toString());
                }else{
                    newtotal = Float.parseFloat(totalPrice.getText().toString())  - Float.parseFloat(p3.getText().toString()) ;
                }


                totalPrice.setText(String.valueOf(newtotal));

            }
        });

        op4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Float newtotal;
                if(b){
                    newtotal = Float.parseFloat(p4.getText().toString()) + Float.parseFloat(totalPrice.getText().toString());
                }else{
                    newtotal = Float.parseFloat(totalPrice.getText().toString())  - Float.parseFloat(p4.getText().toString()) ;
                }

                totalPrice.setText(String.valueOf(newtotal));

            }
        });

        showCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent calCalories = new Intent(customise_food.this,show_calories.class);
                HashMap<String,Integer> selectFoodList = new HashMap();
                if(op1.isChecked()){
                    calCalories.putExtra("op1",op1.getText().toString());
                    calCalories.putExtra("op1Price",p1.getText().toString());
                }
                if(op2.isChecked()){
                    calCalories.putExtra("op2",op2.getText().toString());
                    calCalories.putExtra("op2Price",p2.getText().toString());
                }
                if(op3.isChecked()){
                    calCalories.putExtra("op3",op3.getText().toString());
                    calCalories.putExtra("op3Price",p3.getText().toString());
                }
                if(op4.isChecked()){
                    calCalories.putExtra("op4",op4.getText().toString());
                    calCalories.putExtra("op4Price",p4.getText().toString());
                }
                startActivity(calCalories);

            }
        });

    }
}