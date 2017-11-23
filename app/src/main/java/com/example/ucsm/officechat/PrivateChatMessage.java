package com.example.ucsm.officechat;

/**
 * Created by ucsm on 10/28/2016.
 */

public class PrivateChatMessage {


    public int sernder_id;
    public int receiver_id;
    public String message;


    public PrivateChatMessage()
    {

    }
    public PrivateChatMessage(int sernder_id,int  receiver_id,String message)
    {
        this.sernder_id=sernder_id;
        this.receiver_id=receiver_id;
        this.message=message;
    }
}
