package com.example.ucsm.officechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManageAccount extends AppCompatActivity {

    TextView txtUser_Name;
    EditText eUserName;
    EditText eCurrentPass;
    EditText eNewPass;
    ImageView eUserImage;
    SharedPreference sharedPreference;
    OfficeChatDBHelper officeChatDBHelper;
    Button btnSave;
    int USER_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        txtUser_Name=(TextView) findViewById(R.id.idmUserid);
        eUserName=(EditText) findViewById(R.id.idmUserName);
        eCurrentPass=(EditText)findViewById(R.id.idmCurrentPass);
        eNewPass=(EditText) findViewById(R.id.idmNewPass);
        eUserImage=(ImageView) findViewById(R.id.idmImageView);
        btnSave=(Button) findViewById(R.id.idmbtnSave);

        sharedPreference=new SharedPreference(getApplicationContext());
        USER_ID=sharedPreference.get_user_id(getApplicationContext());

        officeChatDBHelper=new OfficeChatDBHelper(getApplicationContext());

        txtUser_Name.setText(officeChatDBHelper.getUserNameAndGmail(USER_ID)[0]+" ("+USER_ID+" )");
        eUserImage.setImageResource(officeChatDBHelper.getUserImage(USER_ID));



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeChangePassword();

            }
        });
    }

    public void makeChangePassword()
    {
        String User_Name=eUserName.getText().toString();
        String Password=eCurrentPass.getText().toString();
        String NewPass=eNewPass.getText().toString();
        if(HasAccount(User_Name,Password)&&!NewPass.equals(""))
        {

                    officeChatDBHelper.ChangePassword(USER_ID,User_Name,NewPass);

            Toast.makeText(getApplicationContext(),"Successfully Change!",Toast.LENGTH_SHORT).show();
            Intent intentToMain=new Intent(ManageAccount.this,MainActivity.class);
            intentToMain.putExtra("ID",USER_ID);
            startActivity(intentToMain);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid UserName or Password",Toast.LENGTH_SHORT).show();
        }

    }

    public boolean HasAccount(String User_Name,String Password)
    {



        if(USER_ID==officeChatDBHelper.getUserID(User_Name,Password))
            return true;
        else
            return false;
    }
}
