package com.example.admin.stalyzer.LoginRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.stalyzer.Internet;
import com.example.admin.stalyzer.MainActivity;
import com.example.admin.stalyzer.R;
import com.example.admin.stalyzer.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity
{
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    Button loginButton,signUpButton;
    EditText email,password;

    Session session;
    Internet internet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        session = new Session(this);
        internet = new Internet(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(internet.isNetworkAvailable())
                {
                    if(Email.length()==0 || Password.length()==0)
                    {
                        Toast.makeText(Login.this, "Fill in your details", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        checkLogin(Email,Password);
                    }
                }
                else
                    showToast("Network Unavailable");
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    public void checkLogin(final String Email, final String Password)
    {
        valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                int count=0;
                String name = null;
                try
                {
                    for (DataSnapshot ds : dataSnapshot.child("users").getChildren())
                    {
                        String temp_username = ds.child("profile").child("email").getValue().toString();
                        String temp_password = ds.child("profile").child("password").getValue().toString();
                        if (temp_username.equalsIgnoreCase(Email.toLowerCase()) && temp_password.equals(Password.toLowerCase()))
                        {
                            name = ds.child("profile").child("name").getValue().toString();
                            count = 1;
                        }
                    }
                }
                catch (Exception e)
                {
//                    showToast(e.toString());
                }

                if(count==1)
                {
//                    showToast("Login Successfull");
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.putExtra("NAME",name);
                    intent.putExtra("EMAIL",Email);
                    intent.putExtra("ACTIVITY_NAME","Login");
                    session.createLoginSession(Email,name);
                    startActivity(intent);
                }
                else
                {
//                    showToast("Login Failed");
                }
//                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
//                showToast(databaseError.getMessage().toString());
//                showToast("FAILED");
            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    void showToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}