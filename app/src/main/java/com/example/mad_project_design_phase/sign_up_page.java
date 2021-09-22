package com.example.mad_project_design_phase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up_page extends AppCompatActivity {

    EditText firstname, lastname, input_email, postal_address, phone_number,password, et_confirm_password;
    Button button_signup;
    Customer CusObj;
    DatabaseReference dbRef;
//    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        Toast.makeText(this, "FireBase Connection Success", Toast.LENGTH_SHORT).show();

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        input_email = findViewById(R.id.input_email);
        postal_address = findViewById(R.id.postal_address);
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        et_confirm_password = findViewById(R.id.et_confirm_password);
        button_signup = findViewById(R.id.button_signup);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        auth = FirebaseAuth.getInstance();

    }

    //method to clear all user inputs

    public void ClearControls(){

        firstname.setText("");
        lastname.setText("");
        input_email.setText("");
        postal_address.setText("");
        phone_number.setText("");
        password.setText("");
        et_confirm_password.setText("");

    }

    protected void onResume(){

        super.onResume();
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FirstName = firstname.getText().toString().trim();
                String LastName = lastname.getText().toString().trim();
                String Email = input_email.getText().toString().trim();
                String Address = postal_address.getText().toString().trim();

                /////// input only string value don't input integer values///////////////
                String PhoneNumber = phone_number.getText().toString().trim();
                /////////////////////////////////
                String Password = password.getText().toString().trim();

                //phone number
                String ConfirmPassword = et_confirm_password.getText().toString();

//                if(TextUtils.isEmpty(FirstName)){
//                    firstname.setError("First Name is Required");
//                        return;
//                }
//
//                if(TextUtils.isEmpty(LastName)){
//                    lastname.setError("Last Name is Required");
//                   return;
//                }
//
//                if(TextUtils.isEmpty(Email)){
//                   input_email.setError("Email is Required");
//                  return;
//                }
//                if(TextUtils.isEmpty(Address)){
//                   postal_address.setError("Address is Required");
//                    return;
//                }
////               if (!TextUtils.isEmpty(PhoneNumber)) {
////
////                   phone_number.setError("Phone Number is Required");
//                       return;
////                }
//                if(TextUtils.isEmpty(Password)){
//                    password.setError("Password is Required");
//                   return;
//                }
//                if(Password.length()<8){
//                    password.setError("Password must be >= 8 characters");
//                   return;
//                }
//                if(TextUtils.isEmpty(ConfirmPassword)){
//                    et_confirm_password.setError("Please Confirm Your Password");
//                   return;
//                }

//                register the user in firebase.



                auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(sign_up_page.this, "User Created", Toast.LENGTH_SHORT).show();
                            firebaseUser = auth.getCurrentUser();

                            CusObj = new Customer(FirstName,LastName,Email,Address,PhoneNumber,Password);

                            dbRef.child(firebaseUser.getUid()).setValue(CusObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(sign_up_page.this, "Customer Added Successfully", Toast.LENGTH_SHORT).show();

                                    }else{

                                        Toast.makeText(sign_up_page.this, "Customer Added Failed", Toast.LENGTH_SHORT).show();
                                        ClearControls();

                                    }
                                }
                            });


                        }else{

                            Toast.makeText(sign_up_page.this, "User Create failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                });




            }
        });
    }

//    public void CreateCustomer(View view){
//
//        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
//
//        try{
//
//            if(TextUtils.isEmpty(firstname.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please Enter First Name", Toast.LENGTH_LONG).show();
//
//            else if(TextUtils.isEmpty(lastname.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please Enter Last Name", Toast.LENGTH_LONG).show();
//
//            else if(TextUtils.isEmpty(input_email.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please Enter an Email", Toast.LENGTH_LONG).show();
//
//            else if(TextUtils.isEmpty(postal_address.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please Enter Postal Address", Toast.LENGTH_LONG).show();
//
//            //phone Number?
//            else if(TextUtils.isEmpty(password.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please Enter an Email", Toast.LENGTH_LONG).show();
//
//            else if(TextUtils.isEmpty(et_confirm_password.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please Confirm Your Password", Toast.LENGTH_LONG).show();
//
//            else{
//
////     take inputs from the user and assigning to this instance(CusObj)
//
//                CusObj.setFirstName(firstname.getText().toString().trim());
//                CusObj.setLastName(lastname.getText().toString().trim());
//                CusObj.setEmail(input_email.getText().toString().trim());
//                CusObj.setPhoneNumber(Integer.parseInt( phone_number.getText().toString().trim()));
//                CusObj.setAddress(postal_address.getText().toString().trim());
//                CusObj.setPassword(password.getText().toString().trim());
//                //insert into database
//
//                dbRef.push().setValue(CusObj);
//                //dbRef.child("Customer1").setValue(CusObj);
//
//                //feedback to the user via toast
//
//                Toast.makeText(getApplicationContext(), "Customer Added Successfully", Toast.LENGTH_SHORT).show();
//                ClearControls();
//            }
//
//        }
//        catch (NumberFormatException e){
//
//            Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
//        }
//    }
//

}