package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_profile extends AppCompatActivity {

    EditText et_firstname, et_last_name, et_email, up_phone, up_address;
    Button up_edit, btn_View_Address;
    DatabaseReference dbRef;
    //    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        et_firstname = findViewById(R.id.et_firstname);
        et_last_name = findViewById(R.id.et_last_name);
        et_email= findViewById(R.id.et_email);
        up_phone = findViewById(R.id.up_phone);
        up_address = findViewById(R.id.up_address);
        up_edit = findViewById(R.id.up_edit);
        btn_View_Address = findViewById(R.id.btn_View_Address);
    }

    public void ViewUserProfile(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("Customer.id");// id **
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){

                   et_firstname.setText(dataSnapshot.child(" FirstName").getValue().toString());
                   et_last_name.setText(dataSnapshot.child("LastName").getValue().toString());
                   et_email.setText(dataSnapshot.child(" Email").getValue().toString());
                   up_phone.setText(dataSnapshot.child("PhoneNumber").getValue().toString());
                   up_address.setText(dataSnapshot.child("Address").getValue().toString());

                }else{

                    Toast.makeText(user_profile.this, "No Source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}