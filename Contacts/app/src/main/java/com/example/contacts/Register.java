package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.io.DataOutputStream;

public class Register extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    EditText et_Name,et_Mail,et_Password,et_Retype;
    Button btn_Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        et_Name=findViewById(R.id.et_Name);
        et_Mail=findViewById(R.id.et_Mail);
        et_Password=findViewById(R.id.et_Password);
        et_Retype=findViewById(R.id.et_Retype);
        btn_Register=findViewById(R.id.btn_register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_Name.getText().toString().isEmpty()||et_Mail.getText().toString().isEmpty()||et_Password.getText().toString().isEmpty()||et_Retype.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this,"Please enter all fields!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(et_Password.getText().toString().trim().equals(et_Retype.getText().toString().trim()))
                    {
                        BackendlessUser user=new BackendlessUser();
                        user.setEmail(et_Mail.getText().toString().trim());
                        user.setPassword(et_Password.getText().toString().trim());
                        user.setProperty("name",et_Name.getText().toString().trim());
                        tvLoad.setText("Registering new user....Please wait..");
                        showProgress(true);
                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Toast.makeText(Register.this,"User successfully registered..",Toast.LENGTH_SHORT).show();
                                Register.this.finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(Register.this,"Error occured.."+fault.getMessage(),Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Please make sure password match!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}