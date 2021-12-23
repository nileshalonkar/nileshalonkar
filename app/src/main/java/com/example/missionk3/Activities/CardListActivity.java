package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.missionk3.Adapters.CardListAdapter;
import com.example.missionk3.Adapters.NotificationAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.R;

public class CardListActivity extends AppCompatActivity {

    RecyclerView cardlist_recycler;
    CardListAdapter cardListAdapter;
    ImageButton add_card,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        cardlist_recycler=findViewById(R.id.cardlist_recycler);
        add_card=findViewById(R.id.add_card);
        btn_back=findViewById(R.id.btn_back);

        cardListAdapter = new CardListAdapter(this);
        cardlist_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(this));
        cardlist_recycler.setAdapter(cardListAdapter);


        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CardListActivity.this, PaymentActivity.class);
               startActivity(intent);
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
        Intent intent=new Intent(CardListActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}