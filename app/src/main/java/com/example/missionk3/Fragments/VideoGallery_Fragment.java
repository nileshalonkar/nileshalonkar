package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.missionk3.Adapters.AudioGalleryAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.VideoAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import hb.xvideoplayer.MxVideoPlayer;

public class VideoGallery_Fragment extends Fragment {
    RecyclerView video_gallery_recycler;
    VideoAdapter videoAdapter;
    ArrayList<HomeDataModel> homeDataModelArrayList;




    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_video__gallery, container, false);
        video_gallery_recycler=view.findViewById(R.id.video_gallery_recycler);

        /*videoAdapter = new VideoAdapter(getActivity());
        video_gallery_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        video_gallery_recycler.setAdapter(videoAdapter);
*/
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


     //   ShowVideo();

        return view;
    }


  /*  public void ShowVideo() {
        AndroidNetworking.post(Api.show_post_video)
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
                                homeDataModel.setLike_status(jsonObject.getString("like_status"));
                                homeDataModel.setVideo(jsonObject.getString("video"));
                                homeDataModelArrayList.add(homeDataModel);
                            }

                            video_gallery_recycler.setHasFixedSize(true);
                            video_gallery_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            video_gallery_recycler.setAdapter(new VideoAdapter(getActivity(), homeDataModelArrayList));

                        } catch (Exception exception) {
                            Log.e("abcdhd", exception.getMessage());


                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("abcdhd", "onError: " + anError);

                    }
                });

    }*/



}