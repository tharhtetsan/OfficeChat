package com.example.ucsm.officechat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;

import static android.R.id.list;

/**
 * Created by ucsm on 10/25/2016.
 */

public class OfficeChatDBHelper extends SQLiteOpenHelper {


    public static String DB_FILEPATH = "/data/data/com.hpa.sqlitesample/databases/OfficeChatProject.db";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "OfficeChatProject";

    private  static final String USER_TABLE="userTable";
    private  static final String PRIVATE_CHAT_TABLE="privateChatTable";
    private  static final String CON_TABLE="connectionTable";
    private  static final  String GROUP_CHAT="groupChatTable";
    private  static final String USER_PROFILE_TABLE="userProfileTable";

    //private chat data fields
    private  static final String PM_ID="pm_id",
            SENDER_ID="sender_id",
            RECEIVER_ID="receiver_id",
            PMESSAGE="message";

    //connectiontable fields
    private  static final String CONID="conid",
            PERSON1="person1",
            PERSON2="person2";


    //USER profile table fields
    private static final String  did = "id",
            dname= "name",
            dgmail = "gmail",
            dlivein = "livein",
            dworkat = "workat",
            drank = "rank",
            dgender = "gender",
            dschool = "school",
            duserImage="userImage";


    //USER_TABLE Fields
    private static final String  ID = "id",
            PASSWORD = "password",
            USERNAME = "username";
    private int User_ID=0;


    //Group chat table fields
    private static final String  MID = "mid",
            USERID = "userid",
            MESSAGE = "message";


