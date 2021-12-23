package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.Adapters.HomeAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.OurContributionAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OurContribution_Fragment extends Fragment {
    RecyclerView our_contribution_recycler;
    OurContributionAdapter ourContributionAdapter;

    ArrayList<HomeDataModel> homeDataModelArrayList;
    String User_Id="",Status="";

 /*   private SliderLayout sliderLayout;
    private HashMap<String, Integer> HashMapForLocalRes;*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_our__contribution, container, false);
        our_contribution_recycler = view.findViewById(R.id.our_contribution_recycler);
     //   sliderLayout = view.findViewById(R.id.slider);


      /*  ourContributionAdapter = new OurContributionAdapter(getActivity());
        our_contribution_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        our_contribution_recycler.setAdapter(ourContributionAdapter);*/



        User_Id = SharedHelper.getKey(getActivity(), AppConstant.USERID);
        Status = SharedHelper.getKey(getActivity(), AppConstant.STATUS);
        Log.e("sdfsdfsdf", User_Id);
        Log.e("sdfsdfsdf", Status);

        ShowAllPost();

    //    setSliderLayout();

        return view;
    }


    public void ShowAllPost() {
        AndroidNetworking.post(Api.show_post)
                .addBodyParameter("user_id",User_Id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ghjfgkjgfdg", response.toString());
                        homeDataModelArrayList = new ArrayList<>();
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HomeDataModel homeDataModel = new HomeDataModel();
                                homeDataModel.setId(jsonObject.getString("id"));
                                homeDataModel.setCategory_id(jsonObject.getString("category_id"));
                                homeDataModel.setTitle(jsonObject.getString("title"));
                                homeDataModel.setDescription(jsonObject.getString("description"));
                                homeDataModel.setImage(jsonObject.getString("image"));
                                homeDataModel.setPath(jsonObject.getString("path"));
                                homeDataModel.setDownload(jsonObject.getString("download"));
                                homeDataModel.setLike_status(jsonObject.getString("like_status"));
                                homeDataModelArrayList.add(homeDataModel);
                            }

                            our_contribution_recycler.setHasFixedSize(true);
                            our_contribution_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            our_contribution_recycler.setAdapter(new OurContributionAdapter(getActivity(), homeDataModelArrayList));

                        } catch (Exception exception) {
                            Log.e("abcdhd", exception.getMessage());



                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("abcdhd", "onError: " + anError);

                    }
                });

    }



  /*  private void setSliderLayout() {
        AddImageUrlFormLocalRes();
        for (String name : HashMapForLocalRes.keySet()) {

            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            defaultSliderView
                    .image(HashMapForLocalRes.get(name));

            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(defaultSliderView);
        }
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(5000);
    }

    public void AddImageUrlFormLocalRes() {
        HashMapForLocalRes = new HashMap<String, Integer>();
        HashMapForLocalRes.put("1", R.drawable.contribution_one);
        HashMapForLocalRes.put("2", R.drawable.contribution_two);
        HashMapForLocalRes.put("3", R.drawable.contribution_three);
        HashMapForLocalRes.put("4", R.drawable.contribution_four);
    }*/
}