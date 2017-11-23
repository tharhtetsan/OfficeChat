package com.example.ucsm.officechat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupChat.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupChat extends Fragment {
    private ProgressBar mProgressBar;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView messengerTextView;
        public CircleImageView messengerImageView;

        public MessageViewHolder(View v) {
            super(v);
            messageTextView = (TextView) itemView.findViewById(R.id.idmessageM);
            messengerTextView = (TextView) itemView.findViewById(R.id.idUserNameM);
            messengerImageView = (CircleImageView) itemView.findViewById(R.id.idGuserImage);
        }
    }

//    ListView lvGroupChat;
    RecyclerView lvGroupChat;
    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseRecyclerAdapter<GroupChatMessage, MessageViewHolder> mFirebaseAdapter;
    private String MESSAGES_CHILD = "groupChatMessage";


    SimpleAdapter adapter;
    EditText txtMessages;
    ImageButton btnSend;
     OfficeChatDBHelper db;
    int USER_ID;
    SharedPreference sharedPreference;
    private DatabaseReference mDatabase;


     List<HashMap<String,String>> aList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GroupChat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupChat.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupChat newInstance(String param1, String param2) {
        GroupChat fragment = new GroupChat();
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


        final View rootView = inflater.inflate(R.layout.fragment_group_chat, container, false);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        sharedPreference =new SharedPreference(inflater.getContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();

        USER_ID=sharedPreference.get_user_id(inflater.getContext());

        lvGroupChat=(RecyclerView)  rootView.findViewById(R.id.idlvGroupChat);
        db=new OfficeChatDBHelper(inflater.getContext());


        mLinearLayoutManager = new LinearLayoutManager(inflater.getContext());
        mLinearLayoutManager.setStackFromEnd(true);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<GroupChatMessage, MessageViewHolder>(
                GroupChatMessage.class,
                R.layout.groupchatmessage,
                MessageViewHolder.class,
                mDatabase.child(MESSAGES_CHILD)) {


            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, GroupChatMessage friendlyMessage, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.messageTextView.setText(friendlyMessage.gmessage);
                viewHolder.messengerTextView.setText(db.getUserNameAndGmail(friendlyMessage.userId)[0]);
                viewHolder.messengerImageView.setImageResource(db.getUserImage(friendlyMessage.userId));

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
                    lvGroupChat.scrollToPosition(positionStart);
                }
            }
        });

        lvGroupChat.setLayoutManager(mLinearLayoutManager);
        lvGroupChat.setAdapter(mFirebaseAdapter);







        btnSend=(ImageButton) rootView.findViewById(R.id.idbtnSerndToGroupChat);
        txtMessages=(EditText) rootView.findViewById(R.id.idtxtMessageToGroupChat);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txtMessages.getText().toString().equals(""))
                {
                    GroupChatMessage groupChatMessage=new GroupChatMessage(USER_ID,txtMessages.getText().toString());
                    mDatabase.child(MESSAGES_CHILD).push().setValue(groupChatMessage);
                    Toast.makeText(rootView.getContext(),"Success send",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(rootView.getContext(),"No data enter",Toast.LENGTH_SHORT).show();
                }



                txtMessages.setText("");
            }
        });


                return rootView;
    }


    public void setUSER_ID(int id)
    {
        USER_ID=id;
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
