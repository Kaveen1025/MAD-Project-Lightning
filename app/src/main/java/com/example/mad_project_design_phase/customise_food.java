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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class customise_food extends AppCompatActivity {

    CheckBox op1,op2,op3,op4;
    TextView p1,p2,p3,p4,totalPrice,name, des;

    ImageView food;
    Button addToCart, showCal;
    String total,foodDes,link,price,foodname;

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
        food = findViewById(R.id.imgfood);
        name = findViewById(R.id.txt_cuzName);
        des = findViewById(R.id.txt_cuzDes);
        //price = findViewById(R.id.customize_url);
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
                foodDes = dataSnapshot.child("description").getValue().toString();
                foodname = dataSnapshot.child("name").getValue().toString();
                link = dataSnapshot.child("foodImage").getValue(String.class);
                price = dataSnapshot.child("price").getValue().toString();
                    name.setText(foodname);
                Glide.with(food.getContext())
                        .load(link)
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(food);
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

        addToCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cartFood  cf =new cartFood(foodDes,link,foodname,price);

                ref = FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1");
                ref.child(foodname).setValue(cf).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(customise_food.this, "Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                if(op1.isChecked()){
                    String op11 = op1.getText().toString();
                    String p11 = p1.getText().toString();
                    foodOptions op1 = new foodOptions(op11,p11);
                    ref = FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1").child(foodname);
                    ref.child("options").child("op1").setValue(op1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(customise_food.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                if(op2.isChecked()){
                    String op12 = op2.getText().toString();
                    String p12 = p2.getText().toString();
                    foodOptions op2 = new foodOptions(op12,p12);
                    ref = FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1").child(foodname);
                    ref.child("options").child("op2").setValue(op2).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(customise_food.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if(op3.isChecked()){
                    String op13 = op3.getText().toString();
                    String p13 = p3.getText().toString();
                    foodOptions op3 = new foodOptions(op13,p13);
                    ref = FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1").child(foodname);
                    ref.child("options").child("op3").setValue(op3).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(customise_food.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if(op4.isChecked()){
                    String op14 = op4.getText().toString();
                    String p14 = p4.getText().toString();
                    foodOptions op4 = new foodOptions(op14,p14);
                    ref = FirebaseDatabase.getInstance().getReference().child("FoodCart").child("C1").child("R1").child(foodname);
                    ref.child("options").child("op4").setValue(op4).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(customise_food.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

    }
}