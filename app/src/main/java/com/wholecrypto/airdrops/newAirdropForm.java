package com.wholecrypto.airdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newAirdropForm extends AppCompatActivity {


    //References


    //Widgets
    EditText projectNameRef;
    Spinner projectCategoryRef;
    EditText websiteURLRef;

    EditText socialMediaRef;
    EditText whitePaperRef;
    EditText bountyRef;
    EditText airdropFormRef;

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

    CheckBox premiumRef;
    CheckBox termsRef;

    //Variables

    String projectName;
    String projectCategory;
    String websiteURL;

    String socialMedia;
    String whitePaper;
    String bounty;
    String airdropForm;

    String startDate;
    String endDate;

    String tokenSymbol;
    String platform;
    String tokenDistributed;
    String pricePerToken;

    String kycOrWhitelist;

    String restrictions;
    String about;

    boolean isPremium;
    boolean isTermsApproved;


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
// Specify the layout to use when the list of choices appears
        projectCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        projectCategorySpinner.setAdapter(projectCategoryAdapter);


        Spinner kycOrWhitelistSpinner = findViewById(R.id.kycOrWhitelist);
        ArrayAdapter<CharSequence> kycOrWhitelistAdapter = ArrayAdapter.createFromResource(this,
                R.array.kyc_or_whitelist, android.R.layout.simple_spinner_item);
        kycOrWhitelistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kycOrWhitelistSpinner.setAdapter(kycOrWhitelistAdapter);

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
        websiteURLRef = findViewById(R.id.websiteURL);

        socialMediaRef = findViewById(R.id.socialMediaURL);
        whitePaperRef = findViewById(R.id.whitepaperURL);
        bountyRef = findViewById(R.id.bountyURL);
        airdropFormRef = findViewById(R.id.airdropFormURL);

        startDateRef = findViewById(R.id.startDate);
        endDateRef = findViewById(R.id.endDate);

        tokenSymbolRef = findViewById(R.id.tokenSymbol);
        platformRef = findViewById(R.id.projectPlatform); //e.g. 'ERC20' or 'Ethereum'
        tokenDistributedRef = findViewById(R.id.tokensDistributed);
        pricePerTokenRef = findViewById(R.id.pricePerToken);

        kycOrWhitelistRef = findViewById(R.id.kycOrWhitelist);

        restrictionsRef = findViewById(R.id.restrictions);
        aboutRef = findViewById(R.id.about);

        premiumRef = findViewById(R.id.premium);
        termsRef = findViewById(R.id.terms);




        //Initialize the forms
        projectName = projectNameRef.getText().toString();
        projectCategory = projectCategoryRef.getSelectedItem().toString();
        websiteURL = websiteURLRef.getText().toString();

        socialMedia = socialMediaRef.getText().toString();
        whitePaper = whitePaperRef.getText().toString();
        bounty = bountyRef.getText().toString();
        airdropForm = airdropFormRef.getText().toString();

        startDate = startDateRef.getText().toString();
        endDate = endDateRef.getText().toString();

        tokenSymbol = tokenSymbolRef.getText().toString();
        platform = platformRef.getText().toString(); //e.g. 'ERC20' or 'Ethereum'
        tokenDistributed = tokenDistributedRef.getText().toString();
        pricePerToken = pricePerTokenRef.getText().toString();

       kycOrWhitelist = kycOrWhitelistRef.getSelectedItem().toString();

        restrictions = restrictionsRef.getText().toString();
        about = aboutRef.getText().toString();

        isPremium = premiumRef.isChecked();
        isTermsApproved = termsRef.isChecked();

        if(checkFormLogicIsValid()){
            //Create the airdrop object
            Airdrop newAirdrop = new Airdrop("Success");

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

        //check if description is more than 300 chars or is empty
        if(about.length() > 300 || about.isEmpty()){
            Toast toast = (Toast.makeText(this, "Description is empty or exceed 300 characters", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        if (!isTermsApproved) {
            Toast toast = (Toast.makeText(this, "You must accept the terms of service in order to publish airdrop", Toast.LENGTH_SHORT));
            toast.show();
            return false;
        }

        return true;
    }



    private void clearForm(){

        projectNameRef.setText("");
        projectCategoryRef.setVisibility(View.GONE);
        projectCategoryRef.setSelection(-1);

        websiteURLRef.setText("");

        socialMediaRef.setText("");
        whitePaperRef.setText("");
        bountyRef.setText("");
        airdropFormRef.setText("");

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

        premiumRef.setChecked(false);
        termsRef.setChecked(false);
    }





}
