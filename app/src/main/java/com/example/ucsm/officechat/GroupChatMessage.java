package com.example.ucsm.officechat;

/**
 * Created by ucsm on 10/28/2016.
 */

public class GroupChatMessage {


    public  String gmessage;
    public int userId;

    public GroupChatMessage()
    {

    }

    public GroupChatMessage(int userId,String gmessage)
    {
        this.userId=userId;
        this.gmessage=gmessage;
    }

}
