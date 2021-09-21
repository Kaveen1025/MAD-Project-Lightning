package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_details extends AppCompatActivity {
    EditText TextCardType,TextCardNumber,TextCardHolder,TextCardDate,TextCardCVC;
    Button btnHPay;

    DatabaseReference db;
    HAddDetails hadddetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        TextCardType=(EditText) findViewById(R.id.TextCardType);
        TextCardNumber=(EditText) findViewById(R.id.TextCardNumber);
        TextCardHolder=(EditText) findViewById(R.id.TextCardHolder);
        TextCardDate=(EditText) findViewById(R.id.TextCardDate);
        TextCardCVC=(EditText) findViewById(R.id.TextCardCVC);

        btnHPay=(Button) findViewById(R.id.btnHPay);

        db = FirebaseDatabase.getInstance().getReference().child("Add Details");
        btnHPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hadddetails = new HAddDetails();
                hadddetails.setCdType(TextCardType.getText().toString().trim());
                hadddetails.setCdNumber(TextCardNumber.getText().toString().trim());
                hadddetails.setCdHolder(TextCardHolder.getText().toString().trim());
                hadddetails.setCdDate(TextCardDate.getText().toString().trim());
                hadddetails.setCdCVC(TextCardCVC.getText().toString().trim());

                db.child("1").push().setValue(hadddetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(add_details.this, "Details entered successfully", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(add_details.this, "Failed to enter details", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
