package com.example.missionk3.Activities;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.missionk3.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class WelcomeActivity extends AppCompatActivity {
    Button btn_signin, btn_signup;
    ImageButton login_facebook, login_google;
    LoginButton login_button;
    SignInButton google_login;
    private CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btn_signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById(R.id.btn_signup);
        login_button = findViewById(R.id.login_button);
        login_facebook = findViewById(R.id.login_facebook);
        login_google = findViewById(R.id.login_google);
        google_login = findViewById(R.id.google_login);


        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                login_button.performClick();
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


        /////////////////////////////////////////////////////////////////////////////////
        //                             //Facebook Code//                               //
        /////////////////////////////////////////////////////////////////////////////////

        callbackManager = CallbackManager.Factory.create();
        login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                  LoginManager.getInstance().logOut();

                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {

                                System.out.println("onSuccess");

                                String accessToken = loginResult.getAccessToken()
                                        .getToken();
                                Log.i("accessToken", accessToken);

                                GraphRequest request = GraphRequest.newMeRequest(
                                        loginResult.getAccessToken(),
                                        new GraphRequest.GraphJSONObjectCallback() {
                                            @Override
                                            public void onCompleted(JSONObject object,
                                                                    GraphResponse response) {
                                                String name;
                                                Log.i("WelcomeActivity",
                                                        response.toString());
                                                try {
                                                    String id = object.getString("id");
                                                    try {
                                                        URL profile_pic = new URL(
                                                                "http://graph.facebook.com/" + id + "/picture?type=large");
                                                        Log.i("profile_pic",
                                                                profile_pic + "");

                                                    } catch (MalformedURLException e) {
                                                        e.printStackTrace();
                                                    }
                                                    name = object.getString("name");
                                                    String email;
                                                    if (object.has("email")) {
                                                        email = object.getString("email");
                                                    }
                                                    String gender;
                                                    if (object.has("gender")) {
                                                        gender = object.getString("gender");
                                                    }
                                                    String birthday;
                                                    if (object.has("birthday")) {
                                                        birthday = object.getString("birthday");
                                                    }

//                                                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                                                    intent.putExtra("name", name);
//                                                    startActivity(intent);

                                                    Toast.makeText(WelcomeActivity.this, name, Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                Bundle parameters = new Bundle();
                                parameters.putString("fields",
                                        "id,name,email,gender,birthday");
                                request.setParameters(parameters);
                                request.executeAsync();
                            }

                            @Override
                            public void onCancel() {
                                System.out.println("onCancel");
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                System.out.println("onError");
                                Log.v("WelcomeActivity", exception.getCause().toString());
                            }
                        });
            }
        });



        /////////////////////////////////////////////////////////////
        //                     Google Code                        //
        ///////////////////////////////////////////////////////////


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        google_login = findViewById(R.id.google_login);
        //  googleBtn.setSize(SignInButton.SIZE_STANDARD);
        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        //Google result code method
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//
//
//    }

    //Google handler method
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//
//
//            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
//
//            //GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
//            if (acct != null) {
//
//                if (acct.getDisplayName() != null) {
//                    personName = acct.getDisplayName();
//                }
//
//                if (acct.getGivenName() != null) {
//                    personGivenName = acct.getGivenName();
//                }
//                if (acct.getFamilyName() != null) {
//                    personFamilyName = acct.getFamilyName();
//                }
//
//                if (acct.getFamilyName() != null) {
//                    personFamilyName = acct.getFamilyName();
//                }
//
//                if (acct.getEmail() != null) {
//                    personEmail = acct.getEmail();
//                }
//
//
//                if (acct.getId() != null) {
//                    personId = acct.getId();
//                }
//                if (acct.getPhotoUrl() != null) {
//                    personPhoto = acct.getPhotoUrl();
//                }
//
//
//                Toast.makeText(WelcomeActivity.this, "User email : " + personEmail, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                intent.putExtra("name", personName);
//                startActivity(intent);
//            } else {
//                mGoogleSignInClient.signOut();
//
//            }
//
//
//        } catch (ApiException e) {
//            Log.d("Message", e.toString());
//        }
    }
















































































