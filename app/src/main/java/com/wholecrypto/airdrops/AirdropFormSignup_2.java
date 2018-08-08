package com.wholecrypto.airdrops;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AirdropFormSignup_2 extends Fragment {


    public AirdropFormSignup_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_airdrop_form_signup_2, container, false);

        ImageView goBack = layout.findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft;
                Fragment firstSignup = new AirdropFormSignup_1();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.signup_container, firstSignup);
                ft.commit();
            }
        });

                return layout;
    }

}
