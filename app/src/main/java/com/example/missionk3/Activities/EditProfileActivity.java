package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.Fragments.Profile_Fragment;
import com.example.missionk3.R;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class EditProfileActivity extends AppCompatActivity {
    Button btn_edit_profile;
    CircleImageView ProfileCircleImg;
    EditText etName,etEmail,etMobile,etPassword;
    ImageButton btn_back;
    MaterialButton btnSubmit;

    String User_Id="";

    private static final String IMAGE_DIRECTORY = "/directory";
    File f;
    String strimage="";
    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btn_edit_profile=findViewById(R.id.btn_edit_profile);
        ProfileCircleImg=findViewById(R.id.ProfileCircleImg);
        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etMobile=findViewById(R.id.etMobile);
        etPassword=findViewById(R.id.etPassword);
        btn_back=findViewById(R.id.btn_back);
        btnSubmit=findViewById(R.id.btnSubmit);

        User_Id = SharedHelper.getKey(EditProfileActivity.this, AppConstant.USERID);
        Log.e("dfsdfdsfd", User_Id);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ProfileCircleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update_Profile();
            }
        });

        Show_My_Profile();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showPictureDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Select Action");
        String[] pictureDialogItems = {"Select photo from gallery", "Capture image from camera"};

        builder.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case 0:
                        choosePhotoFromGallery();
                        break;

                    case 1:
                        captureFromCamera();
                        break;
                }

            }
        });

        builder.show();
    }


    public void choosePhotoFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, GALLERY);
    }


    public void captureFromCamera() {

        Intent intent_2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent_2, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditProfileActivity.this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    strimage= String.valueOf(f);
                    ProfileCircleImg.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfileActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ProfileCircleImg.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            strimage= String.valueOf(f);
            Toast.makeText(EditProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);

        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(EditProfileActivity.this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("fvbcbv", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void Update_Profile() {
        //      progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.upload(Api.update_profile)
                .addMultipartParameter("id", User_Id)
                .addMultipartParameter("name", etName.getText().toString())
                .addMultipartParameter("email", etEmail.getText().toString())
                .addMultipartParameter("mobile", etMobile.getText().toString())
                .addMultipartParameter("password", etPassword.getText().toString())
                .addMultipartFile("image", f)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //     progressBar.setVisibility(View.GONE);
                        Log.e("eferwfr", "" + response.toString());

                        try {
                            if (response.getString("result").equals("update successfully")) {
                                Toast.makeText(EditProfileActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(EditProfileActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dgfffdf", e.getMessage());
                            //  progressBar.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fytfytf", "onError: " + anError);
                        // progressBar.setVisibility(View.GONE);
                    }
                });

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
                                Toast.makeText(EditProfileActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                Glide.with(EditProfileActivity.this).load(response.getString("path") + response.getString("image"))
                                        //  .placeholder(R.drawable.shop_img).override(250, 250)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(ProfileCircleImg);
                                etName.setText(response.getString("name"));
                                etEmail.setText(response.getString("email"));
                                etMobile.setText(response.getString("phone"));
                                etPassword.setText(response.getString("password"));
                                // startActivity(new Intent(getActivity(), HomeActivity.class));

                            } else {
                                Toast.makeText(EditProfileActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();

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




//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                == PackageManager.PERMISSION_DENIED){
//            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
//
//
//        }else{
//            // ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
//
//        }
//
//
//        user_dp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.activity()
//                        .start(Edit_ProfileActivity.this);
//            }
//        });
//    }
//    @Override
//    public void onRequestPermissionsResult(
//            int requestCode, String permissions[], int[] grantResults) {
//        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                CropImage.startPickImageActivity(this);
//            } else {
//                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
//                        .show();
//            }
//        }
//        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
////            if (mCropImageUri != null
////                    && grantResults.length > 0
////                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                mCurrentFragment.setImageUri(mCropImageUri);
////            } else {
////                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
////                        .show();
////            }
//        }
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//                user_dp.setImageURI(resultUri);
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }
//        }