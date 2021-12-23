package com.example.missionk3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionk3.R;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewholder> {

    Context context;
    String[] notification_txt = {"Video is uploaded","Upcoming video","Speech out guys","Video is on the Way"
    };
    String[] time_ago = {"24hr ago", "1min ago", "1day ago","10hr ago"};


    public CardListAdapter(Context ct) {
        context = ct;


    }

    @NonNull
    @Override
    public CardListAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardlist_view, parent, false);
        return new CardListAdapter.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListAdapter.MyViewholder holder, int position) {

//        holder.notification_txt.setText(notification_txt[position]);
//        holder.time_ago.setText(time_ago[position]);
//        holder.caption.setText(caption[position]);
//        holder.user_image.setImageResource(user_image[position]);
//        holder.user_post.setImageResource(user_post[position]);
    }

    @Override
    public int getItemCount() {
        return 3;
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
