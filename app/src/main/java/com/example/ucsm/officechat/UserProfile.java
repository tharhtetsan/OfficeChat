package com.example.ucsm.officechat;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    private TextView uName;
    private  TextView uGamil;
    private TextView uLivein;
    private TextView uWorkat;
    private TextView uRank;
    private TextView uGender;
    private TextView uSchool;
    private static int USER_ID=0;
    private ImageView uUserPhoto;
    String []userData;
    OfficeChatDBHelper db;
    String []NameAndGmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        uName=(TextView) findViewById(R.id.idName);
        uGamil=(TextView) findViewById(R.id.idUsergmail);
        uLivein=(TextView) findViewById(R.id.idUserLivein);
        uWorkat=(TextView)findViewById(R.id.idUserWorkAt);
        uRank=(TextView) findViewById(R.id.idUserRank);
        uGender=(TextView) findViewById(R.id.idUserGender);
        uSchool=(TextView) findViewById(R.id.idUserSchool);
        uUserPhoto=(ImageView)findViewById(R.id.idpuserImage);

        db = new OfficeChatDBHelper(getApplicationContext());
        Intent obj = getIntent();
        USER_ID = (int) obj.getSerializableExtra("ID");
       FillUserInformation();
    }



    public  void FillUserInformation() {



        userData = db.getProfileData(USER_ID);

        uUserPhoto.setImageResource(db.getUserImage(USER_ID));
        uName.setText(userData[0]);
        uGamil.setText(userData[1]);
        uLivein.setText(userData[2]);
        uWorkat.setText(userData[3]);
        uRank.setText(userData[4]);
        uGender.setText(userData[5]);
        uSchool.setText(userData[6]);
    }

    public String getUserName(Context context)
    {
        OfficeChatDBHelper dbHelper=new OfficeChatDBHelper(context);
        NameAndGmail=dbHelper.getUserNameAndGmail(USER_ID);
        return NameAndGmail[0];
    }
    public String getGmail()
    {
        return  NameAndGmail[1];
    }




}
