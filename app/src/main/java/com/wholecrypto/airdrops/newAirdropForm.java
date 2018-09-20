package com.wholecrypto.airdrops;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class newAirdropForm extends AppCompatActivity {
    private static final String TAG = "newAirdropForm";


    //Widgets
    EditText contactEmailRef;
    EditText projectNameRef;
    Spinner projectCategoryRef;

    EditText allLinksRef;

    EditText startDateRef;
    setDate startDateDialog;
    EditText endDateRef;
    setDate endDateDialog;

    EditText tokenSymbolRef;
    EditText tokenDistributedRef;
    EditText pricePerTokenRef;

    Spinner kycOrWhitelistRef;

    EditText restrictionsRef;
    EditText aboutRef;
    EditText claimInstructionsRef;
    CheckBox contactCheckboxRef;



    //Variables
    String contactEmail;

    String projectName;
    String projectCategory;

    String allLinks;

    String startDate;
    String endDate;

    String tokenSymbol;
    String tokenDistributed;
    String pricePerToken;

    String kycOrWhitelist;

    String restrictions;
    String about;
    String claimInstructions;
    boolean contact_checkbox;

    boolean isPremium;


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


        Spinner projectCategorySpinner = findViewById(R.id.projectCategory);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> projectCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.project_category, android.R.layout.simple_spinner_item);


        projectCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK); //Change selected text color
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

// Specify the layout to use when the list of choices appears
        projectCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        projectCategorySpinner.setAdapter(projectCategoryAdapter);


        Spinner kycOrWhitelistSpinner = findViewById(R.id.kycOrWhitelist);

        //Changes the color of the selected item
        kycOrWhitelistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK); //Change selected text color
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> kycOrWhitelistAdapter = ArrayAdapter.createFromResource(this,
                R.array.kyc_or_whitelist, android.R.layout.simple_spinner_item);
        kycOrWhitelistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kycOrWhitelistSpinner.setAdapter(kycOrWhitelistAdapter);

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
        projectCategoryRef = findViewById(R.id.projectCategory);

        allLinksRef = findViewById(R.id.allLinksURL);

        startDateRef = findViewById(R.id.startDate);
        endDateRef = findViewById(R.id.endDate);

        tokenSymbolRef = findViewById(R.id.tokenSymbol);
        tokenDistributedRef = findViewById(R.id.tokensDistributed);
        pricePerTokenRef = findViewById(R.id.pricePerToken);

        kycOrWhitelistRef = findViewById(R.id.kycOrWhitelist);

        restrictionsRef = findViewById(R.id.restrictions);
        aboutRef = findViewById(R.id.about);
        claimInstructionsRef = findViewById(R.id.claim_instructions);
        contactCheckboxRef = findViewById(R.id.contact_checkbox);




        //Initialize the forms
        contactEmail = contactEmailRef.getText().toString();
        projectName = projectNameRef.getText().toString();
        projectCategory = projectCategoryRef.getSelectedItem().toString();

        allLinks = allLinksRef.getText().toString();

        startDate = startDateRef.getText().toString();
        endDate = endDateRef.getText().toString();

        tokenSymbol = tokenSymbolRef.getText().toString();
        tokenDistributed = tokenDistributedRef.getText().toString();
        pricePerToken = pricePerTokenRef.getText().toString();

        kycOrWhitelist = kycOrWhitelistRef.getSelectedItem().toString();

        restrictions = restrictionsRef.getText().toString();
        about = aboutRef.getText().toString();
        claimInstructions = claimInstructionsRef.getText().toString();
        contact_checkbox = contactCheckboxRef.isChecked();


        if(checkFormLogicIsValid()){


            //Create the airdrop object
            Airdrop newAirdrop = new Airdrop(projectName,projectCategory,allLinks,startDate,endDate,tokenSymbol,tokenDistributed,pricePerToken,kycOrWhitelist,restrictions,about,false,contactEmail,false,claimInstructions);

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

        if (tokenSymbol.isEmpty()) {
            Toast.makeText(this, "Token Symbol is empty", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (tokenDistributed.isEmpty()) {
            Toast.makeText(this, "Tokens distributed amount is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(pricePerToken.isEmpty()){
            Toast.makeText(this, "Price per token is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(about.length() > 1024 || about.isEmpty()){
            Toast.makeText(this, "About is empty or exceed 1024 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!contact_checkbox){
            Toast.makeText(this, "Contact Checkbox is not checked", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    private void clearForm(){

        contactEmailRef.setText("");
        projectNameRef.setText("");
        projectCategoryRef.setVisibility(View.GONE);
        projectCategoryRef.setSelection(-1);

        allLinksRef.setText("");

        startDateRef.setText("");
        endDateRef.setText("");

        tokenSymbolRef.setText("");
        tokenDistributedRef.setText("");
        pricePerTokenRef.setText("");

        kycOrWhitelistRef.setVisibility(View.GONE);
        kycOrWhitelistRef.setSelection(-1);

        restrictionsRef.setText("");
        aboutRef.setText("");
        claimInstructionsRef.setText("");
        contactCheckboxRef.setChecked(false);
    }



    private void showAlertWithButton(@NonNull String title, @NonNull String message, @NonNull String buttonMessage) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonMessage, null).create().show();
    }

}
