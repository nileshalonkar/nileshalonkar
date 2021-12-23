package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.CoursesAdapter;
import com.example.missionk3.R;

public class Courses_Fragment extends Fragment {
    RecyclerView courses_recycler;
    CoursesAdapter coursesAdapter;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_courses, container, false);
        courses_recycler=view.findViewById(R.id.courses_recycler);


        coursesAdapter = new CoursesAdapter(getActivity());
        courses_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        courses_recycler.setAdapter(coursesAdapter);


        return view;
    }
}