package com.example.missionk3.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.R;
import com.example.missionk3.model.CoursesDataModel;
import com.example.missionk3.model.HomeDataModel;

import java.util.List;

import hb.xvideoplayer.MxTvPlayerWidget;
import hb.xvideoplayer.MxVideoPlayer;


public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewholder> {
    private Context mContext;
    private List<CoursesDataModel> coursesDataModelList;

    public CoursesAdapter(Context mContext, List<CoursesDataModel> coursesDataModelList) {
        this.mContext = mContext;
        this.coursesDataModelList = coursesDataModelList;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.courses_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        CoursesDataModel coursesDataModel = coursesDataModelList.get(position);
        holder.courses_name.setText(coursesDataModel.getName());
        holder.courses_description.setText(coursesDataModel.getText());

        if (!coursesDataModel.equals("")){
            try {
                holder.mpw_video_player.startPlay(coursesDataModel.getPath(),"");
                //holder.mpw_video_player.startPlay("http://developerdeepika.com/missionk3/image/SampleVideo_1280x720_1mb.mp4",("vedio 1 2"));
            } catch (Exception e) {
                e.printStackTrace();
            }

         /*   try {
                Glide.with(mContext).load(coursesDataModel.getPath() + coursesDataModel.getImage())
                        //     .placeholder(R.drawable.env_three).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.course_pic);
            } catch (Exception e) {
                Log.e("gtfgdfg", "onBindViewHolder: " + e.getMessage());
            }*/

        }

    }

    @Override
    public int getItemCount() {
        return coursesDataModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView courses_name,courses_description;
        ImageView course_pic;
        MxTvPlayerWidget mpw_video_player;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            courses_name = itemView.findViewById(R.id.courses_name);
            courses_description=itemView.findViewById(R.id.courses_description);
//            location = itemView.findViewById(R.id.location);
       //     course_pic = itemView.findViewById(R.id.course_pic);
            mpw_video_player = itemView.findViewById(R.id.mpw_video_player);
//            user_post = itemView.findViewById(R.id.user_post);

        }
    }
}
