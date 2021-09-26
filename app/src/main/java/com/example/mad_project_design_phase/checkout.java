package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class checkout extends AppCompatActivity {

    TextView txtAddress,txtName,txtPhone,txtEmail;
    Button btnNext;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        txtAddress=(TextView) findViewById(R.id.txtAddress);
        txtName=(TextView) findViewById(R.id.txtName);
        txtPhone=(TextView) findViewById(R.id.txtPhone);
        txtEmail=(TextView) findViewById(R.id.txtEmail);

        btnNext = (Button) findViewById(R.id.btnNext);

        db = FirebaseDatabase.getInstance().getReference().child("Customer").child("FGEOGNmjF4Wg9B80L7NhFPx2pvd2");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {

                    txtAddress.setText(snapshot.child("address").getValue().toString());
                    txtName.setText(snapshot.child("firstName").getValue().toString());
                    txtPhone.setText(snapshot.child("phoneNumber").getValue().toString());
                    txtEmail.setText(snapshot.child("email").getValue().toString());

                }
                else {
                    Toast.makeText(checkout.this,"No Card to Display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}