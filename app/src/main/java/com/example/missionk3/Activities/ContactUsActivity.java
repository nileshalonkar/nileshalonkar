package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class ContactUsActivity extends AppCompatActivity {
    ImageView btn_back;
    EditText etUserName,etEmail,etMobileNo,etComment;
    MaterialButton btn_Submit;
    ProgressBar progressBar;
    String User_Id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        btn_back = findViewById(R.id.btn_back);
        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etMobileNo = findViewById(R.id.etMobileNo);
        etComment = findViewById(R.id.etComment);
        btn_Submit = findViewById(R.id.btn_Submit);
        progressBar = findViewById(R.id.progressBar);

        User_Id = SharedHelper.getKey(ContactUsActivity.this, AppConstant.USERID);
        Log.e("rrfdgfdgfdg", User_Id);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserName.getText().toString().trim().equals("")) {
                    Toast.makeText(ContactUsActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                } else if (etEmail.getText().toString().trim().equals("")) {
                    Toast.makeText(ContactUsActivity.this, "Enter your email ", Toast.LENGTH_SHORT).show();
                } else if (etMobileNo.getText().toString().trim().equals("")) {
                    Toast.makeText(ContactUsActivity.this, "Enter your mobile number ", Toast.LENGTH_SHORT).show();
                } else if (etComment.getText().toString().trim().equals("")) {
                    Toast.makeText(ContactUsActivity.this, "Enter your comment ", Toast.LENGTH_SHORT).show();
                } else {
                    Contact_Us();
                }
            }
        });
    }

    private void Contact_Us() {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(Api.contact_us)
             //   .addBodyParameter("user_id",User_Id)
                .addBodyParameter("name",etUserName.getText().toString())
                .addBodyParameter("email",etEmail.getText().toString())
                .addBodyParameter("mobile",etMobileNo.getText().toString())
                .addBodyParameter("text",etComment.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("frrrte", "onResponse: "+response);
                        try {
                            if (response.getString("result").equals("message sent successfully")){
                                Toast.makeText(ContactUsActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ContactUsActivity.this, MainActivity.class));

                            }else {
                                Toast.makeText(ContactUsActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            Log.e("frrrte", e.getMessage());
                            progressBar.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("sadDSDSSSD", anError.getMessage());
                        progressBar.setVisibility(View.GONE);

                    }
                });
    }
}