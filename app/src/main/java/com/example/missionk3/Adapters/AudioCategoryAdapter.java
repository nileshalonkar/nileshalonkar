package com.example.missionk3.Adapters;


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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.Fragments.AudioListFragment;
import com.example.missionk3.R;
import com.example.missionk3.model.AudioCatDataModel;
import com.example.missionk3.model.HomeDataModel;
import com.facebook.share.Share;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class AudioCategoryAdapter extends RecyclerView.Adapter<AudioCategoryAdapter.MyViewholder>  {
    Context context;

    private List<AudioCatDataModel> audioCatDataModelList;

    public AudioCategoryAdapter(Context ct, List<AudioCatDataModel> audioCatDataModelList) {
        context = ct;
        this.audioCatDataModelList = audioCatDataModelList;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.all_audio_category_layout, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioCategoryAdapter.MyViewholder holder, int position) {
        AudioCatDataModel audioCatDataModel = audioCatDataModelList.get(position);
        holder.txtCatName.setText(audioCatDataModel.getName());

        if (!audioCatDataModel.equals("")){
            Glide.with(context).load(audioCatDataModel.getPath() + audioCatDataModel.getImage())
                    .placeholder(R.drawable.env_three).override(80, 80)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imgCat);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedHelper.putKey(context, AppConstant.AUDIO_CAT_ID,audioCatDataModel.getId());
                Fragment fragment = new AudioListFragment();
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.drawer_layout, fragment).addToBackStack(null).commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return audioCatDataModelList.size();

    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        ImageView imgCat,imgForward;
        TextView txtCatName;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.imgCat);
            imgForward = itemView.findViewById(R.id.imgForward);
            txtCatName = itemView.findViewById(R.id.txtCatName);

        }
    }
}
