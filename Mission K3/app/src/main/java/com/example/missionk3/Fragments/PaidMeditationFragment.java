package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.missionk3.R;

public class PaidMeditationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view  = inflater.inflate(R.layout.fragment_paid_meditation, container, false);

       return view;
    }
}