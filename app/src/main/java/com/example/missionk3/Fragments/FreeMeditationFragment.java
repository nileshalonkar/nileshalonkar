package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Adapters.MeditationAdapter;
import com.example.missionk3.Adapters.VideoAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.example.missionk3.model.VideoDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import hb.xvideoplayer.MxVideoPlayer;

public class FreeMeditationFragment extends Fragment {

    RecyclerView meditation_recycler;
    ArrayList<VideoDataModel> videoDataModelArrayList;
    MeditationAdapter meditationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free_meditation, container, false);
        meditation_recycler=view.findViewById(R.id.meditation_recycler);

/*
        meditationAdapter = new MeditationAdapter(getActivity());
        meditation_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        meditation_recycler.setAdapter(meditationAdapter);*/

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("keyCode" , keyCode+"");
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    MxVideoPlayer.releaseAllVideos();
                    // getActivity().onBackPressed();


                }
                return false;
            }
        });


        ShowVideo();


        return view;
    }

    public void ShowVideo() {
        AndroidNetworking.post(Api.show_post_video)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("dgdfgfggghg", response.toString());
                        videoDataModelArrayList = new ArrayList<>();
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                VideoDataModel videoDataModel = new VideoDataModel();
                                videoDataModel.setId(jsonObject.getString("id"));
                                videoDataModel.setCategory_id(jsonObject.getString("category_id"));
                                videoDataModel.setTitle(jsonObject.getString("title"));
                                videoDataModel.setDescription(jsonObject.getString("description"));
                                videoDataModel.setImage(jsonObject.getString("image"));
                                //   homeDataModel.setPath(jsonObject.getString("path"));
                                videoDataModel.setDownload(jsonObject.getString("download"));
                                videoDataModel.setLike_status(jsonObject.getString("like_status"));

                                JSONArray jsonArray1 = jsonObject.getJSONArray("show_file");
                                for (int j = 0; j <jsonArray1.length() ; j++) {
                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                    videoDataModel.setPath(jsonObject1.getString("path"));
                                    videoDataModel.setFile(jsonObject1.getString("file"));

                                }
                                videoDataModelArrayList.add(videoDataModel);
                            }

                            meditation_recycler.setHasFixedSize(true);
                            meditation_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            meditation_recycler.setAdapter(new VideoAdapter(getActivity(), videoDataModelArrayList));

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