    public OfficeChatDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {



        //Create private Chat table and add data
        String privateChat="CREATE TABLE " +PRIVATE_CHAT_TABLE + "("
                +PM_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SENDER_ID + " INTEGER,"
                +RECEIVER_ID+" INTEGER ,"
                +PMESSAGE+" TEXT )";
        db.execSQL(privateChat);

        String q1="insert into "+PRIVATE_CHAT_TABLE+" ("+PM_ID+","+SENDER_ID+","+RECEIVER_ID+","+PMESSAGE+") values(1,1001,1003,'Hi')";
        db.execSQL(q1);

        String q2="insert into "+PRIVATE_CHAT_TABLE+" ("+PM_ID+","+SENDER_ID+","+RECEIVER_ID+","+PMESSAGE+") values(2,1003,1001,'Hey')";
        db.execSQL(q2);


        String q3="insert into "+PRIVATE_CHAT_TABLE+" ("+PM_ID+","+SENDER_ID+","+RECEIVER_ID+","+PMESSAGE+") values(3,1001,1003,'how are you')";
        db.execSQL(q3);

        String q4="insert into "+PRIVATE_CHAT_TABLE+" ("+PM_ID+","+SENDER_ID+","+RECEIVER_ID+","+PMESSAGE+") values(4,1003,1001,'yeach i find')";
        db.execSQL(q4);




        //Create connection table
        String CREATE_CONNECTION_TABLE="CREATE TABLE " +CON_TABLE + "("
                +CONID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PERSON1 + " INTEGER,"
                +PERSON2+" INTEGER )";
        db.execSQL(CREATE_CONNECTION_TABLE);


        String  conquery="insert into "+CON_TABLE+" ("+CONID+","+PERSON1+","+PERSON2+") values (1,1001,1003)";
        db.execSQL(conquery);

        //Create userTable and add 4 person
        String CREATE_USER_TABLE = "CREATE TABLE " +USER_TABLE + "("
                + ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PASSWORD + " TEXT,"
                +USERNAME+" TEXT )";

        db.execSQL(CREATE_USER_TABLE);

        String dquery1="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1001,'tharhtetsan','skyblue')";
        db.execSQL(dquery1);
        String dquery2="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1002,'deedee','deedee')";
        db.execSQL(dquery2);
        String dquery3="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1003,'thaesunadi','thaesunadi')";
        db.execSQL(dquery3);
        String dquery4="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1004,'nyinyi','nyinyihtet')";
        db.execSQL(dquery4);

        String dquery5="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1005,'waiyan','waiyan')";
        db.execSQL(dquery5);
        String dquery6="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1006,'thegin','thegin')";
        db.execSQL(dquery6);
        String dquery7="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1007,'hsulae','hsulae')";
        db.execSQL(dquery7);
        String dquery8="insert into "+USER_TABLE+" ("+ID+","+PASSWORD+","+USERNAME+") values (1008,'waiwai','waiwai')";
        db.execSQL(dquery8);



        //Create groupChat table
        String CREATE_GROUP_CHAT = "CREATE TABLE " + GROUP_CHAT + "("
                + MID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USERID+ " TEXT,"
                +MESSAGE+" TEXT)";

        db.execSQL(CREATE_GROUP_CHAT);
        String quer1="insert into "+GROUP_CHAT+" ("+MID+","+USERID+","+MESSAGE+") values (1,1001,'This is my fist message')";
        db.execSQL(quer1);

        String quer2="insert into "+GROUP_CHAT+" ("+MID+","+USERID+","+MESSAGE+") values (2,1002,'This is my second message')";
        db.execSQL(quer2);


        //Create user profile table
        String CREATE_TABLE ="create table "+USER_PROFILE_TABLE+"("
                +did+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + dname + " TEXT,"
                + dgmail + " TEXT,"
                + dlivein + " TEXT,"
                + dworkat + " TEXT,"
                + drank + " TEXT,"
                + dgender + " TEXT,"
                + dschool + " TEXT,"
                +duserImage+" INTEGER )";


        db.execSQL(CREATE_TABLE);


        String query1="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1001,'Thar Htet San','tharhtet1234@gmail.com','Mandalay','UCSM','Student','Male','University Of Computer Studied Mandalay',"+R.drawable.b1+")";
        db.execSQL(query1);

        String query2="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1002,'Dee Dee','deedee1234@gmail.com','Pyin Oo Lwin','UCSM','Student','Female','University Of Computer Studied Mandalay',"+R.drawable.b5+")";
        db.execSQL(query2);

        String query3="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1003,'Thae Su Nadi','thaesu1234@gmail.com','Nay Payi Taw','UCSM','Student','Female','University Of Computer Studied Mandalay',"+R.drawable.b2+")";
        db.execSQL(query3);

        String query4="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1004,'Nyi Nyi Htet','nyinyi@gmail.com','Yangon','UCSM','Student','Male','University Of Computer Studied Mandalay',"+R.drawable.b4+")";
        db.execSQL(query4);

        String query5="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1005,'Wai Yan Oo','waiyan1234@gmail.com','Yangon(10)mile','UCSM','Student','Male','University Of Computer Studied Mandalay',"+R.drawable.b3+")";
        db.execSQL(query5);

        String query6="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1006,'Thegin Htwe','HtweHtwe1234@gmail.com','Mandalay','UCSM','Student','Female','University Of Computer Studied Mandalay',"+R.drawable.b7+")";
        db.execSQL(query6);

        String query7="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1007,'Hsu Lae Hnin','hsulaehnin234@gmail.com','Yangon (Maw_B)','UCSM','Student','FeMale','University Of Computer Studied Mandalay',"+R.drawable.b8+")";
        db.execSQL(query7);


        String query8="insert into "+USER_PROFILE_TABLE+" ("+did+","+dname+","+dgmail+","+dlivein+","+dworkat+","+drank+","+dgender+","+dschool+","+duserImage+") values (1008,'Wai Wai Mar','waiwai1234@gmail.com','Mawya','UCSM','Teacher','Female','University Of Computer Studied Mandalay',"+R.drawable.b6+")";
        db.execSQL(query8);
        //CREATE conntection table



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GROUP_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + CON_TABLE);;

