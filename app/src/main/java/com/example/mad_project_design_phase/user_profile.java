package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

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




        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("qbNPPUl4mMc3ghQc6Hpth2g3yhs1");// id **

       dbRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                   et_firstname.setText(snapshot.child("firstName").getValue().toString());
                   et_last_name.setText(snapshot.child("lastName").getValue().toString());
                   et_email.setText(snapshot.child("email").getValue().toString());
                   up_phone.setText(snapshot.child("phoneNumber").getValue().toString());
                   up_address.setText(snapshot.child("address").getValue().toString());

           }

           @Override
           public void onCancelled(@NonNull @NotNull DatabaseError error) {
               Log.i("d", String.valueOf(error));
           }
       });



    }

}