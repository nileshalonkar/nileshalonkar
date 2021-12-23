package com.example.missionk3.Adapters;


import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.BuildConfig;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.artjimlop.altex.AltexImageDownloader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.Activities.Api;
import com.example.missionk3.Activities.MainActivity;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;
import com.example.missionk3.model.HomeDataModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hb.xvideoplayer.MxTvPlayerWidget;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewholder> {

    private Context mContext;
    private List<HomeDataModel> homeDataModelList;
    String User_Id="";
    RecyclerView rvSubcategory;


    String url = "";
    File file;
    String dirPath, fileName;
    Button downldImg;

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
          holder.txtDescription.setText(homeDataModel.getTitle());

            Glide.with(mContext).load(homeDataModel.getPath() + homeDataModel.getFile())
                    //     .placeholder(R.drawable.env_three).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.user_post);

         /*   try {
                Glide.with(mContext).load(homeDataModel.getPath() + homeDataModel.getFile())
                        //     .placeholder(R.drawable.env_three).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.user_post);
            } catch (Exception e) {
                Log.e("gtfgdfg", "onBindViewHolder: " + e.getMessage());
            }

            try {
                holder.mpw_video_player_home.startPlay(homeDataModel.getPath()+homeDataModel.getFile());
            } catch (Exception e) {
                Log.e("gtfgdfg", "onBindViewHolder: " + e.getMessage());
            }*/

/*
            if (homeDataModel.getFile_type_status().equals("1")) {
                    Glide.with(mContext).load(homeDataModel.getPath() + homeDataModel.getFile())
                            //     .placeholder(R.drawable.env_three).override(250, 250)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.user_post);
                    holder.mpw_video_player_home.setVisibility(View.GONE);
                    holder.user_post.setVisibility(View.VISIBLE);
                    holder.txtDescription.setVisibility(View.VISIBLE);
                }

            }else {

        }*/

            }

          /*  if (homeDataModel.getFile_type_status().equals("2")) {
                    holder.mpw_video_player_home.startPlay(homeDataModel.getPath() + homeDataModel.getFile(),"");
                    holder.mpw_video_player_home.setVisibility(View.VISIBLE);
                    holder.user_post.setVisibility(View.GONE);
                    holder.txtDescription.setVisibility(View.GONE);
                    holder.ll_hr.setVisibility(View.GONE);

            }else {



            }
*/

        holder.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

Log.e("dfgkjhdfjkgh",homeDataModel.getPath()+homeDataModel.getFile()+"");
                AltexImageDownloader.writeToDisk(mContext, homeDataModel.getPath() + homeDataModel.getFile(), "Mission K3");
                Toast.makeText(mContext, "Image Saved",
                        Toast.LENGTH_SHORT).show();

               /* try {
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
*/


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

               // String image_url = "http://images.cartradeexchange.com//img//800//vehicle//Honda_Brio_562672_5995_6_1438153637072.jpg";
                String image_url = homeDataModel.getPath()+homeDataModel.getFile();

                Intent shareIntent = new Intent();
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.setAction(Intent.ACTION_SEND);
                //without the below line intent will show error
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, image_url);
                // Target whatsapp:
                shareIntent.setPackage("com.whatsapp");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    mContext.startActivity(shareIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(mContext,
                            "Whatsapp have not been installed.",
                            Toast.LENGTH_SHORT).show();
                }
             /*   Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");

                intent.putExtra(Intent.EXTRA_TEXT,"http://eofdreams.com/data_images/dreams/face/face-03.jpg");

                mContext.startActivity(Intent.createChooser(intent, "Share Image"));
*/
              /*  try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "share demo");
                    String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    mContext.startActivity(Intent.createChooser(intent, "share by"));

                } catch (Exception e) {
                    Toast.makeText(mContext, "Error occured", Toast.LENGTH_SHORT).show();

                }*/
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

   //     ShowAllPost();

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
        MxTvPlayerWidget mpw_video_player_home;
        LinearLayout ll_hr;


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
       //     mpw_video_player_home = itemView.findViewById(R.id.mpw_video_player_home);
            ll_hr = itemView.findViewById(R.id.ll_hr);

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
                             //  Toast.makeText(mContext, ""+response.getString("result"), Toast.LENGTH_SHORT).show();
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
                              //  Toast.makeText(mContext, ""+response.getString("result"), Toast.LENGTH_SHORT).show();
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

  /*  public void ShowAllPost() {
        AndroidNetworking.post(Api.show_post)
                // .addBodyParameter("user_id",User_Id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ghjfgkjgfdg", response.toString());
                        homeDataModelList = new ArrayList<>();
                        try {

                            JSONArray jsonArray = new JSONArray(response.getString("resasdault"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HomeDataModel homeDataModel = new HomeDataModel();
                                homeDataModel.setId(jsonObject.getString("id"));
                                homeDataModel.setCategory_id(jsonObject.getString("category_id"));
                                homeDataModel.setTitle(jsonObject.getString("title"));
                                homeDataModel.setDescription(jsonObject.getString("description"));
                                homeDataModel.setImage(jsonObject.getString("image"));
                                homeDataModel.setPath(jsonObject.getString("path"));
                                homeDataModel.setDownload(jsonObject.getString("download"));
                                homeDataModel.setLike_status(jsonObject.getString("like_status"));

                                JSONArray jsonArray1 = jsonObject.getJSONArray("show_file");
                                for (int j = 0; j <jsonArray1.length() ; j++) {
                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                    homeDataModel.setFile(jsonObject1.getString("file"));
                                    homeDataModel.setFile_type_status(jsonObject1.getString("file_type_status"));
                                    homeDataModel.setThumbnail_image(jsonObject1.getString("thumbnail_image"));

                                }

                                homeDataModelList.add(homeDataModel);
                            }
                            rvSubcategory.setHasFixedSize(true);
                            rvSubcategory.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
                            rvSubcategory.setAdapter(new HomeAdapter(mContext, homeDataModelList));

                        } catch (Exception exception) {
                            Log.e("abcdhd", exception.getMessage());


                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("abcdhd", "onError: " + anError);

                    }
                });

    }
*/

}
