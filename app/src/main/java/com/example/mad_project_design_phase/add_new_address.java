package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_new_address extends AppCompatActivity {

    EditText et_province, et_city, et_address;
    Button save_address;
    CustomerAddress CusAdd;
    DatabaseReference dbRef;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        Toast.makeText(this, "FireBase Connection Success", Toast.LENGTH_SHORT).show();

        et_province = findViewById(R.id.et_province);
        et_city = findViewById(R.id.et_city);
        et_address = findViewById(R.id.et_address);
        save_address = findViewById(R.id.save_address);
        //CusAdd = new CustomerAddress();
    }

//    protected void onResume(){
//
//        save_address.setOnClickListner(new View.View.OnClickListener())
//    }

    //method to clear all user inputs

    public void ClearControls() {

        et_province.setText("");
        et_city.setText("");
        et_address.setText("");


    }

    protected void onResume() {

        super.onResume();
        save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Province = et_province.getText().toString();
                String City = et_city.getText().toString();
                String Address = et_address.getText().toString();


                CusAdd = new CustomerAddress(Province,City,Address);

                dbRef.child(firebaseUser.getUid()).setValue(CusAdd);
                Toast.makeText(add_new_address.this, "Address Added Successfully", Toast.LENGTH_SHORT).show();


                Toast.makeText(add_new_address.this, "Address Add Failed", Toast.LENGTH_SHORT).show();
                ClearControls();
            }


        });


    }

//    public void CreateAddress(View view) {
//
//        dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerAddress");
//
//
//        if (TextUtils.isEmpty(et_province.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please Enter Your Province", Toast.LENGTH_LONG).show();
//
//        } else if (TextUtils.isEmpty(et_city.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please Enter Your City", Toast.LENGTH_LONG).show();
//
//        } else if (TextUtils.isEmpty(et_address.getText().toString())) {
//            Toast.makeText(getApplicationContext(), "Please Enter an Address", Toast.LENGTH_LONG).show();
//
//        } else {
////     take inputs from the user and assigning to this instance(stuObj)
//
//            CusAdd.setProvince(et_province.getText().toString().trim());
//            CusAdd.setCity(et_city.getText().toString().trim());
//            CusAdd.setAddress(et_address.getText().toString().trim());
//
//
//            //insert into database
//
//            dbRef.push().setValue(CusAdd);
//            //dbRef.child("NewAddress").setValue(CusAdd);
//
//            //feedback to the user via toast
//
//            Toast.makeText(getApplicationContext(), "New Address Added Successfully", Toast.LENGTH_SHORT).show();
//            ClearControls();
//        }
//
//    }

}
