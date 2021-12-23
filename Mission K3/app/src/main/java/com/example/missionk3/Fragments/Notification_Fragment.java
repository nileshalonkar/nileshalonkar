package com.example.missionk3.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.NotificationAdapter;
import com.example.missionk3.R;

public class Notification_Fragment extends Fragment {
    RecyclerView notification_recycler;
    NotificationAdapter notificationAdapter;

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_notification, container, false);
        notification_recycler=view.findViewById(R.id.notification_recycler);


        notificationAdapter = new NotificationAdapter(getActivity());
        notification_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        notification_recycler.setAdapter(notificationAdapter);


        return view;
    }
}