package com.example.ucsm.officechat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrivateChat.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrivateChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivateChat extends Fragment {


    ListView lvPrivateUserNames;
    List user_Names;
    List user_Images;
    OfficeChatDBHelper db;
    static int User_id;
    SharedPreference sharedPreference;
    int userNum[]={1002,1003,1004,1005,1006,1007,1008};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PrivateChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrivateChat.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivateChat newInstance(String param1, String param2) {
        PrivateChat fragment = new PrivateChat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_private_chat, container, false);

        db=new OfficeChatDBHelper(rootView.getContext());
        sharedPreference=new SharedPreference(rootView.getContext());
        User_id=sharedPreference.get_user_id(rootView.getContext());
        user_Names=db.getNames(User_id);
        user_Images=db.getUserPhoto(User_id);
        lvPrivateUserNames=(ListView)  rootView.findViewById(R.id.idlvPrivateChat);



        List<HashMap<String,String>> aList=new ArrayList<HashMap<String,String>>();
        for(int i=0;i<user_Names.size();i++)
        {



           HashMap<String,String> hm=new HashMap<String,String>();
            hm.put("user",user_Names.get(i)+"");

            hm.put("userImage",Integer.toString((int) user_Images.get(i)));
            aList.add(hm);
       }
        //key user int hasMap
        String []from={"user","userImage"};
       int[] to = {R.id.user, R.id.userImage};
       SimpleAdapter adapter = new SimpleAdapter(rootView.getContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file


         //Setting the adapter to the listView
       lvPrivateUserNames.setAdapter(adapter);



        System.out.println("user names="+user_Names);
        System.out.println("user image="+user_Images);
        lvPrivateUserNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                try {

                    int RECEIVER_id=db.getReceiverID(user_Names.get(i)+"");
                    System.out.println("\nReceiver id ="+RECEIVER_id);
                    Intent intent=new Intent(getActivity(),One_to_One.class);
                    intent.putExtra("rid",RECEIVER_id);
                    startActivity(intent);

                }catch (Exception e)
                {
                    Log.d("Error in show groupchat message",e.getMessage());
                }

            }

        });



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
