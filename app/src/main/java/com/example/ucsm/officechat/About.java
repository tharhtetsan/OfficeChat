package com.example.ucsm.officechat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class About extends AppCompatActivity {

    TextView txt1;
    TextView txt2;
    TextView LFacebook;
    TextView LGmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    txt1=(TextView) findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);

        LFacebook=(TextView) findViewById(R.id.Lfacebook);
        LGmail=(TextView) findViewById(R.id.LGmail);

        String t1="-Thank to all my Teacher, Leactures, friends,my mon.\n-And also Thank to The New Westminster Institute";
        String t2="-This is my first Application for Android \n-So some errors can be contain .....";
        txt1.setText(t1);
        txt2.setText(t2);

        LFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri. parse("https://www.facebook.com/tharhtet.san.902" );
                Intent intent = new Intent(Intent. ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        LGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri. parse("https://plus.google.com/u/0/106697504285730631251" );
                Intent intent = new Intent(Intent. ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
