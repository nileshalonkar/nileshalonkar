package com.example.missionk3.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;

import java.util.List;


public class OurContributionAdapter extends RecyclerView.Adapter<OurContributionAdapter.MyViewholder> {

    //  THIS ADAPTER IS LIKE THE FEED OF INSTAGRAM  //
    private List<HomeDataModel> homeDataModelList;


// FOR ANY TYPE OF TEXT....

    Context context;
  /*  String[] contribution_image_title = {"Helping People NGO","Child NGO","Vikas Sanstha","Nagar Nigam Sanstha"
    };*/
//    String[] course_discription = {""};



//    String[] caption= {"Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer.",
//            "Hey Buddy I am joining the new job in Accenture as a junior Backend Developer."};


// FOR ANY TYPE OF IMAGES...

   /* int[] contribution_image = {R.drawable.contribution_one, R.drawable.contribution_two, R.drawable.contribution_three,
            R.drawable.contribution_four};*/
//    int[] user_post = {R.drawable.profile, R.drawable.profile_two, R.drawable.profile_three};
//

    public OurContributionAdapter(Context ct,List<HomeDataModel> homeDataModelList) {
        context = ct;
        this.homeDataModelList = homeDataModelList;



    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contribution_view, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        HomeDataModel homeDataModel = homeDataModelList.get(position);


      //  holder.contribution_image_title.setText(contribution_image_title[position]);

       // holder.contribution_image.setImageResource(contribution_image[position]);
        holder.contribution_image_title.setText(homeDataModel.getTitle());
        try {
            Glide.with(context).load(homeDataModel.getPath()+homeDataModel.getImage())
                    //     .placeholder(R.drawable.env_three).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.contribution_image);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return homeDataModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView contribution_image_title,course_discription;
        ImageView contribution_image;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            contribution_image_title = itemView.findViewById(R.id.contribution_image_title);
            contribution_image = itemView.findViewById(R.id.contribution_image);


        }
    }
}
