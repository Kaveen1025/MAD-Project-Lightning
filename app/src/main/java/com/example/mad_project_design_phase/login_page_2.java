package com.example.mad_project_design_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_page_2 extends AppCompatActivity {

    EditText CustomerEmail, CustomerPassword;
    Button SignUp, SignIn;
    DatabaseReference ref;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String customerPassword;
    String customerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);

        CustomerEmail = findViewById(R.id.CustomerEmail);
        CustomerPassword = findViewById(R.id.CustomerPassword);
        SignUp = findViewById(R.id.SignUp);
        SignIn = findViewById(R.id.SignIn);
        auth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                     customerEmail = CustomerEmail.getText().toString();
                     customerPassword = CustomerPassword.getText().toString();
                     loginCustomer(customerEmail, customerPassword);
                }catch (Exception e){
                    Toast.makeText(login_page_2.this, "Please Enter your Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signupIntent = new Intent(login_page_2.this, sign_up_page.class);
                startActivity(signupIntent);
            }
        });
    }

    private void loginCustomer(String customerEmail, String customerPassword) {
        auth.signInWithEmailAndPassword(customerEmail, customerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(login_page_2.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login_page_2.this,newhomess.class);
                    intent.putExtra("UserID",auth.getCurrentUser().getUid());


                   // CustomerDetails.setCustomerID(auth.getCurrentUser().getUid());

                    CustomerDetails.setCustomerID("C1");
                    startActivity(intent);

                }else{
                    Toast.makeText(login_page_2.this, "Failed try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}