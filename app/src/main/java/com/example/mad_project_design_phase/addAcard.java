package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addAcard extends AppCompatActivity {
    EditText txtCardType,txtCardNumber,txtCardHolder,txtCardDate;
    Button btnSave;

    DatabaseReference db;
    HAddCard haddcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acard);
        txtCardType=(EditText) findViewById(R.id.txtCardType);
        txtCardNumber=(EditText) findViewById(R.id.txtCardNumber);
        txtCardHolder=(EditText) findViewById(R.id.txtCardHolder);
        txtCardDate=(EditText) findViewById(R.id.txtCardDate);

        btnSave=(Button) findViewById(R.id.btnSave);

        db = FirebaseDatabase.getInstance().getReference().child("HAddCard");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haddcard.setCardType(txtCardType.getText().toString().trim());
                haddcard.setCardNumber(txtCardNumber.getText().toString().trim());
                haddcard.setCardHolder(txtCardHolder.getText().toString().trim());
                haddcard.setCardDate(txtCardDate.getText().toString().trim());

                db.child("haddcard1").setValue(haddcard);
                Toast.makeText(addAcard.this, "Data inserted successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}