        onCreate(db);
    }

    public int getUserID(String userName ,String password)
    {


        try{

            String query="SELECT id FROM "+USER_TABLE+" where "+USERNAME+" ='"+userName+"' and "+PASSWORD+"='"+password+"'";
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while (cursor.moveToNext())
                User_ID=cursor.getInt(0);


        }catch (Exception e)
        {
            Log.d("DB", e.getMessage());

        }
        return User_ID;

    }

    public int GET_ID()
    {
        return User_ID;
    }


    public void SaveMessage(int userid,String message)
    {
        try {
            int mid = getMessagesid();
            System.out.println("Message is "+mid+"\t"+userid+"\t"+message);
            String query="insert into "+GROUP_CHAT+" ("+MID+","+USERID+","+MESSAGE+") values ("+mid+","+userid+",'"+message+"')";
            SQLiteDatabase db=this.getReadableDatabase();
            db.execSQL(query);

        }catch (Exception e)
        {

            Log.d("DB Save",e.getMessage());
        }

    }

    public int getMessagesid()
    {
        int M_id=0;

        try{
            String query="select "+MID+" from "+GROUP_CHAT;
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while(cursor.moveToNext())
                M_id=cursor.getInt(0);


        }catch (Exception ex)
        {
            Log.d("get last id ",ex.getMessage());
        }


        return ++M_id;
    }

    public List getUserMessages()
    {

        String  USERNAME = "username";
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        try{
            String query="Select "+duserImage+","+dname+","+MESSAGE+" from "+USER_PROFILE_TABLE+","+GROUP_CHAT+" where "+USER_PROFILE_TABLE+".id="+GROUP_CHAT+".userid";
            System.out.println(query);
            SQLiteDatabase db=getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while(cursor.moveToNext())
            {
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put("image",Integer.toString(cursor.getInt(0)));
                hm.put("user",cursor.getString(1));
                hm.put("message",cursor.getString(2));
                aList.add(hm);
            }

        }catch (Exception e)
        {
            Log.d("Eroor in getting messages for groupCaht",e.getMessage());
        }

        return aList;
    }

    public int getReceiverID(String name)
    {
        int receiverid=0;

        try{
            String query="select "+did+" from "+USER_PROFILE_TABLE+" where "+dname+" = '"+name+"'";
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while(cursor.moveToNext())
            {
                receiverid=cursor.getInt(0);

            }


        }catch (Exception ex)
        {
            Log.d("Error in getting receiver from db ",ex.getMessage());
        }


        return receiverid;
    }



    public String[] getProfileData(int id)
    {


        String userData[]=new String[7];
        try {

            String query = "SELECT * FROM " + USER_PROFILE_TABLE + " where "+did+" = "+id;
            System.out.println(id);
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {

                cursor.getInt(0);
                userData[0]=cursor.getString(1);
                userData[1]=cursor.getString(2) ;
                userData[2]=cursor.getString(3) ;
                userData[3]=cursor.getString(4);
                userData[4]=cursor.getString(5);
                userData[5]=cursor.getString(6);
                userData[6]=cursor.getString(7);

            }


        }catch(Exception e)
        {
            Log.d("DB",e.getMessage());
            e.printStackTrace();
        }
        return userData;
    }


    public String[] getUserNameAndGmail(int id)
    {
        String userData[]=new String[2];
        try {

            String query = "SELECT "+dname +","+dgmail+" FROM " + USER_PROFILE_TABLE + " where "+did+" = "+id;
            System.out.println(id);
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {

                cursor.getInt(0);
                userData[0]=cursor.getString(0);
                userData[1]=cursor.getString(1) ;


            }


        }catch(Exception e)
        {
            Log.d("DB",e.getMessage());
            e.printStackTrace();
        }
        return userData;
    }

    public OfficeChatDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public ArrayList<ChatObject> getPrivateMessages(int senderid,int receiverid)
    {
//        List<HashMap<Integer,String>> aList=new ArrayList<>();
        ArrayList<ChatObject> aList = new ArrayList<>();

        try{

            String query="Select "+SENDER_ID+","+PMESSAGE+" from "+PRIVATE_CHAT_TABLE+" where ("+SENDER_ID+" = "+senderid+" and "+RECEIVER_ID+" = "+receiverid+") or ("+SENDER_ID+" = "+receiverid+" and "+RECEIVER_ID+" = "+senderid+")";

            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);

            System.out.println("\nCursor value"+cursor);
            if (cursor.getCount() != 0) {
                while (cursor.moveToNext()) {

                    ChatObject chatObj = new ChatObject(cursor.getString(1), cursor.getInt(0), getUserNameAndGmail(cursor.getInt(0))[0],getUserImage(senderid),getUserImage(receiverid));
                    aList.add(chatObj);
                }

            }
            System.out.print(aList);
        }catch (Exception e)
        {
            Log.d("Error in get Private Message",e.getMessage());
        }
        System.out.println("\nA list arr"+aList);
        return  aList;
    }


    public List getNames(int id)
    {
        List alist=new ArrayList();
        try{

            String query="select "+dname+" from "+USER_PROFILE_TABLE +" where "+did+" != "+id;
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while(cursor.moveToNext())
            {
                alist.add(cursor.getString(0));
            }
        }catch (Exception e)
        {
            Log.d("Error in getName method",e.getMessage());
        }

        return alist;
    }


    public List getUserPhoto(int id)
    {
        List<Integer> alist=new ArrayList();
        try{

            String query="select "+duserImage+" from "+USER_PROFILE_TABLE+" where "+did+" != "+id;
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while(cursor.moveToNext())
            {
                alist.add(cursor.getInt(0));
                System.out.println(cursor.getInt(0));
            }
        }catch (Exception e)
        {
            Log.d("Error in getName method",e.getMessage());
        }

        return alist;
    }

    public Integer getUserImage(int id)
    {
        Integer userPhoto=0;
        try{

            String query="select "+duserImage+" from "+USER_PROFILE_TABLE+" where "+did+" == "+id;
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(query,null);
            while(cursor.moveToNext())
            {
                userPhoto=(cursor.getInt(0));
                System.out.println(cursor.getInt(0));
            }
        }catch (Exception e)
        {
            Log.d("Error in getuser Image method",e.getMessage());
        }

        return userPhoto;
    }


    public void SavePrivateChat(int senderid,int receiverid,String message)
    {
        try{

            String query="insert into "+PRIVATE_CHAT_TABLE+" ( "+SENDER_ID+","+""+RECEIVER_ID+","+PMESSAGE+") values ("+senderid+","+receiverid+",'"+message+"')";
            SQLiteDatabase db=this.getReadableDatabase();
            db.execSQL(query);
        }catch (Exception e)
        {
            Log.d("Error in getuser Image method",e.getMessage());
        }



    }


    public void UpdateUserData(String userData[],int id)
    {
        try{

            String query="update "+USER_PROFILE_TABLE+" SET "+dname+" = '"+userData[0]+"', "+dgmail+" = '"+userData[1]+"', "+dlivein+" = '"+userData[2]+"', "+dworkat+" = '"+userData[3]+"',"+drank+" = '"+userData[4]+"',"+
                    dgender+" = '"+userData[5]+"',"+dschool+" = '"+userData[6]+"' where "+did+" = "+id;
            System.out.println("\n"+query);
            SQLiteDatabase db=this.getReadableDatabase();
            db.execSQL(query);

        }catch (Exception e)
        {
            Log.d("Error in update user data ",e.getMessage());
        }


    }

    public void ChangePassword(int id ,String userName,String pass)
    {
        try{

            String query="update "+USER_TABLE+" SET "+USERNAME+" = '"+userName+"', "+PASSWORD+" = '"+pass+"' where "+ID+" = "+id;
            System.out.println("\n"+query);
            SQLiteDatabase db=this.getReadableDatabase();
            db.execSQL(query);
            String query1="select * from "+USER_TABLE+" where "+ID+" = "+id;
            Cursor cursor=db.rawQuery(query1,null);
            String str="";
            while(cursor.moveToNext())
                str=cursor.getInt(0)+"\t"+cursor.getString(1)+"\t"+cursor.getString(2);

            System.out.println("\n"+str);
        }catch (Exception e)
        {
            Log.d("Error in update user data ",e.getMessage());
        }

    }

}
