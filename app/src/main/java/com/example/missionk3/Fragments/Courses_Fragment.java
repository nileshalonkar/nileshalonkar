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
import com.example.missionk3.Adapters.HomeAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.CoursesAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.CoursesDataModel;
import com.example.missionk3.model.HomeDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Courses_Fragment extends Fragment {
    RecyclerView courses_recycler;
    CoursesAdapter coursesAdapter;
    ArrayList<CoursesDataModel> coursesDataModelArrayList;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_courses, container, false);
        courses_recycler=view.findViewById(R.id.courses_recycler);

     /*   coursesAdapter = new CoursesAdapter(getActivity());
        courses_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        courses_recycler.setAdapter(coursesAdapter);
*/

        ShowAllCourses();

        return view;
    }

    public void ShowAllCourses() {
        AndroidNetworking.post(Api.show_courses_cat)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ghjfgkjgfdg", response.toString());
                        coursesDataModelArrayList = new ArrayList<>();
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                CoursesDataModel coursesDataModel = new CoursesDataModel();
                                coursesDataModel.setId(jsonObject.getString("id"));
                                coursesDataModel.setName(jsonObject.getString("name"));
                                coursesDataModel.setImage(jsonObject.getString("image"));
                                coursesDataModel.setType(jsonObject.getString("type"));
                                coursesDataModel.setText(jsonObject.getString("text"));
                                coursesDataModel.setFile_type_status(jsonObject.getString("file_type_status"));
                                coursesDataModel.setPath(jsonObject.getString("path"));
                                coursesDataModelArrayList.add(coursesDataModel);
                            }

                            courses_recycler.setHasFixedSize(true);
                            courses_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            courses_recycler.setAdapter(new CoursesAdapter(getActivity(),coursesDataModelArrayList));

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