package com.example.missionk3.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionk3.R;


public class MissionK3FamilyAdapter extends RecyclerView.Adapter<MissionK3FamilyAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //


// FOR ANY TYPE OF TEXT....

    Context context;
//    String[] contribution_image_title = {"Helping People NGO","Child NGO","Vikas Sanstha","Nagar Nigam Sanstha"
//    };
//    String[] course_discription = {""};



//    String[] caption= {"Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer."};


// FOR ANY TYPE OF IMAGES...

    int[] visiting_card = {R.drawable.visiting, R.drawable.visiting_one, R.drawable.visiting_three,
            R.drawable.visiting_two};
//    int[] user_post = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//

    public MissionK3FamilyAdapter(Context ct) {
        context = ct;


    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.missionk3_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

//        holder.contribution_image_title.setText(contribution_image_title[position]);
//        holder.course_discription.setText(course_discription[position]);
//        holder.caption.setText(caption[position]);
        holder.visiting_card.setImageResource(visiting_card[position]);
//        holder.user_post.setImageResource(user_post[position]);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        //        public ImageSwitcher candidate_post;
//        TextView contribution_image_title,course_discription;
        //        RelativeLayout courses_relaive;
        ImageView visiting_card;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

//            courses_relaive = itemView.findViewById(R.id.courses_relaive);
//            contribution_image_title = itemView.findViewById(R.id.contribution_image_title);
            visiting_card = itemView.findViewById(R.id.visiting_card);
//            user_post = itemView.findViewById(R.id.user_post);
//            location = itemView.findViewById(R.id.location);

        }
    }
}
