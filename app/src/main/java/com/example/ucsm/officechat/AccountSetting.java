package com.example.ucsm.officechat;

import android.content.Intent;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import static com.example.ucsm.officechat.R.menu.main;

public class AccountSetting extends AppCompatActivity {
TextView uUser;
TextView uid;
    EditText uname;
    TextView ugender;
    EditText ulivein;
    EditText ugmail;
    EditText uworkat;
    EditText urank;
    EditText uschool;
    ImageView uImage;
    SharedPreference sharedPreference;
    static int USER_ID;
    String userData[];
    static Button btnUpdate;
    static OfficeChatDBHelper officeChatDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        uUser=(TextView) findViewById(R.id.idupdateUser);
        uid=(TextView) findViewById(R.id.idupdateID);
        uname=(EditText)findViewById(R.id.idupdateName);
        ugender=(TextView) findViewById(R.id.idupdateGender);
        ulivein=(EditText) findViewById(R.id.idupdateLivein);
        ugmail=(EditText) findViewById(R.id.idupdategmail);
        uworkat=(EditText) findViewById(R.id.idupdateworkat);
        urank=(EditText) findViewById(R.id.idupdaterank);
        uschool=(EditText) findViewById(R.id.idupdateshool);
        uImage=(ImageView) findViewById(R.id.idupdateImage);



        sharedPreference=new SharedPreference();
        officeChatDBHelper=new OfficeChatDBHelper(getApplicationContext());
        USER_ID=sharedPreference.get_user_id(getApplicationContext());

        btnUpdate=(Button) findViewById(R.id.idupdateBtn);
        Fill_user_data();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeUpdate();
            }
        });





    }

    public void makeUpdate()
    {
        String userUpdate[]=new String[7];
        userUpdate[0]=uname.getText().toString();
        userUpdate[1]=ugmail.getText().toString();
        userUpdate[2]=ulivein.getText().toString();
        userUpdate[3]=uworkat.getText().toString();
        userUpdate[4]=urank.getText().toString();
        userUpdate[5]=ugender.getText().toString();
        userUpdate[6]=uschool.getText().toString();


        officeChatDBHelper.UpdateUserData(userUpdate,USER_ID);
        Toast.makeText(getApplicationContext(),"Successfylly updated!..",Toast.LENGTH_SHORT).show();
        Intent intentToMain=new Intent(AccountSetting.this,MainActivity.class);
        intentToMain.putExtra("ID",USER_ID);
        startActivity(intentToMain);

    }

    public void Fill_user_data()
    {

        userData=officeChatDBHelper.getProfileData(USER_ID);
        uid.setText("ID  :"+USER_ID+"");
        uImage.setImageResource(officeChatDBHelper.getUserImage(USER_ID));
        uUser.setText(userData[0]+"'s info");
        uname.setText(userData[0]);
        ugmail.setText(userData[1]);
        ulivein.setText(userData[2]);
        uworkat.setText(userData[3]);
        urank.setText(userData[4]);
        ugender.setText(userData[5]);
        uschool.setText(userData[6]);

    }
}
