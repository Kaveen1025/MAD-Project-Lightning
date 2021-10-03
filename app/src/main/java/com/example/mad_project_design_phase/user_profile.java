package com.example.mad_project_design_phase;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class user_profile extends AppCompatActivity {

    EditText et_firstname, et_last_name, et_email, up_phone, up_address;
    Button up_edit, btn_View_Address;
    DatabaseReference dbRef;
    //    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String CustomerID;
    CircleImageView profile_image;



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
        profile_image = findViewById(R.id.profile_image);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("AhLIouE0q9YwizgYAgbgglsnyuC3");// id **

       dbRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                   et_firstname.setText(snapshot.child("firstName").getValue().toString());
                   et_last_name.setText(snapshot.child("lastName").getValue().toString());
                   et_email.setText(snapshot.child("email").getValue().toString());
                   up_phone.setText(snapshot.child("phoneNumber").getValue().toString());
                   up_address.setText(snapshot.child("address").getValue().toString());
                   String link = snapshot.child("pimage").getValue(String.class);
                   Picasso.get().load(link).into(profile_image);


           }
           @Override
           public void onCancelled(@NonNull @NotNull DatabaseError error) {
               Log.i("d", String.valueOf(error));
           }
       });



    }

}