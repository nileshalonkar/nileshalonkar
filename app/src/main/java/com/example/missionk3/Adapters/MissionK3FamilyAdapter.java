package com.example.missionk3.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.example.missionk3.model.OurFamilyDataModel;

import java.util.List;

public class MissionK3FamilyAdapter extends RecyclerView.Adapter<MissionK3FamilyAdapter.MyViewholder> {

    private Context mContext;
    private List<OurFamilyDataModel> ourFamilyDataModelList;
    public MissionK3FamilyAdapter(Context mContext, List<OurFamilyDataModel> ourFamilyDataModelList) {
        this.mContext = mContext;
        this.ourFamilyDataModelList = ourFamilyDataModelList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.missionk3_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        OurFamilyDataModel ourFamilyDataModel = ourFamilyDataModelList.get(position);

        if (!ourFamilyDataModel.equals("")) {
            Glide.with(mContext).load(ourFamilyDataModel.getPath())
                    .placeholder(R.drawable.env_three).override(410, 230)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.visiting_card);
        }
    }

    @Override
    public int getItemCount() {
        return ourFamilyDataModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        ImageView visiting_card;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            visiting_card = itemView.findViewById(R.id.visiting_card);
        }
    }
}
