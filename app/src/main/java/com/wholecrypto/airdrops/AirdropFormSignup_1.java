package com.wholecrypto.airdrops;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AirdropFormSignup_1 extends Fragment {


    public AirdropFormSignup_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.fragment_airdrop_form_signup_1, container, false);

        Button signupBtnNext = layout.findViewById(R.id.signupBtnNext);

        signupBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft;
                Fragment secondSignup = new AirdropFormSignup_2();
                ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.signup_container, secondSignup);
                ft.commit();
            }
        });




        return layout;
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            //Airdrop Starting Date
            EditText airdropStartingDateEditText = view.findViewById(R.id.startDate);
            setDate startingDate = new setDate(airdropStartingDateEditText,view.getContext());

            //Airdrop Ending Date
            EditText airdropEndingDateEditText = view.findViewById(R.id.endDate);
            setDate endingDate = new setDate(airdropEndingDateEditText, view.getContext());
        }

    }

}
