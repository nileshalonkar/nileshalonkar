package com.example.missionk3.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.Fragments.AboutUs_Fragment;
import com.example.missionk3.Fragments.AudioGallery_Fragment;
import com.example.missionk3.Fragments.BecomeMember_Fragment;
import com.example.missionk3.Fragments.Courses_Fragment;
import com.example.missionk3.Fragments.Profile_Fragment;
import com.example.missionk3.Fragments.Home_Fragment;
import com.example.missionk3.Fragments.Meditation_Fragment;
import com.example.missionk3.Fragments.MissionK3Family_Fragment;
import com.example.missionk3.Fragments.Notification_Fragment;
import com.example.missionk3.Fragments.OurContribution_Fragment;
import com.example.missionk3.Fragments.Setting_Fragment;
import com.example.missionk3.Fragments.VideoGallery_Fragment;
import com.example.missionk3.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    FragmentTransaction ft;
    Home_Fragment dbHome;
    VideoGallery_Fragment dbGallery;
    Meditation_Fragment dbSlideshow;
    Courses_Fragment dbCourses;
    Home_Fragment dbHouse;
    AudioGallery_Fragment dbAudioGallery;
    OurContribution_Fragment dbOurContribution;
    BecomeMember_Fragment dbBecome_a_member;
    MissionK3Family_Fragment dbMissionK3Family;
    Notification_Fragment dbNotification;
    Profile_Fragment db_Profile;
    Setting_Fragment dbSetting;
    AboutUs_Fragment dbAboutus;
    CircleImageView person_image_home;
    TextView txtNameHome;
    String User_Id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // person_image_home = findViewById(R.id.person_image_home);
       // txtNameHome = findViewById(R.id.txtNameHome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        User_Id = SharedHelper.getKey(MainActivity.this,AppConstant.USERID);
        Log.e("rfgsdffd", User_Id);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);



        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadDbHomefragment();



        View hView =  navigationView.getHeaderView(0);
       // View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
         person_image_home = (CircleImageView) hView.findViewById(R.id.person_image_home);
         txtNameHome = (TextView)hView.findViewById(R.id.txtNameHome);




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.open();
            }
        });


        Show_My_Profile();

    }
    private void loadDbHomefragment() {
        dbHome = new Home_Fragment();
        fragment = dbHome;
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.db_frame,fragment);
        ft.commitAllowingStateLoss();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.drawer_menu, menu);
//        return false;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_audio_gallery) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("Home");
            dbHouse = new Home_Fragment();
            fragment = dbHouse;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame,fragment);
            ft.commitAllowingStateLoss();

        } else if (id == R.id.nav_audio_gallery) {
            getSupportActionBar().setTitle("Audio Gallery");

            dbAudioGallery = new AudioGallery_Fragment();
            fragment = dbAudioGallery;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame,fragment);
            ft.commitAllowingStateLoss();

      /*  } else if (id == R.id.nav_video_gallery) {
            getSupportActionBar().setTitle("Video Gallery");
            dbGallery = new VideoGallery_Fragment();
            fragment = dbGallery;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame,fragment);
            ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
*/

        } else if (id == R.id.nav_meditation) {
            getSupportActionBar().setTitle("Meditation Videos");
            dbSlideshow = new Meditation_Fragment();
            fragment = dbSlideshow;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame,fragment);
            ft.commitAllowingStateLoss();


        } else if (id == R.id.nav_courses) {
            getSupportActionBar().setTitle("Courses");
            dbCourses = new Courses_Fragment();
            fragment =dbCourses;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame,fragment);
            ft.commitAllowingStateLoss();


        }else if (id == R.id.nav_our_contribution) {
            getSupportActionBar().setTitle("Our Contribution");
            dbOurContribution = new OurContribution_Fragment();
            fragment = dbOurContribution;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();


        }else if (id == R.id.nav_become_a_member) {
            getSupportActionBar().setTitle("Become a Member");
            dbBecome_a_member = new BecomeMember_Fragment();
            fragment = dbBecome_a_member;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();

        }else if (id == R.id.nav_mission_k_family) {
            getSupportActionBar().setTitle("Mission K3 Family");
            dbMissionK3Family = new MissionK3Family_Fragment();
            fragment = dbMissionK3Family;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();


        }else if (id == R.id.nav_notification) {
            getSupportActionBar().setTitle("Notifications");
            dbNotification = new Notification_Fragment();
            fragment = dbNotification;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();


        }else if (id == R.id.nav_edit_profile) {
            getSupportActionBar().setTitle("Edit Profile");
            db_Profile = new Profile_Fragment();
            fragment = db_Profile;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();



        }else if (id == R.id.nav_setting) {
            getSupportActionBar().setTitle("Setting");
            dbSetting = new Setting_Fragment();
            fragment = dbSetting;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();


        }else if (id == R.id.nav_about_us) {
            getSupportActionBar().setTitle("About us");
            dbAboutus = new AboutUs_Fragment();
            fragment = dbAboutus;
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.db_frame, fragment);
            ft.commitAllowingStateLoss();

        }else if (id == R.id.nav_log_out) {

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(MainActivity.this);
                    }
                    builder.setTitle(getResources().getString(R.string.app_name))
                            .setMessage("Are you sure you want to logout in the app")
                            .setPositiveButton(Html.fromHtml("<font color='#FF8C00'>Ok</font>"), new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                public void onClick(final DialogInterface dialog, int which) {
                                    SharedHelper.putKey(MainActivity.this, AppConstant.USERID,"");
                                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                                    if(Build.VERSION.SDK_INT >= 11) {
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    } else {
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    }
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(Html.fromHtml("<font color='#FF8C00'>Cancel</font>"), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.logout)
                            .show();


           /* Intent intent=new Intent(MainActivity.this,SigninActivity.class);
            startActivity(intent);
            finish();*/

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    private void Show_My_Profile() {
        //    progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(Api.show_profile)
                .addBodyParameter("id", User_Id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("eferwfr", "" + response.toString());

                        try {
                            if (response.getString("result").equals("show profile successfully")) {
                              //   Toast.makeText(MainActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                Glide.with(MainActivity.this).load(response.getString("path") + response.getString("image"))
                                       .placeholder(R.drawable.profile_two).override(50, 50)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(person_image_home);
                                txtNameHome.setText(response.getString("name"));
                                // startActivity(new Intent(getActivity(), HomeActivity.class));
                                Log.e("tdgffdg",response.getString("name") );

                            } else {
                                Toast.makeText(MainActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dgfffdf", e.getMessage());
                            //     progressBar.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("sdafetrr", "onError: " + anError);
                        //   progressBar.setVisibility(View.GONE);
                    }
                });
    }
}