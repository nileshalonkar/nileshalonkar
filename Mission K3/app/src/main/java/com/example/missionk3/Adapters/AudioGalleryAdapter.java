package com.example.missionk3.Adapters;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.rygelouv.audiosensei.player.AudioSenseiPlayerView;
import com.rygelouv.audiosensei.player.OnPlayerViewClickListener;
import com.rygelouv.audiosensei.recorder.AudioSensei;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class AudioGalleryAdapter extends  RecyclerView.Adapter<AudioGalleryAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //
    Context context;

   // MediaPlayer mediaPlayer;
    Uri myUri;
    private Context mContext;
    private List<HomeDataModel> homeDataModelList;
    String User_Id="";
    int index=-1;
// FOR ANY TYPE OF TEXT....

   /* String[] txt_speech_title = {"Motivational speech", "Mind Divert speech", "Peace speech", "Shrpen speech"
    ,"Mind Divert speech","Peace speech","Motivational speech","Sharpen speech","Mind Divert speech","Yoga speech"
            ,"Health Related Speech","Sharpen Sppech","Peace Speech","motivational Speech","Yoga Speech"
    };*/
//    String[] location = {"Poland", "Thailand", "Brazil"};
//    String[] caption= {"Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer."};


// FOR ANY TYPE OF IMAGES...

//    int[] user_image = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//    int[] user_post = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//
    public AudioGalleryAdapter(Context ct,List<HomeDataModel> homeDataModelList) {
        context = ct;
        this.homeDataModelList = homeDataModelList;



    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.audio_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        HomeDataModel homeDataModel = homeDataModelList.get(position);

//        holder.txt_speech_title.setText(homeDataModel.getTitle());
     //   holder.txt_speech_title.setText(txt_speech_title[position]);

        //MyAudio myAudio = audioArrayList.get(position);



       // holder.audioSenseiPlayerView.setAudioTarget(MEDIA_RES_ID);

      /*  holder.audioSenseiPlayerView.registerViewClickListener(R.id.button_play, new OnPlayerViewClickListener()
        {
            @Override
            public void onPlayerViewClick(View view)
            {
                Log.i(TAG, "onPlayer view Clicked play");
                holder.audioSenseiPlayerView.stop();
                holder.audioSenseiPlayerView.setAudioTarget(Uri.parse(homeDataModel.getDownload()));
                // holder.audioTitle.setText(myAudio.name);

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(String.valueOf(Uri.parse(homeDataModel.getDownload())));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    // make something
                }
            }
        });
        holder.audioSenseiPlayerView.registerViewClickListener(R.id.stop, new OnPlayerViewClickListener()
        {
            @Override
            public void onPlayerViewClick(View view)
            {
                Toast.makeText(getApplicationContext(), "pause Audio", Toast.LENGTH_LONG).show();
                holder.audioSenseiPlayerView.stop();
            }
        });*/
        holder.audioSenseiPlayerView.commitClickEvents();
        View playerRootView = holder.audioSenseiPlayerView.getPlayerRootView();
            // initialize Uri here
           //  myUri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba-online-audio-converter.com_-1.wav"); // initialize Uri here
           // mediaPlayer = new MediaPlayer();

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {

            mediaPlayer.setDataSource(String.valueOf(Uri.parse(homeDataModel.getDownload())));
        } catch (IOException e) {
            e.printStackTrace();
        }


        holder.btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=position;
                notifyDataSetChanged();

               /* try {
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                // myUri = Uri.parse(homeDataModel.getDownload());
                holder.btn_play.setVisibility(View.VISIBLE);
                holder.btn_pause.setVisibility(View.GONE);
                Log.i(TAG, "onPlayer view Clicked play");





            }
        });


        if (index==position){
            try {
                Log.e("fgkhdfjk","1");
                mediaPlayer.prepare();
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
        }else{
            try {
                Log.e("fgkhdfjk","2");

                holder.btn_play.setVisibility(View.GONE);
                holder.btn_pause.setVisibility(View.VISIBLE);
                mediaPlayer.stop();
                Toast.makeText(getApplicationContext(), "pause Audio", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // make something
            }
        }

        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                holder.btn_play.setVisibility(View.GONE);
                holder.btn_pause.setVisibility(View.VISIBLE);
                try {
                    mediaPlayer.stop();
                    Toast.makeText(getApplicationContext(), "pause Audio", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    // make something
                }

            }
        });


//        holder.location.setText(location[position]);
//        holder.caption.setText(caption[position]);
//        holder.user_image.setImageResource(user_image[position]);
//        holder.user_post.setImageResource(user_post[position]);
    }

    @Override
    public int getItemCount() {
       // return txt_speech_title.length;
        return homeDataModelList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        //        public ImageSwitcher candidate_post;
        TextView txt_speech_title;
        RelativeLayout audio_relative;
        ImageView btn_play,btn_pause;
        AudioSenseiPlayerView audioSenseiPlayerView;
//        ImageView user_image, user_post;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            audio_relative = itemView.findViewById(R.id.audio_relative);
           txt_speech_title = itemView.findViewById(R.id.txt_speech_title);
            btn_play = itemView.findViewById(R.id.btn_play);
            btn_pause = itemView.findViewById(R.id.btn_pause);
            audioSenseiPlayerView = itemView.findViewById(R.id.audio_player);

//            caption=itemView.findViewById(R.id.caption);
//            location = itemView.findViewById(R.id.location);
//            user_image = itemView.findViewById(R.id.user_image);
//            user_post = itemView.findViewById(R.id.user_post);

        }
    }
}
