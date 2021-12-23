package com.example.missionk3.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Activities.EditProfileActivity;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile_Fragment extends Fragment {
    MaterialButton btn_edit_profile;
    CircleImageView ProfileCircle;
    TextView txtName,txtEmail,txtMobile,txtPassword;
    String User_Id="";
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        btn_edit_profile=view.findViewById(R.id.btn_edit_profile);
        ProfileCircle=view.findViewById(R.id.ProfileCircle);
        txtName=view.findViewById(R.id.txtName);
        txtEmail=view.findViewById(R.id.txtEmail);
        txtMobile=view.findViewById(R.id.txtMobile);
        txtPassword=view.findViewById(R.id.txtPassword);

        User_Id = SharedHelper.getKey(getContext(), AppConstant.USERID);
        Log.e("dfsdds", User_Id );

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

    //    Show_My_Profile();
        return view;
    }

    private void Show_My_Profile() {
        //    progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(Api.show_profile)
                .addBodyParameter("id", User_Id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("eferwfr", "" + response.toString());

                        try {
                            if (response.getString("result").equals("show profile successfully")) {
                                Toast.makeText(getActivity(), "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                Glide.with(getContext()).load(response.getString("path") + response.getString("image"))
                                        //  .placeholder(R.drawable.shop_img).override(250, 250)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(ProfileCircle);
                                txtName.setText(response.getString("name"));
                                txtEmail.setText(response.getString("email"));
                                txtMobile.setText(response.getString("phone"));
                                txtPassword.setText(response.getString("password"));
                                // startActivity(new Intent(getActivity(), HomeActivity.class));

                            } else {
                                Toast.makeText(getActivity(), "" + response.getString("result"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dgfffdf", e.getMessage());
                            //     progressBar.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("sdafetrr", "onError: " + anError);
                        //   progressBar.setVisibility(View.GONE);
                    }
                });
    }

}
