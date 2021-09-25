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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.HashMap;

public class edit_card extends AppCompatActivity {
    EditText edtCardType,edtCardNumber,edtCardHolder,edtCardDate;
    Button btnUpdate;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        edtCardType=(EditText) findViewById(R.id.edtCardType);
        edtCardNumber=(EditText) findViewById(R.id.edtCardNumber);
        edtCardHolder=(EditText) findViewById(R.id.edtCardHolder);
        edtCardDate=(EditText) findViewById(R.id.edtCardDate);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        db = FirebaseDatabase.getInstance().getReference().child("Payment").child("DgaCUSQDSOOgGnoFmv3ojR3vpH73").child("AdgeASFDGnjvfn4vf");
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
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CardDate = edtCardDate.getText().toString();


                updateCard(CardDate);

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
