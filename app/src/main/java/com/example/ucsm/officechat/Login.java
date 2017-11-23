package com.example.ucsm.officechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText USERNAME;
    EditText PASSWORD;
    Button btnLogIn;
    OfficeChatDBHelper getUser_ID;
    private  int USER_ID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        USERNAME=(EditText) findViewById(R.id.txtUserName);
        PASSWORD=(EditText) findViewById(R.id.txtPassword);
        btnLogIn=(Button) findViewById(R.id.btnLogin);
        getUser_ID=new OfficeChatDBHelper(getApplicationContext());

        USERNAME.setText("");
        PASSWORD.setText("");
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLogin();
            }
        });
    }

    public void makeLogin()
    {
        String User_Name=USERNAME.getText().toString();
        String Password=PASSWORD.getText().toString();
        if(HasAccount(User_Name,Password))
        {
            SharedPreference sharedPreference = new SharedPreference(getApplicationContext());
            sharedPreference.createLoginSession(USER_ID);
            Intent intentToMain=new Intent(Login.this,MainActivity.class);
            Toast.makeText(getApplicationContext(),"Success Login!\n Welcome",Toast.LENGTH_SHORT).show();
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

         USER_ID=getUser_ID.getUserID(User_Name,Password);

        if(USER_ID==0)
            return false;
        else
           return true;
    }
}
