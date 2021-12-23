



package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;

import org.json.JSONObject;

public class OTPActivity extends AppCompatActivity {

    Button btn_submit_otp;
    ImageButton btn_back;
    EditText edit_txt_enter_otp;
    String USERID="";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        USERID= SharedHelper.getKey(OTPActivity.this, AppConstant.USERID);

        btn_submit_otp=findViewById(R.id.btn_submit_otp);
        progressBar=findViewById(R.id.progressBar);
        edit_txt_enter_otp=findViewById(R.id.edit_txt_enter_otp);
        btn_back=findViewById(R.id.btn_back);

        btn_submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_txt_enter_otp.getText().toString().trim().equals("")){
                    Toast.makeText(OTPActivity.this, "please enter otp", Toast.LENGTH_SHORT).show();
                }else{
                    otpSend(edit_txt_enter_otp.getText().toString().trim());
                }
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void otpSend(String trim) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(Api.check_otp)
                .addBodyParameter("id", USERID)
                .addBodyParameter("otp", trim)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("OtpActivity", "onResponse: " + response);
                        try {
                            if (response.getString("result").equals("otp match successfully")) {

                                Toast.makeText(OTPActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(OTPActivity.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(OTPActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
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