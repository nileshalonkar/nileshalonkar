package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.MissionK3FamilyAdapter;
import com.example.missionk3.R;

public class MissionK3Family_Fragment extends Fragment {

    RecyclerView mssionk3family_recycler;
    MissionK3FamilyAdapter missionK3FamilyAdapter;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_mission__k3__family, container, false);
        mssionk3family_recycler=view.findViewById(R.id.mssionk3family_recycler);



        missionK3FamilyAdapter = new MissionK3FamilyAdapter(getActivity());
        mssionk3family_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        mssionk3family_recycler.setAdapter(missionK3FamilyAdapter);





        return view;
    }
}