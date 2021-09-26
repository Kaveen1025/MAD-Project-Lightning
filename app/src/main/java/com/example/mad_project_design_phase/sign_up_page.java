package com.example.mad_project_design_phase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;


public class sign_up_page extends AppCompatActivity {

    EditText firstname, lastname, input_email, postal_address, phone_number,password, et_confirm_password;
    Button button_signup;
    Customer CusObj;
    DatabaseReference dbRef;
//    FirebaseDatabase database;
    FirebaseAuth auth;
    CheckBox terms;
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
        terms = findViewById(R.id.terms);
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

                if(terms.isChecked()){

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


                }else{

                    Toast.makeText(sign_up_page.this, "You should agree with our terms and conditions", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }


}