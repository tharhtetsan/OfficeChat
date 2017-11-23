package com.example.ucsm.officechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class One_to_One extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String MESSAGES_CHILD = "";
    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseRecyclerAdapter<PrivateChatMessage, MessageViewHolder> mFirebaseAdapter;
    PrivateChatMessage privateChatMessage;

    SharedPreference sharedPreference;
    RecyclerView recyclerViewPrivateChat;
    EditText txtPrivateChat;
    ImageButton btnSendPrivateChat;

    OfficeChatDBHelper officeChatDBHelper;
    int receiver_id ;
    int sender_id;
    private ProgressBar mProgressBar;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {



        public TextView messageTextView;
        public TextView messageSenderTextView;
        public CircleImageView receiver_ImageView;
        public CircleImageView sender_ImageView;



        public MessageViewHolder(View v) {
            super(v);
            messageTextView = (TextView) itemView.findViewById(R.id.id_message_receiver);
            messageSenderTextView = (TextView) itemView.findViewById(R.id.id_message_sender);
            receiver_ImageView = (CircleImageView) itemView.findViewById(R.id.id_receiver_photo);
            sender_ImageView=(CircleImageView) itemView.findViewById(R.id.id_sender_photo);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to__one);

        txtPrivateChat = (EditText) findViewById(R.id.idtxtMessageToPrivateChat);
        btnSendPrivateChat = (ImageButton) findViewById(R.id.idbtnSerndToPrivatChat);
        sharedPreference = new SharedPreference(getApplicationContext());
        recyclerViewPrivateChat = (RecyclerView) findViewById(R.id.idlvPivateChat);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerViewPrivateChat.setLayoutManager(layoutManager);
        officeChatDBHelper= new OfficeChatDBHelper(getApplicationContext());

        Intent intent = getIntent();
        receiver_id = (int) intent.getSerializableExtra("rid");
        Integer receiver_photo = officeChatDBHelper.getUserImage(receiver_id);
        sender_id= sharedPreference.get_user_id(getApplicationContext());
        Integer sender_photo = officeChatDBHelper.getUserImage(sender_id);


        mProgressBar = (ProgressBar) findViewById(R.id.progressBarPrivate);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        makeDatabaseName(sender_id,receiver_id);

        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setStackFromEnd(true);


        mFirebaseAdapter = new FirebaseRecyclerAdapter<PrivateChatMessage, MessageViewHolder>(
                PrivateChatMessage.class,
                R.layout.private_chat_message,
                MessageViewHolder.class,
                mDatabase.child(MESSAGES_CHILD)) {


            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, PrivateChatMessage friendlyMessage, int position) {



                if(sender_id==friendlyMessage.sernder_id)
                {
                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    viewHolder.messageTextView.setText("");
                    viewHolder.messageSenderTextView.setText(friendlyMessage.message);
                    viewHolder.messageSenderTextView.setBackgroundResource(R.drawable.in_message_bg);
                    viewHolder.messageTextView.setBackgroundResource(0);
                    viewHolder.receiver_ImageView.setImageResource(0);
                    viewHolder.sender_ImageView.setImageResource(officeChatDBHelper.getUserImage(sender_id));




                }
                else
                {
                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    viewHolder.messageTextView.setText(friendlyMessage.message);
                    viewHolder.messageTextView.setBackgroundResource(R.drawable.out_message_bg);
                    viewHolder.messageSenderTextView.setText("");
                    viewHolder.messageSenderTextView.setBackgroundResource(0);
                    viewHolder.receiver_ImageView.setImageResource(officeChatDBHelper.getUserImage(friendlyMessage.sernder_id));
                    viewHolder.sender_ImageView.setImageResource(0);

                }

            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    recyclerViewPrivateChat.scrollToPosition(positionStart);
                }
            }
        });

        recyclerViewPrivateChat.setLayoutManager(mLinearLayoutManager);
        recyclerViewPrivateChat.setAdapter(mFirebaseAdapter);








        btnSendPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSavePrivateChatMessage(txtPrivateChat.getText().toString());

            }
        });

    }


    public void makeDatabaseName(int sender_id,int receiver_id)
    {

        if(sender_id<receiver_id)
            MESSAGES_CHILD=sender_id+"Database"+receiver_id;
        else
            MESSAGES_CHILD=receiver_id+"Database"+sender_id;

        System.out.println("Database"+MESSAGES_CHILD);
    }

    public void makeSavePrivateChatMessage(String privateMessage)
    {


        System.out.println("insert button");
        if(!privateMessage.equals(""))
        {
            privateChatMessage  =new PrivateChatMessage(sender_id,receiver_id,privateMessage);
            mDatabase.child(MESSAGES_CHILD).push().setValue(privateChatMessage);
            Toast.makeText(getApplicationContext(),"Success send",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No data enter",Toast.LENGTH_SHORT).show();
        }

        txtPrivateChat.setText("");
    }


}
