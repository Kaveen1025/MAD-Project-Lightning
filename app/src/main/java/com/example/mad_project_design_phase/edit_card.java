package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.HashMap;

public class edit_card extends Working_Side {
    EditText edtCardType,edtCardNumber,edtCardHolder,edtCardDate;
    Button btnUpdate,btnSelect;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;

    DatabaseReference db;
    String CustomerID,CardID;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        intent = getIntent();
        CardID = intent.getStringExtra("CardID");
//        CustomerID = CustomerDetails.getCustomerID();

        CustomerID = "DgaCUSQDSOOgGnoFmv3ojR3vpH73";



        CustomerID = CustomerDetails.getCustomerID();
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


        edtCardType=(EditText) findViewById(R.id.edtCardType);
        edtCardNumber=(EditText) findViewById(R.id.edtCardNumber);
        edtCardHolder=(EditText) findViewById(R.id.edtCardHolder);
        edtCardDate=(EditText) findViewById(R.id.edtCardDate);
        btnUpdate = (Button) findViewById(R.id.btnPayC);
        btnSelect = findViewById(R.id.btnSelect);
        db = FirebaseDatabase.getInstance().getReference().child("Payment").child(CustomerID).child(CardID);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
//
                    edtCardType.setText(snapshot.child("cardType").getValue().toString());
                    edtCardNumber.setText(snapshot.child("cardNumber").getValue().toString());
                    edtCardHolder.setText(snapshot.child("cardHolder").getValue().toString());
                    edtCardDate.setText(snapshot.child("cardDate").getValue().toString());

                }
                else {
                    Toast.makeText(edit_card.this,"No Card to Display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(edit_card.this,card_details.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CardDate = edtCardDate.getText().toString();


                updateCard(CardDate);

            }
        });


        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(edit_card.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(edit_card.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(edit_card.this,edit_cart.class);
                startActivity(i);
            }
        });

    }

    private void updateCard(String CardDate) {
        HashMap card = new HashMap();
        card.put("cardDate",CardDate);


        db.updateChildren(card).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull @NotNull Task task) {
                if(task.isSuccessful()){

                    Toast.makeText(edit_card.this, "Card updated successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(edit_card.this, "Error has been occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
