package com.example.greenbotprikolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdit, passwordEdit;
    Button loginBtn;

    ProgressBar progressBar;
    TextView createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdit = findViewById(R.id.email_edit_log_text);
        passwordEdit = findViewById(R.id.password_edit_log_text);
        loginBtn = findViewById(R.id.login_btn1);
        progressBar = findViewById(R.id.progress_log_indicator);
        createAccountBtn = findViewById(R.id.create_account_text_view);

        loginBtn.setOnClickListener((v)-> loginUser());

        createAccountBtn.setOnClickListener(v->{startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            finish();});
    }
    public void loginUser(){
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        boolean isValidated = validateData(email, password);
        if (!isValidated){
            return;
        }
        loginAccountInFirebase(email, password);

    }

    public void loginAccountInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if (task.isSuccessful()){
                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else {
                        Utility.showToast(LoginActivity.this, "Email not verified...");
                    }
                } else {
                    Utility.showToast(LoginActivity.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }

    public void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    public boolean validateData(String email, String password) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdit.setError("Email is invalid");
            return false;
        }
        if (password.length()<6){
            passwordEdit.setError("Password length is invalid");
            return false;
        }
        return true;
    }
}