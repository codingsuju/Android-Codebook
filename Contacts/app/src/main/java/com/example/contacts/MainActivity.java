package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MainActivity extends AppCompatActivity {

    Button btnLogout,btnCreateContact,btnContactList;
    TextView tvUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout=findViewById(R.id.btnLogout);
        btnCreateContact=findViewById(R.id.btnCreateContact);
        btnContactList=findViewById(R.id.btnContactList);
        tvUserEmail=findViewById(R.id.tvUserEmail);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Logging you out...",Toast.LENGTH_SHORT);
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(MainActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Login.class));
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this,"Error"+fault.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        tvUserEmail.setText("Logged in as "+ContactsApplication.user.getProperty("name")+"("+ContactsApplication.user.getEmail()+")");
        btnContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContactList.class));
            }
        });


        btnCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateContact.class));
            }
        });
    }
}