package org.enovatelab.androidfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText email, password;

    private Button submitReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);

        submitReg = findViewById(R.id.submitRegister);
        submitReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerNewUser();
            }
        });
    }

    private void registerNewUser() {

        String Email = email.getText().toString();
        String Password = password.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this, MainActivity.class));
                            finish();
                        }

                        else {
                            Toast.makeText(Register.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
