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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);

        CustomerEmail = findViewById(R.id.CustomerEmail);
        CustomerPassword = findViewById(R.id.CustomerPassword);
        SignUp = findViewById(R.id.SignUp);
        SignIn = findViewById(R.id.SignIn);
        auth = FirebaseAuth.getInstance();  // very important

    }

    @Override
    protected void onResume() {
        super.onResume();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String customerEmail = CustomerEmail.getText().toString().trim();
                String customerPassword = CustomerPassword.getText().toString().trim();
//                Log.i("check", customerEmail + customerPassword);
                loginCustomer(customerEmail, customerPassword);

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
                    Log.i("UserID",auth.getCurrentUser().getUid());
                    Toast.makeText(login_page_2.this, "Login Success!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(login_page_2.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}