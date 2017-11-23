package com.example.ucsm.officechat;

/**
 * Created by ucsm on 10/26/2016.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PrivateChatAdapter extends RecyclerView.Adapter<PrivateChatAdapter.ViewHolder> {
    ArrayList<ChatObject> messageObject;
    private Integer senderId;
    int id = 0;
    int img;
    public PrivateChatAdapter(ArrayList<ChatObject> messageObject,Integer senderId) {
        this.messageObject = messageObject;
        this.senderId=senderId;
        Log.d("AdapterData",messageObject.get(0).getUserid()+" "+messageObject.get(0).getUserid()
                +" "+messageObject.get(0).getUserid());
        Log.d("getItemCount",messageObject.size()+"");
    }

    @Override
    public PrivateChatAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        Log.d("SenderData",messageObject.get(id).getUserid()+" "+id+" "+senderId);
        if(messageObject.get(id).getUserid().equals(senderId)) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.private_ont_to_one_layout1, viewGroup, false);

            Log.d("side ","left"+messageObject.get(id).getUserid());
            img = messageObject.get(id).getSender_image();
        }
        else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.private_one_to_one_layout, viewGroup, false);

            Log.d("side ","right"+messageObject.get(id).getUserid());
            img = messageObject.get(id).getReceiver_image();
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrivateChatAdapter.ViewHolder viewHolder, int i) {
        Log.d("onBind","dtta"+i);
        viewHolder.userMessage.setText(messageObject.get(i).getMessages());
        viewHolder.UserName.setText(messageObject.get(i).getUserName());
        viewHolder.userPhtot.setImageResource(img);
id++;
    }

    @Override
    public int getItemCount() {

        return messageObject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userMessage,UserName;
        ImageView userPhtot;
        public ViewHolder(View view) {
            super(view);
            if(messageObject.get(id).getUserid().equals(senderId))
            {
                userMessage = (TextView) view.findViewById(R.id.senderMsg);
                UserName = (TextView)view.findViewById(R.id.idPrivateChatUserName);
                userPhtot = (ImageView)view.findViewById(R.id.sendImg);

            }
            else
            {
                userMessage = (TextView) view.findViewById(R.id.idPrivateChatMessageOther);
                UserName = (TextView)view.findViewById(R.id.idOtherName);
                userPhtot = (ImageView)view.findViewById(R.id.idotherUserPhoto);

            }

        }
    }

}