package com.example.ucsm.officechat;

/**
 * Created by ucsm on 10/26/2016.
 */

public class ChatObject {

    private String messages;
    private  Integer userid;
    private  Integer Sender_image;
    private  Integer receiver_image;
    private  String userName;

    public ChatObject(String messages,Integer userid,String userName,Integer sender_image,Integer receiver_image)
    {
        this.messages=messages;
        this.userid=userid;
        this.Sender_image=sender_image;
        this.receiver_image=receiver_image;
        this.userName=userName;
    }

    public String getMessages() {
        return messages;
    }

    public String getUserName() {
        return userName;
    }
    public Integer getUserid() {
        return userid;
    }

    public Integer getSender_image() {
        return Sender_image;
    }
    public Integer getReceiver_image() {
        return receiver_image;
    }

}
