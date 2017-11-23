package com.example.ucsm.officechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;

import java.util.ArrayList;
import java.util.List;

public class PrivateChatOnetoOne extends AppCompatActivity {

    SharedPreference sharedPreference;
    RecyclerView recyclerViewPrivateChat;
    EditText txtPrivateChat;
    ImageButton btnSendPrivateChat;
    OfficeChatDBHelper officeChatDBHelper;
    int receiver_id ;
    int sender_id;
    ArrayList<ChatObject> objectChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat_oneto_one);

        sharedPreference = new SharedPreference(getApplicationContext());

        recyclerViewPrivateChat = (RecyclerView) findViewById(R.id.privateChat);
        recyclerViewPrivateChat.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPrivateChat.setLayoutManager(layoutManager);

        officeChatDBHelper= new OfficeChatDBHelper(getApplicationContext());

        Intent intent = getIntent();
         receiver_id = (int) intent.getSerializableExtra("rid");
        Integer receiver_photo = officeChatDBHelper.getUserImage(receiver_id);
        sender_id= sharedPreference.get_user_id(getApplicationContext());
        Integer sender_photo = officeChatDBHelper.getUserImage(sender_id);

        System.out.println("receiver photo" + receiver_id + "\t" + sender_photo);



        if (officeChatDBHelper.getPrivateMessages(sender_id, receiver_id).size() == 0) {
            System.out.println("no messages");
        } else {

            System.out.println("has message");
            objectChat = officeChatDBHelper.getPrivateMessages(sender_id, receiver_id);
            PrivateChatAdapter adapter = new PrivateChatAdapter(objectChat, sender_id);
            recyclerViewPrivateChat.setAdapter(adapter);
        }


        txtPrivateChat = (EditText) findViewById(R.id.idMessagePrivateChat);
        btnSendPrivateChat = (ImageButton) findViewById(R.id.btnSendPrvateChat);

        btnSendPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSavePrivateChatMessage();
                System.out.println("aa");
            }
        });

    }
        public void makeSavePrivateChatMessage()
        {
            String privateMessage=txtPrivateChat.getText().toString();

            officeChatDBHelper.SavePrivateChat(sender_id,receiver_id,privateMessage);

            System.out.print(privateMessage);
            objectChat = officeChatDBHelper.getPrivateMessages(sender_id, receiver_id);
            PrivateChatAdapter adapter = new PrivateChatAdapter(objectChat, sender_id);
            recyclerViewPrivateChat.setAdapter(adapter);
            txtPrivateChat.setText("");
        }


}
