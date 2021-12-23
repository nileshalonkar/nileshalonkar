package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.missionk3.R;

public class PaymentActivity extends AppCompatActivity {
    Button btn_proceed_payment;

    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btn_proceed_payment=findViewById(R.id.btn_proceed_payment);
        btn_back=findViewById(R.id.btn_back);

        btn_proceed_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentActivity.this,CardListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PaymentActivity.this,CardListActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}