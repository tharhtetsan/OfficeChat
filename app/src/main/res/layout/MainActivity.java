package com.example.ucsm.officechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.ucsm.officechat.R.menu.main;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public  int USER_ID;
    private TextView UserNameP;
    private  TextView UserGmainP;
    private  ImageView UserImageP ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Own
        Intent intentObj=getIntent();
        USER_ID=(int)intentObj.getSerializableExtra("ID");

        GroupChat GroupChatfargment = new GroupChat();
        GroupChatfargment.setUSER_ID(USER_ID);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,GroupChatfargment).commit();

        UserNameP=(TextView)navigationView.getHeaderView(0).findViewById(R.id.idUserNameP);
        UserGmainP=(TextView) navigationView.getHeaderView(0).findViewById(R.id.idUsergamilP);
        UserImageP=(ImageView) navigationView.getHeaderView(0).findViewById(R.id.idUserImageP);

        fillUserName();

    }

    public int getUSER_ID()
    {
        return  USER_ID;
    }

    public void fillUserName()
    {
        OfficeChatDBHelper db=new OfficeChatDBHelper(getApplicationContext());
       String userData[]=db.getUserNameAndGmail(USER_ID);
        UserNameP.setText(userData[0]);
        UserGmainP.setText(userData[1]);
        UserImageP.setImageResource(db.getUserImage(USER_ID));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intentToMange=new Intent(MainActivity.this,ManageAccount.class);
            startActivity(intentToMange);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           Intent intentToProfile=new Intent(MainActivity.this,UserProfile.class);
            intentToProfile.putExtra("ID",USER_ID);
            startActivity(intentToProfile);
        } else if(id==R.id.idGroupChat)
        {
            GroupChat GroupChatfargment = new GroupChat();
            GroupChatfargment.setUSER_ID(USER_ID);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container,GroupChatfargment).commit();

        }else if (id == R.id.idPrivateChat) {
            PrivateChat PrivateChatfargment = new PrivateChat();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container,PrivateChatfargment).commit();
        } else if (id == R.id.nav_manage) {

            Intent intentToUpdate=new Intent(MainActivity.this,AccountSetting.class);
            startActivity(intentToUpdate);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
