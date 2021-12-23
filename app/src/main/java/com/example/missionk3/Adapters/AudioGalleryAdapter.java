package com.example.missionk3.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionk3.R;
import com.example.missionk3.model.AudioListDataModel;
import com.example.missionk3.model.HomeDataModel;

import java.util.List;
import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;


public class AudioGalleryAdapter extends RecyclerView.Adapter<AudioGalleryAdapter.AudioItemsViewHolder> implements Handler.Callback {
    Context context;
 //   MediaPlayer mediaPlayer;
//    Uri myUri;
 //   private Context mContext;

    private static final int MSG_UPDATE_SEEK_BAR = 1845;
    private MediaPlayer mediaPlayer;
    private Handler uiUpdateHandler;
    private int playingPosition;
    private List<AudioListDataModel> audioListDataModelList;
    private AudioItemsViewHolder playingHolder;
    String User_Id="";

    public AudioGalleryAdapter(Context ct,List<AudioListDataModel> audioListDataModelList) {
        context = ct;
        this.audioListDataModelList = audioListDataModelList;
        this.playingPosition = -1;
        uiUpdateHandler = new Handler(this);
    }

    @NonNull
    @Override
    public AudioItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.audio_view, parent, false);
        return new AudioItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioItemsViewHolder holder, int position) {
        AudioListDataModel audioListDataModel = audioListDataModelList.get(position);
        //  holder.txt_speech_title.setText(homeDataModel.getTitle());

        if (position == playingPosition) {
            playingHolder = holder;
            updatePlayingView();
        } else {

            updateNonPlayingView(holder);
        }
        holder.txtTitle.setText(audioListDataModel.getTitle());


    }
      //   mediaPlayer = new MediaPlayer();

     /*   holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUri = Uri.parse(homeDataModel.getPath());
                holder.btn_play.setVisibility(View.GONE);
                holder.btn_pause.setVisibility(View.VISIBLE);


                    mediaPlayer.stop();
                    mediaPlayer.pause();

            }
        });*/

/*
        holder.btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("jgkjdg", mediaPlayer.getDuration()+"");
                myUri = Uri.parse(homeDataModel.getPath());
                holder.btn_play.setVisibility(View.VISIBLE);
                holder.btn_pause.setVisibility(View.GONE);
                try {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(getApplicationContext(), myUri);
                mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });*/

    @Override
    public int getItemCount() {
        return audioListDataModelList.size();

    }

    @Override
    public void onViewRecycled(AudioItemsViewHolder holder) {
        super.onViewRecycled(holder);
        if (playingPosition == holder.getAdapterPosition()) {
            updateNonPlayingView(playingHolder);
            playingHolder = null;
        }
    }

    private void updateNonPlayingView(AudioItemsViewHolder holder) {
        if (holder == playingHolder) {
            uiUpdateHandler.removeMessages(MSG_UPDATE_SEEK_BAR);
        }

        holder.sbProgress.setEnabled(false);
        holder.sbProgress.setProgress(0);
        holder.ivPlayPause.setImageResource(R.drawable.ic_play_arrow);
    }

    private void updatePlayingView() {
        playingHolder.sbProgress.setMax(mediaPlayer.getDuration());
        playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
        playingHolder.sbProgress.setEnabled(true);
        if (mediaPlayer.isPlaying()) {
            uiUpdateHandler.sendEmptyMessageDelayed(MSG_UPDATE_SEEK_BAR, 100);
            playingHolder.ivPlayPause.setImageResource(R.drawable.ic_pause);
        } else {
            uiUpdateHandler.removeMessages(MSG_UPDATE_SEEK_BAR);
            playingHolder.ivPlayPause.setImageResource(R.drawable.ic_play_arrow);
        }
    }

    void stopPlayer() {
        if (null != mediaPlayer) {
            releaseMediaPlayer();
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case MSG_UPDATE_SEEK_BAR: {
                playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
                uiUpdateHandler.sendEmptyMessageDelayed(MSG_UPDATE_SEEK_BAR, 100);
                return true;
            }
        }
        return false;
    }

    class AudioItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
        SeekBar sbProgress;
        ImageView ivPlayPause;
        TextView txtTitle;

        AudioItemsViewHolder(View itemView) {
            super(itemView);
            ivPlayPause = (ImageView) itemView.findViewById(R.id.ivPlayPause);
            ivPlayPause.setOnClickListener(this);
            sbProgress = (SeekBar) itemView.findViewById(R.id.sbProgress);
            sbProgress.setOnSeekBarChangeListener(this);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == playingPosition) {
                // toggle between play/pause of audio
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            } else {
                // start another audio playback
                playingPosition = getAdapterPosition();
                if (mediaPlayer != null) {
                    if (null != playingHolder) {
                        updateNonPlayingView(playingHolder);
                    }
                    mediaPlayer.release();
                }
                playingHolder = this;
                startMediaPlayer(audioListDataModelList.get(playingPosition).getPath());
            }
            updatePlayingView();
        }

        private void startMediaPlayer(String path) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(path));
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer();
                }
            });
            mediaPlayer.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mediaPlayer.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

   /* private void startMediaPlayer(int audioResId) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        });
        mediaPlayer.start();
    }*/

    private void releaseMediaPlayer() {
        if (null != playingHolder) {
            updateNonPlayingView(playingHolder);
        }
        mediaPlayer.release();
        mediaPlayer = null;
        playingPosition = -1;
    }

 /*   public static class MyViewholder extends RecyclerView.ViewHolder {
     *//*   TextView txt_speech_title;
        RelativeLayout audio_relative;
        ImageView btn_play,btn_pause;*//*


        public MyViewholder(@NonNull View itemView) {
            super(itemView);

        *//*    audio_relative = itemView.findViewById(R.id.audio_relative);
            txt_speech_title = itemView.findViewById(R.id.txt_speech_title);
            btn_play = itemView.findViewById(R.id.btn_play);
            btn_pause = itemView.findViewById(R.id.btn_pause);*//*

        }
    }*/

}
