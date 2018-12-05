package org.enovatelab.androidfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser current_user;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        current_user = firebaseAuth.getCurrentUser();

        if (current_user == null) {

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}
