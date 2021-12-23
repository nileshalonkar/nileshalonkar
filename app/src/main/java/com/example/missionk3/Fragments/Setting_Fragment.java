package com.example.missionk3.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.missionk3.Activities.ChangePasswordActivity;
import com.example.missionk3.Activities.ContactUsActivity;
import com.example.missionk3.Activities.PrivacyPolicyActivity;
import com.example.missionk3.Activities.TermsAndConditionActivity;
import com.example.missionk3.R;

public class Setting_Fragment extends Fragment {

    LinearLayout llPrivacyPolicy,llTermsConditions,llContactUs,llChangePassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        llPrivacyPolicy = view.findViewById(R.id.llPrivacyPolicy);
        llTermsConditions = view.findViewById(R.id.llTermsConditions);
        llContactUs = view.findViewById(R.id.llContactUs);
        llChangePassword = view.findViewById(R.id.llChangePassword);


        llPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PrivacyPolicyActivity.class));
            }
        });


        llTermsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TermsAndConditionActivity.class));
            }
        });

        llContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ContactUsActivity.class));
            }
        });

        llChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });

        return view;
    }
}