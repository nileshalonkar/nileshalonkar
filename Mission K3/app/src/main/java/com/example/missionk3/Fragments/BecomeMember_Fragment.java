package com.example.missionk3.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.example.missionk3.Activities.CardListActivity;
import com.example.missionk3.Activities.MainActivity;
import com.example.missionk3.R;

public class BecomeMember_Fragment extends Fragment {
    Button  btn_buy;
    TextView txt_description,txt_price;
    static Dialog dialog;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_become_a_member_, container, false);
        txt_description=view.findViewById(R.id.txt_description);
        txt_price=view.findViewById(R.id.txt_price);

        btn_buy=view.findViewById(R.id.btn_buy);
        dialog = new Dialog(getContext());
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuggestFeatureAlertDialog();
            }
        });
        return view;
    }


    public static void showSuggestFeatureAlertDialog() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dailog_view);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        Button btnDialogYes=dialog.findViewById(R.id.btnDialogYes);
        Button  btnDialogNo=dialog.findViewById(R.id.btnDialogNo);

        btnDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dialog.getContext(), CardListActivity.class);
                dialog.getContext().startActivity(intent);
                
            }
        });

        btnDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dialog.getContext(),MainActivity.class);
                dialog.getContext().startActivity(intent);

            }
        });
    }

}