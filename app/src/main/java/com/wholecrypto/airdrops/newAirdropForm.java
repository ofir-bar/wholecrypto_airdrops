package com.wholecrypto.airdrops;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class newAirdropForm extends AppCompatActivity {
    private static final String TAG = "newAirdropForm";


    //Widgets
    EditText contactEmailRef;
    EditText projectNameRef;

    EditText allLinksRef;

    EditText startDateRef;
    setDate startDateDialog;
    EditText endDateRef;
    setDate endDateDialog;

    EditText tokenSymbolRef;

    EditText restrictionsRef;
    EditText aboutRef;
    EditText claimInstructionsRef;
    EditText estimatedValueRef;



    //Variables
    String contactEmail;

    String projectName;

    String allLinks;

    String startDate;
    String endDate;

    String tokenSymbol;

    String restrictions;
    String about;
    String claimInstructions;
    String estimatedValue;



    // Firebase instance variables
    private FirebaseDatabase aFirebaseDatabase;
    private DatabaseReference aAirdropsDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_airdrop_form);

        aFirebaseDatabase = FirebaseDatabase.getInstance();
        aAirdropsDatabaseReference= aFirebaseDatabase.getReference().child("airdrops");

        startDateRef = findViewById(R.id.startDate);
        endDateRef = findViewById(R.id.endDate);
        startDateDialog = new setDate(startDateRef,this);
        endDateDialog = new setDate(endDateRef,this);


        TextView termsAndConditions = findViewById(R.id.terms_and_conditions);
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listItemIntent = new Intent(getBaseContext(),TermsOfServiceActivity.class);
                startActivity(listItemIntent);
            }
        });


        Button submitAirdrop = findViewById(R.id.sendAirdrop);
        submitAirdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAirdrop();
            }
        });



    }



    //get data from the user form, create a new airdrop object.
    //it also launch an in-app purchase flow. if purchase succeed, push airdrop to db for manual inspection.

    private void createAirdrop(){

        //Widgets
        contactEmailRef = findViewById(R.id.contact_email_box);
        projectNameRef = findViewById(R.id.icoProjectName);
        allLinksRef = findViewById(R.id.allLinksURL);
        startDateRef = findViewById(R.id.startDate);
        endDateRef = findViewById(R.id.endDate);
        tokenSymbolRef = findViewById(R.id.tokenSymbol);
        restrictionsRef = findViewById(R.id.restrictions);
        aboutRef = findViewById(R.id.about);
        claimInstructionsRef = findViewById(R.id.claim_instructions);
        estimatedValueRef= findViewById(R.id.estimated_value);


        //Initialize the forms
        contactEmail = contactEmailRef.getText().toString();
        projectName = projectNameRef.getText().toString();
        allLinks = allLinksRef.getText().toString();
        startDate = startDateRef.getText().toString();
        endDate = endDateRef.getText().toString();
        tokenSymbol = tokenSymbolRef.getText().toString();
        restrictions = restrictionsRef.getText().toString();
        about = aboutRef.getText().toString();
        claimInstructions = claimInstructionsRef.getText().toString();
        estimatedValue = estimatedValueRef.getText().toString();


        if(checkFormLogicIsValid()){

            optionalFieldDefaultText();

            //Create the airdrop object
            Airdrop newAirdrop = new Airdrop(projectName,allLinks,startDate,endDate,tokenSymbol,restrictions,about,claimInstructions,estimatedValue,contactEmail);

            aAirdropsDatabaseReference.push().setValue(newAirdrop);//add a success listener
            showAlertWithButton("Airdrop sent for inspection", "We will contact you by email for modifications and before your airdrop is published", "I understand");

            clearForm();

        }

    }


//Check logic for mandatory fields
    private boolean checkFormLogicIsValid() {


        //Check if blank, or contain any unrelevant stuff

        if (contactEmail.isEmpty()) {
            Toast.makeText(this, "Contact email empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (projectName.isEmpty()) {
            Toast.makeText(this, "Project Name is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Check if blank, or contain any unrelevant stuff
        if (allLinks.isEmpty()) {
            Toast.makeText(this, "Airdrop links is empty", Toast.LENGTH_SHORT).show();
            return false;
        }


        if(startDate.isEmpty()){
            Toast.makeText(this, "Starting Date is empty", Toast.LENGTH_SHORT).show();
            return false;
        }


        if(about.length() > 1024 || about.isEmpty()){
            Toast.makeText(this, "About is empty or exceed 1024 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    private void clearForm(){

        contactEmailRef.setText("");
        projectNameRef.setText("");
        allLinksRef.setText("");
        startDateRef.setText("");
        endDateRef.setText("");
        tokenSymbolRef.setText("");
        restrictionsRef.setText("");
        aboutRef.setText("");
        claimInstructionsRef.setText("");
        estimatedValueRef.setText("");

    }

    /**
    Method assign default text for blank optional fields
     */
    private void optionalFieldDefaultText(){
        if(endDate.isEmpty()){
            endDate = "Unknown at the moment";
        }
        if(restrictions.isEmpty()){
            restrictions = "No Restrictions Known";
        }
        if(estimatedValue.isEmpty()){
            estimatedValue = "Unknown at the moment";
        }

    }



    private void showAlertWithButton(@NonNull String title, @NonNull String message, @NonNull String buttonMessage) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonMessage, null).create().show();
    }

}
