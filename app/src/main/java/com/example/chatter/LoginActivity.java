package com.example.chatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    //intended for login page
public class LoginActivity extends AppCompatActivity {

    EditText emailBox, passwordBox;
    Button loginBtn, signupBtn;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        auth = FirebaseAuth.getInstance();        //returns instance corresponding to firebaseapp instance

        //identifies by attribute processed on OnCreate
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);

        loginBtn = findViewById(R.id.loginbtn);
         signupBtn = findViewById(R.id.createBtn);

         //function call to login button
         loginBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.show();
                 String email, password;
                 email = emailBox.getText().toString();
                 password = passwordBox.getText().toString();

                 //uses the instance to sign in a user with provided credentials
                 auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         dialog.dismiss();
                         if(task.isSuccessful()){
                             //proceed to dashboard page
                             startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                         } else {
                             //provides a localised message to user
                             Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
             }
         });

         //function call to sign up button
         signupBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(LoginActivity.this, SignupActivity.class));
             }
         });
    }
}