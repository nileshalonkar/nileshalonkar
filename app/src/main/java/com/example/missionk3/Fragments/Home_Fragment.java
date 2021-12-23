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
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.HomeAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class Home_Fragment extends Fragment {
    RecyclerView rvHomePosts;
    ArrayList<HomeDataModel> homeDataModelArrayList;
    String User_Id = "", Status = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_, container, false);
        rvHomePosts = view.findViewById(R.id.rvHomePosts);

      /*  homeAdapter = new HomeAdapter(getActivity());
        home_frag_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        home_frag_recycler.setAdapter(homeAdapter);*/


        User_Id = SharedHelper.getKey(getActivity(), AppConstant.USERID);
        Status = SharedHelper.getKey(getActivity(), AppConstant.STATUS);
        Log.e("sdfsdfsdf", User_Id);
        Log.e("sdfsdfsdf", Status);

        ShowAllPost();

        return view;

    }

    public void ShowAllPost() {
        AndroidNetworking.post(Api.show_post)
                // .addBodyParameter("user_id",User_Id)
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
                                // homeDataModel.setPath(jsonObject.getString("path"));
                                homeDataModel.setDownload(jsonObject.getString("download"));
                                homeDataModel.setLike_status(jsonObject.getString("like_status"));

                                JSONArray jsonArray1 = jsonObject.getJSONArray("show_file");
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                    homeDataModel.setFile(jsonObject1.getString("file"));
                                    homeDataModel.setPath(jsonObject.getString("path"));
                                  //  homeDataModel.setVideo_path(jsonObject.getString("video_path"));
                                    homeDataModel.setFile_type_status(jsonObject1.getString("file_type_status"));
                                    homeDataModel.setThumbnail_image(jsonObject1.getString("thumbnail_image"));

                                }

                                homeDataModelArrayList.add(homeDataModel);
                            }

                            rvHomePosts.setHasFixedSize(true);
                            rvHomePosts.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            rvHomePosts.setAdapter(new HomeAdapter(getActivity(), homeDataModelArrayList));

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
}