package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId,password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText);
        emailId=findViewById(R.id.editText3);
        btnSignIn=findViewById(R.id.button);
        tvSignUp=findViewById(R.id.textView);
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser !=null){
                    Toast.makeText(LoginActivity.this,"Logowanie powiodło się",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);


                }
                else {
                    Toast.makeText(LoginActivity.this,"Musisz się zalogować by używać aplikacji",Toast.LENGTH_SHORT).show();
                }

            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Wpisz email");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Wpisz hasło");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Wypełnij puste pola",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Błąd logowania",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToHomePage=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intToHomePage);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Błąd",Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToSignUp=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intToSignUp);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
