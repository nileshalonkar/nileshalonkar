package com.example.missionk3.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.missionk3.Activities.ShareHelper.AppConstant;
import com.example.missionk3.Activities.ShareHelper.SharedHelper;
import com.example.missionk3.R;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {
    MaterialButton btn_submit;
    CircleImageView ProfileCircleImage;
    RelativeLayout sugnup_relative;
    TextView txt_signin;
    EditText enter_email, enter_password, enter_confirm_password, enter_name, enter_mobileno;

    CircleImageView user_dp;
    ProgressBar progressBar;

    private static final String IMAGE_DIRECTORY = "/directory";
    File f;
    String strimage="";
    private int GALLERY = 1, CAMERA = 2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        //TODO--ALL ID'S

        sugnup_relative = findViewById(R.id.sugnup_relative);
        btn_submit = findViewById(R.id.btn_submit);
        txt_signin = findViewById(R.id.txt_signin);
        enter_email = findViewById(R.id.enter_email);
        enter_name = findViewById(R.id.enter_name);
        enter_password = findViewById(R.id.enter_password);
        enter_confirm_password = findViewById(R.id.enter_confirm_password);
        enter_mobileno = findViewById(R.id.enter_mobileno);
        progressBar = findViewById(R.id.progressBar);
        ProfileCircleImage = findViewById(R.id.ProfileCircleImage);


        //TODO---CLICK LISTENER

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, OTPActivity.class);
                startActivity(intent);
            }
        });

        sugnup_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        ProfileCircleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = enter_email.getText().toString().trim();

                String password = enter_password.getText().toString().trim();
                String confirmPassword = enter_confirm_password.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (enter_name.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();

                } else if (enter_email.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();

                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(SignupActivity.this, "Please enter valid email address.", Toast.LENGTH_SHORT).show();

                } else if (enter_mobileno.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();

                } else if (enter_password.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();

                } else if (enter_confirm_password.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Confirm password is not correct", Toast.LENGTH_SHORT).show();

                } else {
                    signup(email, password, confirmPassword, enter_name.getText().toString().trim(), enter_mobileno.getText().toString().trim());

                }
            }
        });
    }


    public void showPictureDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(SignupActivity.this);
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(SignupActivity.this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    strimage= String.valueOf(f);
                    ProfileCircleImage.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SignupActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ProfileCircleImage.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            strimage= String.valueOf(f);
            Toast.makeText(SignupActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
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
            MediaScannerConnection.scanFile(SignupActivity.this,
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

    public void signup(String email, String password, String confirmPassword, String trim, String s) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.upload(Api.Signup)
                .addMultipartParameter("name", trim)
                .addMultipartParameter("mobile", s)
                .addMultipartParameter("email", email)
                .addMultipartParameter("password", password)
                .addMultipartParameter("confirm_password", confirmPassword)
                .addMultipartFile("image", f)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("OtpActivity", "onResponse: " + response.toString());
                        try {
                            if (response.getString("result").equals("otp sent successfully")) {

                                SharedHelper.putKey(SignupActivity.this, AppConstant.USERID, response.getString("id"));
                                Toast.makeText(SignupActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, OTPActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "" + response.getString("result"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception exception) {
                            Log.e("dgvdfsgdf", exception.getMessage());
                            progressBar.setVisibility(View.GONE);

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("gbgvbvb", "onError: " + anError);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

}