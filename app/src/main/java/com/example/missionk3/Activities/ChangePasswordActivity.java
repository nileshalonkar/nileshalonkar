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

public class ChangePasswordActivity extends AppCompatActivity {
    ImageView btn_back;
    EditText etOldPassword,etNewPassword,etConfirmPassword;
    MaterialButton btn_Submit;
    ProgressBar progressBar;
    String User_Id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btn_back = findViewById(R.id.btn_back);
        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btn_Submit = findViewById(R.id.btn_Submit);
        progressBar = findViewById(R.id.progressBar);

        User_Id = SharedHelper.getKey(ChangePasswordActivity.this, AppConstant.USERID);
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
                validateDetails();
            }
        });

    }

    private void validateDetails() {
        if (etOldPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter old password", Toast.LENGTH_SHORT).show();
        } else if (etNewPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter new password", Toast.LENGTH_SHORT).show();
        } else if(etConfirmPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
        } else {
            ChangePassword();
        }
    }

    private void ChangePassword() {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(Api.change_password)
                .addBodyParameter("id",User_Id)
                .addBodyParameter("old_password",etOldPassword.getText().toString())
                .addBodyParameter("new_passwrod",etNewPassword.getText().toString())
                .addBodyParameter("confirm_passwrod",etConfirmPassword.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("frrrte", "onResponse: "+response);
                        try {
                            if (response.getString("result").equals("update successfully")){
                                Toast.makeText(ChangePasswordActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));

                            }else {
                                Toast.makeText(ChangePasswordActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
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