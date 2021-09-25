package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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


        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("qbNPPUl4mMc3ghQc6Hpth2g3yhs1");// id **
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){

                    edit_fn.setText(dataSnapshot.child("firstName").getValue().toString());
                    edit_ln.setText(dataSnapshot.child("lastName").getValue().toString());
                    edit_email.setText(dataSnapshot.child("email").getValue().toString());
                    edit_phone.setText(dataSnapshot.child("address").getValue().toString());
                    edit_add.setText(dataSnapshot.child("phoneNumber").getValue().toString());

                }else{

                    Toast.makeText(edit_profile.this, "No Source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("d", String.valueOf(error));
            }
        });

        //btnUpdate

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = edit_fn.getText().toString().trim();
                String lastName  = edit_ln.getText().toString().trim();
                String email     = edit_email.getText().toString().trim();
                String address   = edit_add.getText().toString().trim();
                String phoneNumber =edit_phone.getText().toString().trim();

                UpdateUserProfile(firstName, lastName, email, address, phoneNumber);

                //change password????

            }


        });





    }







    //Update Profile

    private void UpdateUserProfile(String FirstName, String LastName, String Email, String Address, String PhoneNumber) {

        HashMap customer = new HashMap();
        customer.put("firstName", FirstName);
        customer.put("lastName", LastName);
        customer.put("email", Email);
        customer.put("address", Address);
        customer.put("phoneNumber", PhoneNumber);

        //** change password? ->

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("qbNPPUl4mMc3ghQc6Hpth2g3yhs1"); //*userID?
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
                if(dataSnapshot.hasChild("qbNPPUl4mMc3ghQc6Hpth2g3yhs1")){ // ** userId

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("qbNPPUl4mMc3ghQc6Hpth2g3yhs1"); // ** userId
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