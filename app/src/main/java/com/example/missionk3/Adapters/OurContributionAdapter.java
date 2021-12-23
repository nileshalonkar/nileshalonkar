package com.example.missionk3.Adapters;


import android.content.Context;
import android.util.Log;
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
import com.example.missionk3.model.ContributionsDataModel;
import com.example.missionk3.model.CoursesDataModel;

import java.util.List;


public class OurContributionAdapter extends RecyclerView.Adapter<OurContributionAdapter.MyViewholder> {

    private Context mContext;
    private List<ContributionsDataModel> contributionsDataModelList;
    public OurContributionAdapter(Context mContext, List<ContributionsDataModel> contributionsDataModelList) {
        this.mContext = mContext;
        this.contributionsDataModelList = contributionsDataModelList;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.contribution_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        ContributionsDataModel contributionsDataModel = contributionsDataModelList.get(position);
        holder.contribution_image_title.setText(contributionsDataModel.getText());
    //    holder.contribution_image.setImageResource(contribution_image[position]);

        if (!contributionsDataModel.equals("")){
            try {
                Glide.with(mContext).load(contributionsDataModel.getPath() + contributionsDataModel.getImage())
                        //     .placeholder(R.drawable.env_three).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.contribution_image);
            } catch (Exception e) {
                Log.e("gtfgdfg", "onBindViewHolder: " + e.getMessage());
            }

        }

    }

    @Override
    public int getItemCount() {
        return contributionsDataModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        //        public ImageSwitcher candidate_post;
        TextView contribution_image_title,course_discription;
//        RelativeLayout courses_relaive;
        ImageView contribution_image;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

//            courses_relaive = itemView.findViewById(R.id.courses_relaive);
            contribution_image_title = itemView.findViewById(R.id.contribution_image_title);
            contribution_image = itemView.findViewById(R.id.contribution_image);
//            user_post = itemView.findViewById(R.id.user_post);
//            location = itemView.findViewById(R.id.location);

        }
    }
}
