package com.example.missionk3.Fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.Adapters.AudioGalleryAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.AudioListDataModel;
import com.example.missionk3.model.HomeDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AudioListFragment extends Fragment {
    RecyclerView rv_audio_list;
    ArrayList<AudioListDataModel> audioListDataModels;
    ImageView btn_back;
    String AudioCatID="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_audio_list, container, false);

        rv_audio_list = view.findViewById(R.id.rv_audio_list);
        btn_back = view.findViewById(R.id.btn_back);

        AudioCatID = SharedHelper.getKey(getContext(), AppConstant.AUDIO_CAT_ID);
        Log.e("fdsfdsfd", AudioCatID );

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ShowAudioList();

        return view;
    }

    public void ShowAudioList() {
        AndroidNetworking.post(Api.show_post_audio)
                .addBodyParameter("category_id",AudioCatID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ghjfgkjgfdg", response.toString());
                        audioListDataModels = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                AudioListDataModel audioListDataModel = new AudioListDataModel();
                                audioListDataModel.setId(jsonObject.getString("id"));
                                audioListDataModel.setCategory_id(jsonObject.getString("category_id"));
                                audioListDataModel.setTitle(jsonObject.getString("title"));
                                audioListDataModel.setDescription(jsonObject.getString("description"));
                                audioListDataModel.setImage(jsonObject.getString("image"));
                                //   homeDataModel.setPath(jsonObject.getString("path"));
                                audioListDataModel.setDownload(jsonObject.getString("download"));
                                audioListDataModel.setLike_status(jsonObject.getString("like_status"));

                                JSONArray jsonArray1 = jsonObject.getJSONArray("show_file");
                                for (int j = 0; j <jsonArray1.length() ; j++) {
                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                    audioListDataModel.setPath(jsonObject1.getString("path"));

                                }

                                audioListDataModels.add(audioListDataModel);
                            }

                            rv_audio_list.setHasFixedSize(true);
                            rv_audio_list.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            rv_audio_list.setAdapter(new AudioGalleryAdapter(getActivity(), audioListDataModels));

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