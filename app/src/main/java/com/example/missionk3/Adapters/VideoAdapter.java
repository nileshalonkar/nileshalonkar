package com.example.missionk3.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionk3.Activities.MainActivity;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.example.missionk3.model.VideoDataModel;

import java.util.List;

import hb.xvideoplayer.MxTvPlayerWidget;
import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //


// FOR ANY TYPE OF TEXT....

    Context context;
    private List<VideoDataModel> videoDataModelList;
    MediaController mediaControls;


   /* String[] video_title = {"Motivational Speech",
            "Peace Speech",
            "Mind Sharpen",
            "Yoga Speech",
            "Health Related Speech"};


    int[] video_image = {R.drawable.envi_first,
            R.drawable.envi_sec,
            R.drawable.envi_three,
            R.drawable.envi_four,
            R.drawable.envi_five};*/
//    int[] user_post = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//

    public VideoAdapter(Context ct, List<VideoDataModel> videoDataModelList) {
        context = ct;
        this.videoDataModelList = videoDataModelList;


    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        VideoDataModel videoDataModel = videoDataModelList.get(position);
        // holder.video_title.setText(video_title[position]);
        holder.video_title.setText(videoDataModel.getTitle());
        Log.e("FRgfdgdfgd", videoDataModel.getTitle());

        //   holder.mpw_video_player.startPlay("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "video name");

        try {
            holder.mpw_video_player.startPlay(videoDataModel.getPath(),videoDataModel.getDescription());
            //holder.mpw_video_player.startPlay("http://developerdeepika.com/missionk3/image/SampleVideo_1280x720_1mb.mp4",("vedio 1 2"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // holder.mpw_video_player.startPlay("https://www.youtube.com/watch?v=HexFqifusOk&list=RDHexFqifusOk&start_radio=1", MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "video name");

        //   holder.location.setText(location[position]);
        // holder.caption.setText(caption[position]);
        // holder.video_image.setImageResource(video_image[position]);
        // holder.user_post.setImageResource(user_post[position]);
    }


    @Override
    public int getItemCount() {
        return videoDataModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        //        public ImageSwitcher candidate_post;
        TextView video_title;
        LinearLayout video_linear;
        ImageView video_image, user_post;
        MxTvPlayerWidget mpw_video_player;


        public MyViewholder(@NonNull View itemView) {
            super(itemView);

         //   video_linear = itemView.findViewById(R.id.video_linear);
            video_title = itemView.findViewById(R.id.video_title);
            // caption=itemView.findViewById(R.id.caption);
            //  location = itemView.findViewById(R.id.location);
            // video_image = itemView.findViewById(R.id.video_image);
            user_post = itemView.findViewById(R.id.user_post);
            mpw_video_player  = itemView.findViewById(R.id.mpw_video_player );
        }
    }

}
