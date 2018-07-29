package com.milind.test;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    GoogleSignInAccount acct;
    @BindView(R.id.civ_profileImage)
    CircleImageView civProfileImage;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;

    String TAG="ProfileTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        init();

    }

    void init() {
        acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();

            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            if(personName!=null){
                tvUserName.setText(personName);
            }else if (personGivenName!=null){
                tvUserName.setText(personGivenName);

            }

            if(personEmail!=null){

                tvUserEmail.setText(personEmail);
            }

            if(personPhoto!=null){

                Picasso.get().load(personPhoto).into(civProfileImage);
            }else {
                Log.i(TAG,"picasso default image");
                Picasso.get().load(R.drawable.profilepng).into(civProfileImage);

            }
        }
    }

}
