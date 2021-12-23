package com.example.missionk3.Adapters;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionk3.R;



public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //

    String CONTENT_URL = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";
   // int playerID=R.id.pv_main;
    int appNameStringRes = R.string.app_name;




// FOR ANY TYPE OF TEXT....

    Context context;

    String[] video_title = {"Motivational Speech",
            "Peace Speech",
            "Mind Sharpen",
            "Yoga Speech",
            "Health Related Speech"};

// FOR ANY TYPE OF IMAGES...

    int[] video_image = {R.drawable.medi_one,
            R.drawable.medi_second,
            R.drawable.medi_three,
            R.drawable.medi_four,
            R.drawable.medi_one};


    public MeditationAdapter(Context ct) {
        context = ct;
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

      //  holder.video_title.setText(video_title[position]);

       // holder.video_image.setImageResource(video_image[position]);

       // holder.bvp.setCaptions(Uri.parse("https://www.example.com/subrip.srt"), CaptionsView.SubMime.SUBRIP);
      //  holder.bvp.setCaptions(Uri.parse("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"), CaptionsView.SubMime.SUBRIP);

       /* Uri video = Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4");
        holder.videoView.setVideoURI(video);
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                holder. videoView.start();
            }
        });


        holder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder. videoView.pause();
            }
        });*/
      // holder. videoView.play(mediaSource);

        //For now we just picked an arbitrary item to play
       // holder.bvp.setVideoURI(Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"));
    }

    @Override
    public int getItemCount() {
        return video_title.length;
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        //public ImageSwitcher candidate_post;
        TextView video_title;
        LinearLayout video_linear;
        ImageView video_image, user_post;

       // VideoView videoView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            video_linear = itemView.findViewById(R.id.video_linear);
           // video_title = itemView.findViewById(R.id.video_title);
           // videoView = itemView.findViewById(R.id.videoView);
           // video_image = itemView.findViewById(R.id.video_image);

        }
    }
}
