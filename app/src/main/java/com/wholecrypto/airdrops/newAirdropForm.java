package com.wholecrypto.airdrops;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newAirdropForm extends AppCompatActivity {


    //References


    //Widgets
    EditText projectNameRef;
    Spinner projectCategoryRef;

    EditText allLinksRef;

    EditText startDateRef;
    setDate startDateDialog;
    EditText endDateRef;
    setDate endDateDialog;

    EditText tokenSymbolRef;
    EditText platformRef; //e.g. 'ERC20' or 'Ethereum'
    EditText tokenDistributedRef;
    EditText pricePerTokenRef;

    Spinner kycOrWhitelistRef;

    EditText restrictionsRef;
    EditText aboutRef;
    EditText claimInstructionsRef;



    //Variables
    String sentByPhone = MainActivity.currentUserPhone;

    String projectName;
    String projectCategory;

    String allLinks;

    String startDate;
    String endDate;

    String tokenSymbol;
    String platform;
    String tokenDistributed;
    String pricePerToken;

    String kycOrWhitelist;

    String restrictions;
    String about;
    String claimInstructions;

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
                //TODO add link here
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
        projectNameRef = findViewById(R.id.icoProjectName);
        projectCategoryRef = findViewById(R.id.projectCategory);

        allLinksRef = findViewById(R.id.allLinksURL);

        startDateRef = findViewById(R.id.startDate);
        endDateRef = findViewById(R.id.endDate);

        tokenSymbolRef = findViewById(R.id.tokenSymbol);
        platformRef = findViewById(R.id.projectPlatform); //e.g. 'ERC20' or 'Ethereum'
        tokenDistributedRef = findViewById(R.id.tokensDistributed);
        pricePerTokenRef = findViewById(R.id.pricePerToken);

        kycOrWhitelistRef = findViewById(R.id.kycOrWhitelist);

        restrictionsRef = findViewById(R.id.restrictions);
        aboutRef = findViewById(R.id.about);
        claimInstructionsRef = findViewById(R.id.claim_instructions);




        //Initialize the forms
        projectName = projectNameRef.getText().toString();
        projectCategory = projectCategoryRef.getSelectedItem().toString();

        allLinks = allLinksRef.getText().toString();

        startDate = startDateRef.getText().toString();
        endDate = endDateRef.getText().toString();

        tokenSymbol = tokenSymbolRef.getText().toString();
        platform = platformRef.getText().toString(); //e.g. 'ERC20' or 'Ethereum'
        tokenDistributed = tokenDistributedRef.getText().toString();
        pricePerToken = pricePerTokenRef.getText().toString();

        kycOrWhitelist = kycOrWhitelistRef.getSelectedItem().toString();

        restrictions = restrictionsRef.getText().toString();
        about = aboutRef.getText().toString();
        claimInstructions = claimInstructionsRef.getText().toString();


        if(checkFormLogicIsValid()){


            //Create the airdrop object
            Airdrop newAirdrop = new Airdrop(projectName,projectCategory,allLinks,startDate,endDate,tokenSymbol,platform,tokenDistributed,pricePerToken,kycOrWhitelist,restrictions,about,false,sentByPhone,false,claimInstructions);

            aAirdropsDatabaseReference.push().setValue(newAirdrop);//add a success listener
            Toast toast = (Toast.makeText(this,"Form Sent Successfully",Toast.LENGTH_SHORT));
            toast.show();


            clearForm();

        }

    }


//Check logic for mandatory fields
    private boolean checkFormLogicIsValid() {


        //Check if blank, or contain any unrelevant stuff
        if (projectName.isEmpty()) {
            Toast toast = (Toast.makeText(this, "Project Name is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        //Check if blank, or contain any unrelevant stuff
        if (allLinks.isEmpty()) {
            Toast toast = (Toast.makeText(this, "Airdrop links is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }


        if(startDate.isEmpty()){
            Toast toast = (Toast.makeText(this, "Starting Date is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        if (tokenSymbol.isEmpty()) {
            Toast toast = (Toast.makeText(this, "Token Symbol is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }


        if (tokenDistributed.isEmpty()) {
            Toast toast = (Toast.makeText(this, "Tokens distributed amount is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        if(pricePerToken.isEmpty()){
            Toast toast = (Toast.makeText(this, "Price per token is empty", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        if(about.length() > 1024 || about.isEmpty()){
            Toast toast = (Toast.makeText(this, "About is empty or exceed 1024 characters", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        return true;
    }



    private void clearForm(){

        projectNameRef.setText("");
        projectCategoryRef.setVisibility(View.GONE);
        projectCategoryRef.setSelection(-1);

        allLinksRef.setText("");

        startDateRef.setText("");
        endDateRef.setText("");

        tokenSymbolRef.setText("");
        platformRef.setText("");
        tokenDistributedRef.setText("");
        pricePerTokenRef.setText("");

        kycOrWhitelistRef.setVisibility(View.GONE);
        kycOrWhitelistRef.setSelection(-1);

        restrictionsRef.setText("");
        aboutRef.setText("");
        claimInstructionsRef.setText("");
    }





}
