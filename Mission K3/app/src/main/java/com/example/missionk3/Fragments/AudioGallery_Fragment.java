package com.example.missionk3.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Adapters.AudioGalleryAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AudioGallery_Fragment extends Fragment {
    AudioGalleryAdapter audioGalleryAdapter;
    RecyclerView recycler_audio;
    ArrayList<HomeDataModel> homeDataModelArrayList;


    View view;
    String User_Id = "", Status = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_audio__gallery, container, false);
        recycler_audio = view.findViewById(R.id.recycler_audio);
       /* audioGalleryAdapter = new AudioGalleryAdapter(getActivity());
        recycler_audio.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        recycler_audio.setAdapter(audioGalleryAdapter);*/

      /*  User_Id = SharedHelper.getKey(getActivity(), AppConstant.USERID);
        Status = SharedHelper.getKey(getActivity(), AppConstant.STATUS);
        Log.e("sdfsdfsdf", User_Id);
        Log.e("sdfsdfsdf", Status);*/
        ShowAudio();

        return view;

    }


    public void ShowAudio() {
        AndroidNetworking.post(Api.show_post_audio)
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

                            recycler_audio.setHasFixedSize(true);
                            recycler_audio.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            recycler_audio.setAdapter(new AudioGalleryAdapter(getActivity(), homeDataModelArrayList));

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