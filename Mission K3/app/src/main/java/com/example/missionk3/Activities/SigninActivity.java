package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity {

    TextView txt_signup, txt_email,forgot_password_signin;
    RelativeLayout signin_relative;
    EditText enter_email, enter_password_signin;
    MaterialButton btn_submit_signin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        signin_relative = findViewById(R.id.signin_relative);
        txt_signup = findViewById(R.id.txt_signin);
        txt_email = findViewById(R.id.txt_email);
        btn_submit_signin = findViewById(R.id.btn_submit_signin);
        enter_password_signin = findViewById(R.id.enter_password_signin);
        enter_email = findViewById(R.id.enter_email);
        forgot_password_signin = findViewById(R.id.forgot_password_signin);
        progressBar = findViewById(R.id.progressBar);


        btn_submit_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = enter_email.getText().toString().trim();
                String password = enter_password_signin.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(enter_email.getText().toString().isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();

                }else if(!email.matches(emailPattern)) {
                    Toast.makeText(SigninActivity.this, "Please enter valid email id.", Toast.LENGTH_SHORT).show();

                }else if(enter_password_signin.getText().toString().equals("")) {
                    Toast.makeText(SigninActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();

                }else {

                    Loggedin(enter_email.getText().toString().trim(),enter_password_signin.getText().toString().trim());

                }
            }
        });

        signin_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        forgot_password_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this,ForgotPasswordActivity.class));
            }
        });

    }

    public void Loggedin(String trim, String s) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(Api.login)
                .addBodyParameter("email", trim)
                .addBodyParameter("password", s)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("OtpActivity", "onResponse: " + response);
                        try {
                            if (response.getString("result").equals("otp sent successfully")) {

                                SharedHelper.putKey(SigninActivity.this, AppConstant.USERID,response.getString("id"));
                                Toast.makeText(SigninActivity.this,response.getString("result"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SigninActivity.this, OTPActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SigninActivity.this,response.getString("result"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception exception) {
                            Log.e("dgvdfsgdf", exception.getMessage());
                            progressBar.setVisibility(View.GONE);

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("OTPActivity", "onError: " + anError);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }


}

//        btn_submit_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = enter_email_signin.getText().toString().trim();
//
//                String password = enter_password_signin.getText().toString().trim();
//
//                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//                if (email!=null && email.equals("")) {
//                    Toast.makeText(SigninActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
//
//                } else if (!email.matches(emailPattern)) {
//                    Toast.makeText(SigninActivity.this, "Please enter valid email address.", Toast.LENGTH_SHORT).show();
//
//                } else if (enter_password_signin.getText().toString().equals("")) {
//                    Toast.makeText(SigninActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
//
//
//                }else {
//                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
