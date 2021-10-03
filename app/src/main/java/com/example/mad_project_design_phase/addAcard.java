package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;

public class addAcard extends AppCompatActivity {

//    //Initialize Variables
//    EditText txtCardType,txtCardNumber,txtCardHolder,txtCardDate;
//    Button btnSave;
//
//    AwesomeValidation awesomeValidation;
//
//    DatabaseReference db;
//    HAddCard haddcard;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_acard);
//
//        //Assign Variable
//        txtCardType=(EditText) findViewById(R.id.txtCardType);
//        txtCardNumber=(EditText) findViewById(R.id.txtCardNumber);
//        txtCardHolder=(EditText) findViewById(R.id.txtCardHolder);
//        txtCardDate=(EditText) findViewById(R.id.txtCardDate);
//
//        btnSave=(Button) findViewById(R.id.btnSave);
//
//        //Initialize Validation Style
//        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//
//        //Add Validation For CardType
//        awesomeValidation.addValidation(this,R.id.txtCardType,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_type);
//        //Add Validation For CardNumber
//        awesomeValidation.addValidation(this,R.id.txtCardNumber, "[0-9]{19}$",R.string.invalid_number);
//        //Add Validation For CardHolder
//        awesomeValidation.addValidation(this,R.id.txtCardHolder,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
//        //Add Validation For CardDate
//        awesomeValidation.addValidation(this,R.id.txtCardDate,
//                RegexTemplate.NOT_EMPTY,R.string.invalid_date);
//
//
//        db = FirebaseDatabase.getInstance().getReference().child("Payment");
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Check Validation
//                if (awesomeValidation.validate()) {
//                    //On Success
//                    Toast.makeText(getApplicationContext(), "Data Validated Successfully!!",Toast.LENGTH_SHORT).show();
//
//                haddcard = new HAddCard();
//                haddcard.setCardType(txtCardType.getText().toString().trim());
//                haddcard.setCardNumber(txtCardNumber.getText().toString().trim());
//                haddcard.setCardHolder(txtCardHolder.getText().toString().trim());
//                haddcard.setCardDate(txtCardDate.getText().toString().trim());
//
//                db.child("DgaCUSQDSOOgGnoFmv3ojR3vpH73").push().setValue(haddcard).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                            Toast.makeText(addAcard.this, "Card inserted successfully", Toast.LENGTH_LONG).show();
//
//                        else
//                            Toast.makeText(addAcard.this, "Failed", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//            }else {
//                    Toast.makeText(getApplicationContext(), "Data Validation Failed!!",Toast.LENGTH_SHORT).show();
//                }}
//        });
//    }
}