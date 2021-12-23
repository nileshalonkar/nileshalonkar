package com.example.missionk3.Adapters;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.BuildConfig;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewholder> {

    private Context mContext;
    private List<HomeDataModel> homeDataModelList;
    String User_Id="";


    public HomeAdapter(Context mContext, List<HomeDataModel> homeDataModelList) {
        this.mContext = mContext;
        this.homeDataModelList = homeDataModelList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_view, parent, false);
        User_Id = SharedHelper.getKey(mContext, AppConstant.USERID);
        Log.e("fsgfdgdfg", User_Id );
        return new HomeAdapter.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        HomeDataModel homeDataModel = homeDataModelList.get(position);

        if (!homeDataModel.equals("")) {
          holder.txtDescription.setText(homeDataModel.getDescription());

          Log.e("fjgjkdf",homeDataModel.getPath()+homeDataModel.getImage());
            try {
                Glide.with(mContext).load(homeDataModel.getPath()+homeDataModel.getImage())
                   //     .placeholder(R.drawable.env_three).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.user_post);
            } catch (Exception e) {

            }

            Log.e("dfdfdfdf", homeDataModel.getPath() + homeDataModel.getImage());
        }

        holder.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Bitmap bitmap = ((BitmapDrawable)holder.user_post.getDrawable()).getBitmap();
                    String time  = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
                    File path  = Environment.getExternalStorageDirectory();
                    File dir = new File(path+"/Mission K3");
                    dir.mkdir();
                    String imagename = time+".PNG";
                    File file = new File(dir,imagename);
                    OutputStream out;
                    try {
                        out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
                        out.flush();
                        out.close();
                        Toast.makeText(mContext, "Image Save To Mission K3",
                                Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Log.e("dfgjhdfgkj",e.getMessage());
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



              /*  DownloadManager mgr = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

                Uri downloadUri = Uri.parse(Status);
                DownloadManager.Request request = new DownloadManager.Request(
                        downloadUri);

                request.setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_WIFI
                                | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false).setTitle("Demo")
                        .setDescription("Something useful. No, really.")
                        .setDestinationInExternalPublicDir("/post image", "fileName.jpg");

                mgr.enqueue(request);
*/
            }
        });

        holder.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "share demo");
                    String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    mContext.startActivity(Intent.createChooser(intent, "share by"));

                } catch (Exception e) {
                    Toast.makeText(mContext, "Error occured", Toast.LENGTH_SHORT).show();

                }
            }
        });

        if (homeDataModel.getLike_status().equals("1")){
            holder.like_red.setVisibility(View.VISIBLE);
            holder.like.setVisibility(View.GONE);
        }else {
            holder.like_red.setVisibility(View.GONE);
            holder.like.setVisibility(View.VISIBLE);
        }

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFav( homeDataModel.getId(),holder);

            }
        });
        holder.like_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnAddFav( homeDataModel.getId(),holder);
            }
        });

    }
    @Override
    public int getItemCount() {
        return  homeDataModelList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView name_user,txtDescription;
        RelativeLayout postrelative;
        ImageView user_image, user_post,imgDownload,imgShare;
        ImageView like,like_red;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            postrelative = itemView.findViewById(R.id.postrelative);
            name_user = itemView.findViewById(R.id.name_user);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            user_image = itemView.findViewById(R.id.user_image);
            user_post = itemView.findViewById(R.id.user_post);
            imgDownload = itemView.findViewById(R.id.imgDownload);
            imgShare = itemView.findViewById(R.id.imgShare);
            like = itemView.findViewById(R.id.like);
            like_red = itemView.findViewById(R.id.like_red);

        }
    }

    public void AddFav(String id, MyViewholder holder) {
        AndroidNetworking.post(Api.add_favourite)
                .addBodyParameter("user_id", User_Id)
                .addBodyParameter("post_id", id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("dhdgnfn", response.toString());

                        try {
                            if (response.getString("result").equals("Add to like")) {
                                Toast.makeText(mContext, ""+response.getString("result"), Toast.LENGTH_SHORT).show();
                                holder.like_red.setVisibility(View.VISIBLE);
                                holder.like.setVisibility(View.GONE);
                            } else {
                                Toast toast = Toast.makeText(mContext, "" + response.getString("result"), Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        } catch (JSONException e) {

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    public void UnAddFav(String id, MyViewholder holder) {
        AndroidNetworking.post(Api.add_favourite)
                .addBodyParameter("user_id", User_Id)
                .addBodyParameter("post_id", id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("dhdgnfn", response.toString());

                        try {
                            if (response.getString("result").equals("Remove like")) {
                                Toast.makeText(mContext, ""+response.getString("result"), Toast.LENGTH_SHORT).show();
                                holder.like_red.setVisibility(View.GONE);
                                holder.like.setVisibility(View.VISIBLE);
                            } else {
                                Toast toast = Toast.makeText(mContext, "" + response.getString("result"), Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        } catch (JSONException e) {

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

}
