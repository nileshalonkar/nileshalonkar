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


public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //


// FOR ANY TYPE OF TEXT....

    Context context;
    String[] courses_name = {"Health Course","Meditation Course","Yoga Course","Mind Sharpen Course","Memory Course"
    };
    String[] corses_description = {"Health Courses are very useful for the people. In this you will get know about Health Care Treatment and many more.",
            "This course is related to the Meditation in this we teach you how to meditate yourself in your daily life.",
    "This course is related to the Yoga in this we teach you how to do YOga your daily life.",
    "This course is related to the Mind in this we teach you how to increase your memory."
    ,"This course is related to the Memory in this we teach you how to increase your memory."};






//    String[] caption= {"Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer."};


// FOR ANY TYPE OF IMAGES...

    int[] course_pic = {R.drawable.health_pic, R.drawable.yoga_pic, R.drawable.meditation_two,
            R.drawable.mind_sharpe_pics, R.drawable.mind_divert_pic};
//    int[] user_post = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//

    public CoursesAdapter(Context ct) {
        context = ct;


    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.courses_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        holder.courses_name.setText(courses_name[position]);
        holder.corses_description.setText(corses_description[position]);
//        holder.caption.setText(caption[position]);
        holder.course_pic.setImageResource(course_pic[position]);
//        holder.user_post.setImageResource(user_post[position]);
    }

    @Override
    public int getItemCount() {
        return courses_name.length;
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        //        public ImageSwitcher candidate_post;
        TextView courses_name,corses_description;
        ImageView course_pic;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            courses_name = itemView.findViewById(R.id.courses_name);
            corses_description=itemView.findViewById(R.id.corses_description);
//            location = itemView.findViewById(R.id.location);
            course_pic = itemView.findViewById(R.id.course_pic);
//            user_post = itemView.findViewById(R.id.user_post);

        }
    }
}
