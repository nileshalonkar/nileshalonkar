package com.example.missionk3.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Adapters.HomeAdapter;
import com.example.missionk3.CommonUtils.CommonUtils;
import com.example.missionk3.Adapters.MissionK3FamilyAdapter;
import com.example.missionk3.R;
import com.example.missionk3.model.CategoryModel;
import com.example.missionk3.model.DistrictModel;
import com.example.missionk3.model.HomeDataModel;
import com.example.missionk3.model.OurFamilyDataModel;
import com.example.missionk3.model.StateModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MissionK3Family_Fragment extends Fragment {

    RecyclerView mssionk3family_recycler;
    MissionK3FamilyAdapter missionK3FamilyAdapter;
    ArrayList<OurFamilyDataModel> ourFamilyDataModelArrayList;
    Spinner SpinnerState,SpinnerDistrict,SpinnerCategory;

    ArrayList<StateModel> StateModelArrayList;
    ArrayList<String> Arr_StateID;
    ArrayList<String> Arr_stateName;

    ArrayList<DistrictModel> districtModelArrayList;
    ArrayList<String> Arr_districtID = new ArrayList<>();
    ArrayList<String> Arr_districtName = new ArrayList<>();

    ArrayList<CategoryModel> categoryModelArrayList;
    ArrayList<String> Arr_categoryID = new ArrayList<>();
    ArrayList<String> Arr_categoryName = new ArrayList<>();

    String stateID="",districtID="",categoryID="";

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_mission__k3__family, container, false);
        mssionk3family_recycler=view.findViewById(R.id.mssionk3family_recycler);
        SpinnerState=view.findViewById(R.id.SpinnerState);
        SpinnerDistrict=view.findViewById(R.id.SpinnerDistrict);
        SpinnerCategory=view.findViewById(R.id.SpinnerCategory);


        StateModelArrayList= new ArrayList<>();
        Arr_StateID = new ArrayList<>();
        Arr_stateName = new ArrayList<>();
        Arr_StateID.add("0");
        Arr_stateName.add("Select State");

        districtModelArrayList= new ArrayList<>();
        Arr_districtID = new ArrayList<>();
        Arr_districtName = new ArrayList<>();
        Arr_districtID.add("0");
        Arr_districtName.add("Select District");

        categoryModelArrayList= new ArrayList<>();
        Arr_categoryID = new ArrayList<>();
        Arr_categoryName = new ArrayList<>();
        Arr_categoryID.add("0");
        Arr_categoryName.add("Select Category");


        ArrayAdapter state = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, Arr_stateName);
        state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerState.setAdapter(state);

        ArrayAdapter district = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, Arr_districtName);
        district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDistrict.setAdapter(district);

        ArrayAdapter category = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, Arr_categoryName);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCategory.setAdapter(category);

     /*   missionK3FamilyAdapter = new MissionK3FamilyAdapter(getActivity());
        mssionk3family_recycler.setLayoutManager(CommonUtils.verticalRecycleHandle(getActivity()));
        mssionk3family_recycler.setAdapter(missionK3FamilyAdapter);
*/
        state();
        ShowMissionK3Family();

        return view;
    }

    public void state() {
        AndroidNetworking.post(Api.state)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("tgdtfgdffff", response.toString());
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Arr_StateID.add(jsonObject.getString("id"));
                                Arr_stateName.add(jsonObject.getString("name"));

                            }

                            SpinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String stateId = Arr_StateID.get(position);
                                    ((TextView) SpinnerState.getChildAt(0)).setTextColor(Color.WHITE);
                                    ((TextView) SpinnerState.getChildAt(0)).setTextSize(15);

                                    if (stateId.equals("0")){


                                    }else {

                                        stateID=stateId;
                                    }

                                   district();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("kjfcxgh", e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("fbdlgs",anError.getMessage());

                    }
                });
    }

    public void district() {
        AndroidNetworking.post(Api.district)
                .addBodyParameter("state_id",stateID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("tgdtfgdffff", response.toString());
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Arr_districtID.add(jsonObject.getString("id"));
                                Arr_districtName.add(jsonObject.getString("name"));

                            }

                            SpinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String districtId = Arr_districtID.get(position);
                                    ((TextView) SpinnerDistrict.getChildAt(0)).setTextColor(Color.WHITE);
                                    ((TextView) SpinnerDistrict.getChildAt(0)).setTextSize(15);

                                    if (districtId.equals("0")){


                                    }else {
                                        districtID=districtId;
                                    }
                                    category();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("kjfcxgh", e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("fbdlgs",anError.getMessage());

                    }
                });
    }

    public void category() {
        AndroidNetworking.post(Api.category)
                .addBodyParameter("city_id",districtID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("tgdtfgdffff", response.toString());
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Arr_categoryID.add(jsonObject.getString("id"));
                                Arr_categoryName.add(jsonObject.getString("name"));

                            }

                            SpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String categoryId = Arr_categoryID.get(position);
                                    ((TextView) SpinnerCategory.getChildAt(0)).setTextColor(Color.WHITE);
                                    ((TextView) SpinnerCategory.getChildAt(0)).setTextSize(15);

                                    if (categoryId.equals("0")){


                                    }else {

                                        categoryID=categoryId;
                                    }

                                    ShowMissionK3Family();

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("kjfcxgh", e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("fbdlgs",anError.getMessage());

                    }
                });
    }

    public void ShowMissionK3Family() {
        AndroidNetworking.post(Api.show_mission_K3_family)
                 .addBodyParameter("cat_id",categoryID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ghjfgkjgfdg", response.toString());
                        ourFamilyDataModelArrayList = new ArrayList<>();
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                OurFamilyDataModel ourFamilyDataModel = new OurFamilyDataModel();
                                ourFamilyDataModel.setId(jsonObject.getString("id"));
                                ourFamilyDataModel.setName(jsonObject.getString("name"));
                                ourFamilyDataModel.setType(jsonObject.getString("type"));
                                ourFamilyDataModel.setText(jsonObject.getString("text"));
                                ourFamilyDataModel.setImage(jsonObject.getString("image"));
                                ourFamilyDataModel.setPath(jsonObject.getString("path"));
                                ourFamilyDataModel.setFile_type_status(jsonObject.getString("file_type_status"));
                                ourFamilyDataModelArrayList.add(ourFamilyDataModel);
                            }

                            mssionk3family_recycler.setHasFixedSize(true);
                            mssionk3family_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                            mssionk3family_recycler.setAdapter(new MissionK3FamilyAdapter(getActivity(), ourFamilyDataModelArrayList));

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