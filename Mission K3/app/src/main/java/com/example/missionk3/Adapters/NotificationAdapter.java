package com.example.missionk3.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //


// FOR ANY TYPE OF TEXT....

    Context context;
    String[] notification_txt = {"Video is uploaded","Upcoming video","Speech out guys","Video is on the Way"
    };
    String[] time_ago = {"24hr ago", "1min ago", "1day ago","10hr ago"};
//    String[] caption= {"Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer."};


// FOR ANY TYPE OF IMAGES...

//    int[] user_image = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//    int[] user_post = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//

    public NotificationAdapter(Context ct) {
        context = ct;


    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notification_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        holder.notification_txt.setText(notification_txt[position]);
        holder.time_ago.setText(time_ago[position]);
//        holder.caption.setText(caption[position]);
//        holder.user_image.setImageResource(user_image[position]);
//        holder.user_post.setImageResource(user_post[position]);
    }

    @Override
    public int getItemCount() {
        return notification_txt.length;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        //        public ImageSwitcher candidate_post;
        TextView notification_txt,time_ago;
//        ImageView user_image, user_post;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            notification_txt = itemView.findViewById(R.id.notification_txt);
            time_ago=itemView.findViewById(R.id.time_ago);
//            location = itemView.findViewById(R.id.location);
//            user_image = itemView.findViewById(R.id.user_image);
//            user_post = itemView.findViewById(R.id.user_post);

        }
    }
}
