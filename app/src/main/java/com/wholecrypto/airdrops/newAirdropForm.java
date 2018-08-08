package com.wholecrypto.airdrops;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

//FirebaseDatabase aFirebaseDatabase = FirebaseDatabase.getInstance();
//aAirdropsDatabaseReference= aFirebaseDatabase.getReference().child("airdrops");

public class newAirdropForm extends AppCompatActivity {
    private static final String TAG = "newAirdropForm";
    // Firebase instance variables
    private DatabaseReference aAirdropsDatabaseReference;
    String username = "changeME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_airdrop_form);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft;

        if (savedInstanceState == null){
            Fragment firstSignup = new AirdropFormSignup_1();
            ft = fragmentManager.beginTransaction();
            ft.add(R.id.signup_container, firstSignup);
            ft.commit();
        }

            }



    //get data from the user form, create a new airdrop object.
    //it also launch an in-app purchase flow. if purchase succeed, push airdrop to db for manual inspection.

    /*
    private void createAirdrop(View currentActivity){

        //References
        EditText coinNameRef = findViewById(R.id.coin_name);
        EditText coinSymbolRef = findViewById(R.id.coin_symbol);
        EditText startDateRef = findViewById(R.id.airdrop_start_date);
        EditText endDateRef = findViewById(R.id.airdrop_end_date);
        EditText airdropDescriptionRef = findViewById(R.id.airdrop_description);


        //Initialize the forms
        String coinName = coinNameRef.getText().toString();
        String coinSymbol = coinSymbolRef.getText().toString();
        String airdropStartingDate = startDateRef.getText().toString();
        String airdropEndingDate = endDateRef.getText().toString();
        String airdropDescription = airdropDescriptionRef.getText().toString();


        if(checkFormLogicIsValid()){
            Airdrop newAirdrop = new Airdrop(coinName,coinSymbol,airdropStartingDate,airdropEndingDate,airdropDescription,false,username);
            aAirdropsDatabaseReference.push().setValue(newAirdrop);
            Snackbar.make(currentActivity, "Airdrop sent", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            cleanForm();
        }

    }

    private void cleanForm(){
        EditText coinNameRef = findViewById(R.id.coin_name);
        EditText coinSymbolRef = findViewById(R.id.coin_symbol);
        EditText startDateRef = findViewById(R.id.airdrop_start_date);
        EditText endDateRef = findViewById(R.id.airdrop_end_date);
        EditText airdropDescriptionRef = findViewById(R.id.airdrop_description);

        coinNameRef.setText("");
        coinSymbolRef.setText("");
        startDateRef.setText("");
        endDateRef.setText("");
        airdropDescriptionRef.setText("");

    }

    private boolean checkFormLogicIsValid() {

        EditText coinNameRef = findViewById(R.id.coin_name);
        EditText coinSymbolRef = findViewById(R.id.coin_symbol);
        EditText startDateRef = findViewById(R.id.airdrop_start_date);
        EditText endDateRef = findViewById(R.id.airdrop_end_date);
        EditText airdropDescriptionRef = findViewById(R.id.airdrop_description);

        String coinName = coinNameRef.getText().toString();
        String coinSymbol = coinSymbolRef.getText().toString();
        String airdropStartingDate = startDateRef.getText().toString();
        String airdropEndingDate = endDateRef.getText().toString();
        String airdropDescription = airdropDescriptionRef.getText().toString();


        if (coinName.isEmpty()) {
            Toast toast = (Toast.makeText(this, "Coin Name is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        if (coinSymbol.isEmpty()) {
            Toast toast = (Toast.makeText(this, "Coin Symbol is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }


        if(airdropStartingDate.isEmpty()){
            Toast toast = (Toast.makeText(this, "Starting Date is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        //check if description is more than 300 chars or is empty
        if(airdropDescription.length() > 300 || airdropDescription.isEmpty()){
            Toast toast = (Toast.makeText(this, "Description is empty or exceed 300 characters", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        return true;
    }


*/






}
