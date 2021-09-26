package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_details extends AppCompatActivity {
    EditText TextCardType,TextCardNumber,TextCardHolder,TextCardDate,TextCardCVC;
    Button btnHPay;

    DatabaseReference db;
    HAddDetails hadddetails;

    AwesomeValidation awesomeValidation;


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

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation For CardType
        awesomeValidation.addValidation(this,R.id.TextCardType,
                RegexTemplate.NOT_EMPTY,R.string.invalid_type);
        //Add Validation For CardNumber
        awesomeValidation.addValidation(this,R.id.TextCardNumber,
                "[0-9]{19}$",R.string.invalid_number);
        //Add Validation For CardHolder
        awesomeValidation.addValidation(this,R.id.TextCardHolder,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //Add Validation For CardDate
        awesomeValidation.addValidation(this,R.id.TextCardDate,
                RegexTemplate.NOT_EMPTY,R.string.invalid_date);
        //Add Validation For CardNumber
        awesomeValidation.addValidation(this,R.id.TextCardCVC,
                "[0-9]{3}$",R.string.invalid_cvc);

        db = FirebaseDatabase.getInstance().getReference().child("Add Details");
        btnHPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check Validation
                if (awesomeValidation.validate()) {
                    //On Success
                    Toast.makeText(getApplicationContext(), "Data Validated Successfully!!",Toast.LENGTH_SHORT).show();

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
            }else {
                Toast.makeText(getApplicationContext(), "Data Validation Failed!!",Toast.LENGTH_SHORT).show();
            }}
        });
    }
}
