package com.example.missionk3.Adapters;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
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

import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.util.List;

import hb.xvideoplayer.MxTvPlayerWidget;
import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;

import static android.content.ContentValues.TAG;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //

int index = -1;
// FOR ANY TYPE OF TEXT....

    Context context;
    private List<HomeDataModel> homeDataModelList;
    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;
    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";


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

    public VideoAdapter(Context ct,List<HomeDataModel> homeDataModelList) {
        context = ct;
        this.homeDataModelList = homeDataModelList;



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
        HomeDataModel homeDataModel = homeDataModelList.get(position);

       // holder.video_title.setText(video_title[position]);


/*
       holder. mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = holder.mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = holder.mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                holder.mVideoLayout.setLayoutParams(videoLayoutParams);
                holder.mVideoView.setVideoPath(VIDEO_URL);
                holder.mVideoView.requestFocus();
            }
        });
*/


        index=position;


        holder.mVideoView.setMediaController(holder.mMediaController);

        holder.mVideoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            @Override
            public void onScaleChange(boolean isFullscreen) {
               // this.isFullscreen = isFullscreen;
                if (isFullscreen) {
                    ViewGroup.LayoutParams layoutParams = holder.mVideoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    holder. mVideoLayout.setLayoutParams(layoutParams);
                    //GONE the unconcerned views to leave room for video and controller
                    holder.mBottomLayout.setVisibility(View.GONE);
                } else {
                    ViewGroup.LayoutParams layoutParams = holder.mVideoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    //layoutParams.height = context.this.cachedHeight;
                    holder.mVideoLayout.setLayoutParams(layoutParams);
                    holder.mBottomLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPause(MediaPlayer mediaPlayer) { // Video pause
                Log.e("kjghkfjghfkg", "1");
               // holder.mVideoView.pause();


            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) { // Video start/resume to play
                Log.e("kjghkfjghfkg", "2");
               // holder.mVideoView.start();



              //  holder.mVideoView.start();


            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {// steam start loading
                Log.e("kjghkfjghfkg", "3");
               // holder.mVideoView.pause();

            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {// steam end loading
                Log.e("kjghkfjghfkg", "4");
               // holder.mVideoView.start();

            }

        });


        if (position==0){
            holder.mVideoView.setMediaController(holder.mMediaController);
            holder.mVideoView.setVideoPath(homeDataModel.getVideo());
            holder.mVideoView.requestFocus();
            holder.mVideoView.start();
            holder.mMediaController.setTitle(homeDataModel.getTitle());

        }else{
            holder.mVideoView.setMediaController(holder.mMediaController);
            holder.mVideoView.setVideoPath(homeDataModel.getVideo());
            holder.mVideoView.requestFocus();
            holder.mVideoView.pause();
            holder.mMediaController.setTitle(homeDataModel.getTitle());

        }


     //   holder.mpw_video_player.startPlay("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "video name");
      /*  try {
            holder.video_title.setText(homeDataModel.getTitle());
            holder.mpw_video_player.startPlay(homeDataModel.getVideo(), MxVideoPlayer.SCREEN_LAYOUT_NORMAL, homeDataModel.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        // holder.mpw_video_player.startPlay("https://www.youtube.com/watch?v=HexFqifusOk&list=RDHexFqifusOk&start_radio=1", MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "video name");

        //   holder.location.setText(location[position]);
       // holder.caption.setText(caption[position]);
       // holder.video_image.setImageResource(video_image[position]);
       // holder.user_post.setImageResource(user_post[position]);
    }


    @Override
    public int getItemCount() {
        return homeDataModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        //        public ImageSwitcher candidate_post;
        TextView video_title;
        LinearLayout video_linear;
        ImageView video_image, user_post;
        MxVideoPlayerWidget mpw_video_player;

        View mBottomLayout;
        View mVideoLayout;
        UniversalVideoView mVideoView;
        UniversalMediaController mMediaController;


        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            video_linear = itemView.findViewById(R.id.video_linear);
           // video_title = itemView.findViewById(R.id.video_title);
           // caption=itemView.findViewById(R.id.caption);
          //  location = itemView.findViewById(R.id.location);
           // video_image = itemView.findViewById(R.id.video_image);
            user_post = itemView.findViewById(R.id.user_post);
          //  mpw_video_player  = itemView.findViewById(R.id.mpw_video_player );


            mVideoView = (UniversalVideoView) itemView.findViewById(R.id.videoView);
            mMediaController = (UniversalMediaController) itemView.findViewById(R.id.media_controller);
           // mVideoView.setMediaController(mMediaController);



        }
    }
}
