package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.missionk3.Adapters.MeditationAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.R;

public class FreeMeditationFragment extends Fragment {

    RecyclerView meditation_recycler;
    MeditationAdapter meditationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free_meditation, container, false);


        meditation_recycler=view.findViewById(R.id.meditation_recycler);


        meditationAdapter = new MeditationAdapter(getActivity());
        meditation_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        meditation_recycler.setAdapter(meditationAdapter);


        return view;
    }
}