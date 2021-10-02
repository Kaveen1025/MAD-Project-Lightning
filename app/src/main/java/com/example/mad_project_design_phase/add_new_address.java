package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_new_address extends Working_Side {

    EditText et_province, et_city, et_address;
    Button save_address;
    CustomerAddress CusAdd;
    DatabaseReference dbRef;
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    String CustomerID;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);


        et_province = (EditText) findViewById(R.id.et_province);
        et_city = (EditText) findViewById(R.id.et_city);
        et_address =(EditText) findViewById(R.id.et_address);
        save_address = (Button) findViewById(R.id.save_address);
        CusAdd = new CustomerAddress();



        notificationBtn = findViewById(R.id.notificationBtn);
        profileBtn = findViewById(R.id.profileBtn);
        cartBtn = findViewById(R.id.cartBtn);


        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navvd);
        toolbar = findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer Address").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73");
        auth = FirebaseAuth.getInstance();





        save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Province = et_province.getText().toString().trim();
                String City = et_city.getText().toString().trim();
                String Address = et_address.getText().toString().trim();

                CusAdd.setProvince(Province);
                CusAdd.setCity(City);
                CusAdd.setAddress(Address);

                dbRef.push().setValue(CusAdd);  //firebaseUser.getUid()).
                Toast.makeText(add_new_address.this, "Address Added Successfully", Toast.LENGTH_SHORT).show();


//                Toast.makeText(add_new_address.this, "Address Add Failed", Toast.LENGTH_SHORT).show();
                ClearControls();
            }


        });

    }

    public void ClearControls() {

        et_province.setText("");
        et_city.setText("");
        et_address.setText("");


    }

    protected void onResume() {
        super.onResume();

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_new_address.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_new_address.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_new_address.this,edit_cart.class);
                startActivity(i);
            }
        });
    }

    }


