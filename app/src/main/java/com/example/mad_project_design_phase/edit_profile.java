package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class edit_profile extends AppCompatActivity {

    EditText edit_fn, edit_ln, edit_email,  edit_add,  edit_phone, current_password, new_password,confirm_password;
    Button update_btn, delete_btn;
    DatabaseReference dbRef;
    //    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);
        edit_fn = findViewById(R.id.edit_fn);
        edit_ln = findViewById(R.id.edit_ln);
        edit_email= findViewById(R.id.edit_email);
        edit_add = findViewById(R.id.edit_add);
        edit_phone = findViewById(R.id.edit_phone);
        current_password = findViewById(R.id.current_password);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        //btnUpdate

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FirstName = edit_fn.getText().toString().trim();
                String LastName  = edit_ln.getText().toString().trim();
                String Email     = edit_email.getText().toString().trim();
                String Address   = edit_add.getText().toString().trim();
                Integer PhoneNumber = Integer.parseInt(edit_phone.getText().toString().trim());

                UpdateUserProfile(FirstName, LastName, Email, Address, PhoneNumber);

                //change password????

            }


        });


    }


    public void GetUserProfile(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("Customer.id");// id **
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){

                    edit_fn.setText(dataSnapshot.child("FirstName").getValue().toString());
                    edit_ln.setText(dataSnapshot.child("LastName").getValue().toString());
                    edit_email.setText(dataSnapshot.child("Email").getValue().toString());
                    edit_phone.setText(dataSnapshot.child("Address").getValue().toString());
                    edit_add.setText(dataSnapshot.child("PhoneNumber").getValue().toString());

                }else{

                    Toast.makeText(edit_profile.this, "No Source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Update Profile

    private void UpdateUserProfile(String FirstName, String LastName, String Email, String Address, Integer PhoneNumber) {

        HashMap customer = new HashMap();
        customer.put("FirstName", FirstName);
        customer.put("LastName", LastName);
        customer.put("Email", Email);
        customer.put("Address", Address);
        customer.put("PhoneNumber", PhoneNumber);

        //** change password? ->

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("userID"); //*userID?
        dbRef.updateChildren(customer).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    Toast.makeText(edit_profile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(edit_profile.this, "Profile Update Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void DeleteUserprofile(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("userId")){ // ** userId

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("userId"); // ** userId
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(), "Profile deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



    }
}