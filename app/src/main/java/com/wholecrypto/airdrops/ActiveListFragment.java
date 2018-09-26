package com.wholecrypto.airdrops;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActiveListFragment extends Fragment {
    private static final String TAG = "ActiveListFragment";
    static DatabaseReference airdropsRef;
    private AirdropsAdapter adapter;
    private ChildEventListener approvedAirdropsListener;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"Fragment onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active_list, container, false);

        // loading animation
        final ProgressBar progressBar = view.findViewById(R.id.spin_kit);
        WanderingCubes wanderingCubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(wanderingCubes);

        RecyclerView recyclerView = view.findViewById(R.id.airdrops_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Stop animation when a view is loaded
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                progressBar.setVisibility(view.INVISIBLE);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });

        adapter = new AirdropsAdapter();
        recyclerView.setAdapter(adapter);
        airdropsRef = FirebaseDatabase.getInstance().getReference().child("airdrops");


        return view;
    }


    public void onResume(){
        Log.e(TAG, "Fragment: onResume");
        super.onResume();
        attachDatabaseReadListener();
    }


    public void onPause(){
        Log.e(TAG, "Fragment: onPause");
        super.onPause();
        detachDatabaseReadListener();
    }


    private void attachDatabaseReadListener() {
        if (approvedAirdropsListener == null) {
            Log.e(TAG, "Fragment: Attaching approvedAirdropsListener");
            approvedAirdropsListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
          /*add data in adapter one by one if isPassedInspection true*/
                    String itemKey = dataSnapshot.getKey();
                    Airdrop airdrop = dataSnapshot.getValue(Airdrop.class);
                    Log.d(TAG,"ActiveListFragment: onChildAdded");
                    if (airdrop.isApproved()) {
                        Log.d(TAG,"ActiveListFragment: onChildAdded : airdrop.isAprroved = true");
                        adapter.addAirdropItem(itemKey, airdrop);
                    }
                    else if(!airdrop.isApproved()){
                        Log.d(TAG,"Airdrop is not approved");
                    }
                    else{
                        Log.d(TAG,"Airdrop is null");

                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
              /*when something change in data then add in adapter if isPassedInspection true*/
                    String itemKey = dataSnapshot.getKey();
                    Airdrop airdrop = dataSnapshot.getValue(Airdrop.class);
                    if (airdrop.isApproved()) {
                        adapter.addAirdropItem(itemKey, airdrop);
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            airdropsRef.addChildEventListener(approvedAirdropsListener);
        }
    }

    private void detachDatabaseReadListener(){
        Log.e(TAG, "Fragment: Detaching approvedAirdropsListener and setting it to null");

        if(approvedAirdropsListener != null) {
            airdropsRef.removeEventListener(approvedAirdropsListener);
            approvedAirdropsListener = null;
        }
    }



    public void onDestroy(){
        super.onDestroy();
        Log.e(TAG,"Fragment onDestroy");
    }

}


